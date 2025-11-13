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
public class CharacterController {

    private final GameData data;
    private final BattleService battleService;

    public CharacterController(GameData data, BattleService battleService) {
        this.data = data;
        this.battleService = battleService;
    }

    // --- LIST ALL CHARACTERS ---
    @GetMapping("/")
    public String characters(Model model) {
        model.addAttribute("characters", data.findAllCharacters());
        return "characters"; // characters.html
    }

    // --- SHOW FORM TO ADD NEW CHARACTER ---
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("character", new Character());
        return "editCharacter";
    }

    // --- SAVE NEW OR UPDATED CHARACTER ---
    @PostMapping("/save")
    public String saveCharacter(@ModelAttribute Character character) {
        if (character.getId() == null) {
            // Novi karakter
            data.saveCharacter(character);
        } else {
            // Postojeći karakter, update
            Character existing = data.findCharacter(character.getId());
            if (existing != null) {
                existing.setName(character.getName());
                existing.setPower(character.getPower());
                existing.setLives(character.getLives());
                existing.setPoints(character.getPoints());
                existing.setLevel(character.getLevel());
                existing.setTools(character.getTools());
            }
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        data.deleteCharacter(id);
        return "redirect:/";
    }


    // --- SHOW FORM TO EDIT CHARACTER ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Character character = data.findCharacter(id);
        if (character == null) return "redirect:/";
        model.addAttribute("character", character);
        return "editCharacter";
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
        if (c == null) return "redirect:/battle";

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
