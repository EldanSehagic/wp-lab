package unze.ptf.battlearena.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.data.GameData;
import unze.ptf.battlearena.model.Battle;
import unze.ptf.battlearena.model.Character;
import unze.ptf.battlearena.model.Tool;
import unze.ptf.battlearena.service.BattleService;

@Controller
@RequestMapping("/tools")
public class ToolController {

    private final GameData data;

    public ToolController(GameData data) {
        this.data = data;
    }

    @GetMapping
    public String tools(Model model) {
        model.addAttribute("tools", data.findAllTools());
        return "tools";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("tool", new Tool());
        return "editTool";
    }

    @PostMapping("/save")
    public String saveTool(@ModelAttribute Tool tool) {
        data.saveTool(tool);
        return "redirect:/tools";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("tool", data.findTool(id));
        return "editTool";
    }

    @GetMapping("/delete/{id}")
    public String deleteTool(@PathVariable Long id) {
        data.deleteTool(id);
        return "redirect:/tools";
    }
}
