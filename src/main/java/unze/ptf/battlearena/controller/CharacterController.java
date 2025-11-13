package unze.ptf.battlearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.model.Battle;
import unze.ptf.battlearena.model.Character;
import unze.ptf.battlearena.repository.BattleRepository;
import unze.ptf.battlearena.repository.CharacterRepository;
import unze.ptf.battlearena.service.BattleService;

import java.util.List;
import java.util.Optional;

@Controller
public class CharacterController {

    private final CharacterRepository characterRepository;
    private final BattleRepository battleRepository;
    private final BattleService battleService;

    public CharacterController(CharacterRepository characterRepository,
                               BattleRepository battleRepository,
                               BattleService battleService) {
        this.characterRepository = characterRepository;
        this.battleRepository = battleRepository;
        this.battleService = battleService;
    }

    // --- LIST ALL CHARACTERS ---
    @GetMapping("/")
    public String characters(Model model) {
        model.addAttribute("characters", characterRepository.findAll());
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
        characterRepository.save(character); // JPA automatski zna da li je novo ili update
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        characterRepository.deleteById(id);
        return "redirect:/";
    }

    // --- SHOW FORM TO EDIT CHARACTER ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Character> character = characterRepository.findById(id);
        if (character.isEmpty()) return "redirect:/";
        model.addAttribute("character", character.get());
        return "editCharacter";
    }

    // --- BATTLE PAGE ---
    @GetMapping("/battle")
    public String battle(Model model,
                         @RequestParam(required = false) Long characterId) {
        model.addAttribute("characters", characterRepository.findAll());
        model.addAttribute("selectedId", characterId);
        return "battle";
    }

    @PostMapping("/battle/start")
    public String startBattle(@RequestParam Long characterId, Model model) {
        Optional<Character> characterOpt = characterRepository.findById(characterId);
        if (characterOpt.isEmpty()) return "redirect:/battle";

        Character c = characterOpt.get();
        var result = battleService.simulate(c);

        model.addAttribute("characters", characterRepository.findAll());
        model.addAttribute("selectedId", characterId);
        model.addAttribute("character", c);
        model.addAttribute("result", result);
        return "battle";
    }

    // --- BATTLE HISTORY ---
    @GetMapping("/history")
    public String history(Model model) {
        List<Battle> battles = battleRepository.findAll();
        model.addAttribute("battles", battles);
        return "history";
    }
}