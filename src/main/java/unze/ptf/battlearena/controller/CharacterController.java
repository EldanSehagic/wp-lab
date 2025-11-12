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

    @GetMapping("/")
    public String home() {
        return "redirect:/characters";
    }

    // --- CHARACTERS ---
    @GetMapping("/characters")
    public String characters(Model model) {
        model.addAttribute("characters", data.findAllCharacters());
        return "characters";
    }

    // --- TOOLS ---
    @GetMapping("/tools")
    public String tools(Model model) {
        model.addAttribute("tools", data.findAllTools());
        model.addAttribute("characters", data.findAllCharacters());
        return "tools";
    }

    @PostMapping("/tools/buy")
    public String buyTool(@RequestParam Long characterId, @RequestParam Long toolId) {
        data.buyTool(characterId, toolId, 3); // cijena 3 boda
        return "redirect:/tools";
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
    public String startBattle(@RequestParam Long characterId,
                              Model model) {

        Character c = data.findCharacter(characterId);
        if (c == null) {
            return "redirect:/battle";
        }

        // Simulacija borbe i dodavanje u GameData (BattleService radi to)
        String result = battleService.simulate(c).toString();

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
        return "history"; // history.html u templates
    }
}
