package unze.ptf.battlearena.controller;

import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.data.GameData;
import unze.ptf.battlearena.model.Tool;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
public class ToolRestController {

    private final GameData data;

    public ToolRestController(GameData data) {
        this.data = data;
    }

    @GetMapping
    public List<Tool> getAllTools() {
        return data.findAllTools();
    }

    @GetMapping("/{id}")
    public Tool getTool(@PathVariable Long id) {
        return data.findTool(id);
    }

    @PostMapping
    public Tool addTool(@RequestBody Tool tool) {
        return data.saveTool(tool);
    }

    @PutMapping("/{id}")
    public Tool updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        Tool existing = data.findTool(id);
        if (existing != null) {
            existing.setName(tool.getName());
            existing.setPowerBoost(tool.getPowerBoost());
            existing.setLifeBoost(tool.getLifeBoost());
            return data.saveTool(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTool(@PathVariable Long id) {
        data.deleteTool(id);
    }
}
