package unze.ptf.battlearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.data.GameData;
import unze.ptf.battlearena.model.Battle;
import unze.ptf.battlearena.model.Character;
import unze.ptf.battlearena.service.BattleService;

import java.util.List;

@Controller
@RequestMapping("/characters")
public class CharacterController {

    private final GameData data;
    private final BattleService battleService;

    public CharacterController(GameData data, BattleService battleService) {
        this.data = data;
        this.battleService = battleService;
    }

    // --- LIST ALL CHARACTERS ---
    @GetMapping
    public String characters(Model model) {
        model.addAttribute("characters", data.findAllCharacters());
        return "characters"; // characters.html
    }

    // --- SHOW FORM TO ADD NEW CHARACTER ---
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("character", new Character());
        return "character-form"; // Napraviti character-form.html
    }

    // --- SAVE NEW OR UPDATED CHARACTER ---
    @PostMapping("/save")
    public String saveCharacter(@ModelAttribute Character character) {
        data.saveCharacter(character); // save radi i insert i update po ID
        return "redirect:/characters";
    }

    // --- SHOW FORM TO EDIT CHARACTER ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Character character = data.findCharacter(id);
        if (character == null) {
            return "redirect:/characters";
        }
        model.addAttribute("character", character);
        return "character-form"; // Isti form kao za dodavanje
    }

    // --- DELETE CHARACTER ---
    @GetMapping("/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        Character character = data.findCharacter(id);
        if (character != null) {
            data.findAllCharacters().remove(character); // privremeno, kasnije u bazi ovo postaje repository.delete()
        }
        return "redirect:/characters";
    }

    // --- TOOLS PAGE ---
    @GetMapping("/tools")
    public String tools(Model model) {
        model.addAttribute("tools", data.findAllTools());
        model.addAttribute("characters", data.findAllCharacters());
        return "tools";
    }

    @PostMapping("/tools/buy")
    public String buyTool(@RequestParam Long characterId, @RequestParam Long toolId) {
        data.buyTool(characterId, toolId, 3);
        return "redirect:/characters/tools";
    }

    // --- BATTLE PAGE ---
    @GetMapping("/battle")
    public String battle(Model model,
                         @RequestParam(required = false) Long characterId) {
        model.addAttribute("characters", data.findAllCharacters());
        model.addAttribute("selectedId", characterId);
        return "battle";
    }

    @PostMapping("/battle/start")
    public String startBattle(@RequestParam Long characterId, Model model) {
        Character c = data.findCharacter(characterId);
        if (c == null) return "redirect:/characters/battle";

        var result = battleService.simulate(c);

        model.addAttribute("characters", data.findAllCharacters());
        model.addAttribute("selectedId", characterId);
        model.addAttribute("character", c);
        model.addAttribute("result", result);
        return "battle";
    }

    // --- BATTLE HISTORY ---
    @GetMapping("/history")
    public String history(Model model) {
        List<Battle> battles = data.findAllBattles();
        model.addAttribute("battles", battles);
        return "history";
    }
}
