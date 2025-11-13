package unze.ptf.battlearena.controller;

import org.springframework.web.bind.annotation.*;
import unze.ptf.battlearena.model.Tool;
import unze.ptf.battlearena.repository.ToolRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tools")
public class ToolRestController {

    private final ToolRepository toolRepository;

    public ToolRestController(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @GetMapping
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tool> getTool(@PathVariable Long id) {
        return toolRepository.findById(id);
    }

    @PostMapping
    public Tool addTool(@RequestBody Tool tool) {
        return toolRepository.save(tool);
    }

    @PutMapping("/{id}")
    public Tool updateTool(@PathVariable Long id, @RequestBody Tool tool) {
        Optional<Tool> existingOpt = toolRepository.findById(id);
        if (existingOpt.isPresent()) {
            Tool existing = existingOpt.get();
            existing.setName(tool.getName());
            existing.setPowerBoost(tool.getPowerBoost());
            existing.setLifeBoost(tool.getLifeBoost());
            return toolRepository.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTool(@PathVariable Long id) {
        toolRepository.deleteById(id);
    }
}