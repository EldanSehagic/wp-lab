package unze.ptf.battlearena.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "power")
    private int power;   // trenutna snaga

    @Column(name = "lives")
    private int lives;   // 0..5

    @Column(name = "points")
    private int points;  // skupljeni bodovi

    @Column(name = "level")
    private int level; // broj odigranih borbi / trenutni level

    // VEZA SA TOOL-OVIMA - OneToMany ili ElementCollection
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private List<Tool> tools = new ArrayList<>(); // max 3

    public Character() {}

    public Character(String name, int power, int lives, int points) {
        this.name = name;
        this.power = power;
        this.lives = lives;
        this.points = points;
        this.level = 0;
    }

    // Getteri i setteri ostaju isti...
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPower() { return power; }
    public int getLives() { return lives; }
    public int getPoints() { return points; }
    public List<Tool> getTools() { return tools; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPower(int power) { this.power = power; }
    public void setLives(int lives) { this.lives = lives; }
    public void setPoints(int points) { this.points = points; }
    public void setTools(List<Tool> tools) { this.tools = tools; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int totalPower() {
        int boost = tools.stream().mapToInt(Tool::getPowerBoost).sum();
        return power + boost;
    }

    public int totalLives() {
        int boost = tools.stream().mapToInt(Tool::getLifeBoost).sum();
        int total = lives + boost;
        return Math.min(total, 5); // globalni limit 5
    }

    public boolean canAddTool() {
        return tools.size() < 3;
    }

    public void addTool(Tool t) {
        if (canAddTool()) {
            tools.add(t);
        }
    }
}