package unze.ptf.battlearena.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tools")
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "power_boost")
    private int powerBoost; // +snaga

    @Column(name = "life_boost")
    private int lifeBoost;  // 0 ili 1

    // Veza nazad na Character (opcionalno, ali korisno)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    public Tool() {}

    public Tool(String name, int powerBoost, int lifeBoost) {
        this.name = name;
        this.powerBoost = powerBoost;
        this.lifeBoost = lifeBoost;
    }

    // Getteri i setteri ostaju isti + dodajem getter/setter za character
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPowerBoost() { return powerBoost; }
    public int getLifeBoost() { return lifeBoost; }
    public Character getCharacter() { return character; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPowerBoost(int powerBoost) { this.powerBoost = powerBoost; }
    public void setLifeBoost(int lifeBoost) { this.lifeBoost = lifeBoost; }
    public void setCharacter(Character character) { this.character = character; }
}