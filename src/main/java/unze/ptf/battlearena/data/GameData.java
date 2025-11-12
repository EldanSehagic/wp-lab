package unze.ptf.battlearena.data;

import org.springframework.stereotype.Component;
import unze.ptf.battlearena.model.Character;
import unze.ptf.battlearena.model.Tool;

import java.util.*;

@Component
public class GameData {

    private final Map<Long, Character> characters = new LinkedHashMap<>();
    private final Map<Long, Tool> tools = new LinkedHashMap<>();
    private long charSeq = 1;
    private long toolSeq = 1;

    public GameData() {
        // seed tools
        saveTool(new Tool(null, "Mač", 3, 0));
        saveTool(new Tool(null, "Štit", 1, 0));
        saveTool(new Tool(null, "Eliksir", 0, 1));
        saveTool(new Tool(null, "Koplje", 2, 0));
        saveTool(new Tool(null, "Čarobni prsten", 2, 1)); // +2 power, +1 life (life cap 5)

        // seed characters
        saveCharacter(new Character(null, "Ayla", 5, 5, 0));
        saveCharacter(new Character(null, "Borin", 4, 5, 0));
    }

    // Characters
    public List<Character> findAllCharacters() {
        return new ArrayList<>(characters.values());
    }

    public Character findCharacter(Long id) {
        return characters.get(id);
    }

    public Character saveCharacter(Character c) {
        if (c.getId() == null) c.setId(charSeq++);
        characters.put(c.getId(), c);
        return c;
    }

    // Tools
    public List<Tool> findAllTools() {
        return new ArrayList<>(tools.values());
    }

    public Tool findTool(Long id) {
        return tools.get(id);
    }

    public Tool saveTool(Tool t) {
        if (t.getId() == null) t.setId(toolSeq++);
        tools.put(t.getId(), t);
        return t;
    }

    // Kupovina alata: max 3 alata, lifeBoost pojedinačno max 1 (model već to poštuje)
    public boolean buyTool(Long characterId, Long toolId, int pricePoints) {
        Character c = findCharacter(characterId);
        Tool t = findTool(toolId);
        if (c == null || t == null) return false;
        if (!c.canAddTool()) return false;
        if (c.getPoints() < pricePoints) return false;

        c.setPoints(c.getPoints() - pricePoints);
        c.addTool(t);
        return true;
    }
}
