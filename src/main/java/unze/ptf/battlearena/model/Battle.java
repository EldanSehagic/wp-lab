package unze.ptf.battlearena.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "battles")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DODAO SAM ID - OBAVEZNO ZA JPA

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "opponent_name", nullable = false)
    private String opponentName;

    @Column(name = "player_power")
    private int playerPower;

    @Column(name = "opponent_power")
    private int opponentPower;

    @Column(name = "result")
    private String result; // "Victory", "Defeat", "Draw"

    @Column(name = "battle_time")
    private LocalDateTime battleTime;

    // OBAVEZAN PRAZAN KONSTRUKTOR ZA JPA
    public Battle() {
    }

    public Battle(String playerName, String opponentName, int playerPower, int opponentPower, String result) {
        this.playerName = playerName;
        this.opponentName = opponentName;
        this.playerPower = playerPower;
        this.opponentPower = opponentPower;
        this.result = result;
        this.battleTime = LocalDateTime.now();
    }

    // Getteri i setteri OSTAJU ISTI + DODAJEMO getId() i setId()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public int getPlayerPower() {
        return playerPower;
    }

    public void setPlayerPower(int playerPower) {
        this.playerPower = playerPower;
    }

    public int getOpponentPower() {
        return opponentPower;
    }

    public void setOpponentPower(int opponentPower) {
        this.opponentPower = opponentPower;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDateTime getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(LocalDateTime battleTime) {
        this.battleTime = battleTime;
    }

    @Override
    public String toString() {
        return playerName + " vs " + opponentName + " → " + result + " (" + battleTime + ")";
    }
}