package unze.ptf.battlearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.model.Tool;
import unze.ptf.battlearena.model.Character;
import unze.ptf.battlearena.repository.ToolRepository;
import unze.ptf.battlearena.repository.CharacterRepository;

import java.util.Optional;

@Controller
@RequestMapping("/tools")
public class ToolController {

    private final ToolRepository toolRepository;
    private final CharacterRepository characterRepository;

    public ToolController(ToolRepository toolRepository, CharacterRepository characterRepository) {
        this.toolRepository = toolRepository;
        this.characterRepository = characterRepository;
    }

    // --- LIST ALL TOOLS ---
    @GetMapping
    public String tools(Model model) {
        model.addAttribute("tools", toolRepository.findAll());
        model.addAttribute("characters", characterRepository.findAll()); // za kupovinu alata
        return "tools";
    }

    // --- BUY TOOL ---
    @PostMapping("/buy")
    public String buyTool(@RequestParam Long characterId, @RequestParam Long toolId) {
        Optional<Character> characterOpt = characterRepository.findById(characterId);
        Optional<Tool> toolOpt = toolRepository.findById(toolId);

        if (characterOpt.isPresent() && toolOpt.isPresent()) {
            Character character = characterOpt.get();
            Tool tool = toolOpt.get();

            // dodaj alat karakteru
            character.getTools().add(tool);

            // po želji povećaj atribute
            character.setPower(character.getPower() + tool.getPowerBoost());
            character.setLives(character.getLives() + tool.getLifeBoost());

            // sačuvaj promjene
            characterRepository.save(character);
        }
        return "redirect:/tools";
    }

    // --- SHOW FORM TO ADD NEW TOOL ---
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("tool", new Tool());
        return "editTool";
    }

    // --- SAVE NEW OR UPDATED TOOL ---
    @PostMapping("/save")
    public String saveTool(@ModelAttribute Tool tool) {
        toolRepository.save(tool);
        return "redirect:/tools";
    }

    // --- SHOW FORM TO EDIT TOOL ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Tool> tool = toolRepository.findById(id);
        if (tool.isEmpty()) return "redirect:/tools";
        model.addAttribute("tool", tool.get());
        return "editTool";
    }

    // --- DELETE TOOL ---
    @GetMapping("/delete/{id}")
    public String deleteTool(@PathVariable Long id) {
        toolRepository.deleteById(id);
        return "redirect:/tools";
    }
}