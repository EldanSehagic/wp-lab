package unze.ptf.battlearena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.data.GameData;
import unze.ptf.battlearena.model.Tool;

@Controller
@RequestMapping("/tools")
public class ToolController {

    private final GameData data;

    public ToolController(GameData data) {
        this.data = data;
    }

    // --- LIST ALL TOOLS ---
    @GetMapping
    public String tools(Model model) {
        model.addAttribute("tools", data.findAllTools());
        model.addAttribute("characters", data.findAllCharacters()); // za kupovinu alata
        return "tools";
    }

    // --- BUY TOOL ---
    @PostMapping("/buy")
    public String buyTool(@RequestParam Long characterId, @RequestParam Long toolId) {
        data.buyTool(characterId, toolId, 3);
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
        data.saveTool(tool);
        return "redirect:/tools";
    }

    // --- SHOW FORM TO EDIT TOOL ---
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        var tool = data.findTool(id);
        if (tool == null) return "redirect:/tools";
        model.addAttribute("tool", tool);
        return "editTool";
    }

    // --- DELETE TOOL ---
    @GetMapping("/delete/{id}")
    public String deleteTool(@PathVariable Long id) {
        data.deleteTool(id);
        return "redirect:/tools";
    }
}
