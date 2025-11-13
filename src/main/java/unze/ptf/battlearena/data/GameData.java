package unze.ptf.battlearena.data;

import unze.ptf.battlearena.model.Character;
import unze.ptf.battlearena.model.Tool;
import unze.ptf.battlearena.model.Battle;

import java.util.*;

// @Component  // ZAKOMENTARIŠI OVO - Spring neće više koristiti ovu klasu!
public class GameData {

    private final Map<Long, Character> characters = new LinkedHashMap<>();
    private final Map<Long, Tool> tools = new LinkedHashMap<>();
    private final List<Battle> battles = new ArrayList<>(); // ➕ lista borbi
    private long charSeq = 1;
    private long toolSeq = 1;

    public GameData() {
        // seed tools - KORISTI NOVI KONSTRUKTOR BEZ ID
        saveTool(new Tool("Mač", 3, 0));
        saveTool(new Tool("Štit", 1, 0));
        saveTool(new Tool("Eliksir", 0, 1));
        saveTool(new Tool("Koplje", 2, 0));
        saveTool(new Tool("Čarobni prsten", 2, 1)); // +2 power, +1 life

        // seed characters - KORISTI NOVI KONSTRUKTOR BEZ ID
        saveCharacter(new Character("Ayla", 5, 5, 0));
        saveCharacter(new Character("Borin", 4, 5, 0));
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

    // Kupovina alata
    public void buyTool(Long characterId, Long toolId, int quantity) {
        var character = findCharacter(characterId);
        var tool = findTool(toolId);
        if (character != null && tool != null) {
            // dodaj alat karakteru
            character.getTools().add(tool);

            // po želji povećaj atribute
            character.setPower(character.getPower() + tool.getPowerBoost());
            character.setLives(character.getLives() + tool.getLifeBoost());
        }
    }

    // ➕ Battle metode
    public List<Battle> findAllBattles() {
        return new ArrayList<>(battles);
    }

    public void addBattle(Battle battle) {
        battles.add(battle);
    }

    public void deleteCharacter(Long id) {
        characters.remove(id);
    }

    public void deleteTool(Long id) {
        tools.remove(id);
    }
}