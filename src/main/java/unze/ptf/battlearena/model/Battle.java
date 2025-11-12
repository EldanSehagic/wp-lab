package unze.ptf.battlearena.model;

import java.time.LocalDateTime;

public class Battle {

    private String playerName;
    private String opponentName;
    private int playerPower;
    private int opponentPower;
    private String result; // "Victory", "Defeat", "Draw"
    private LocalDateTime battleTime;

    public Battle(String playerName, String opponentName, int playerPower, int opponentPower, String result) {
        this.playerName = playerName;
        this.opponentName = opponentName;
        this.playerPower = playerPower;
        this.opponentPower = opponentPower;
        this.result = result;
        this.battleTime = LocalDateTime.now();
    }




    // Getteri i setteri
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
