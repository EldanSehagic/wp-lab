package unze.ptf.battlearena.service;

import org.springframework.stereotype.Service;
import unze.ptf.battlearena.model.Character;
import unze.ptf.battlearena.model.Battle;
import unze.ptf.battlearena.repository.BattleRepository;
import unze.ptf.battlearena.repository.CharacterRepository;

import java.util.Random;

@Service
public class BattleService {

    private final Random rnd = new Random();
    private final BattleRepository battleRepository;
    private final CharacterRepository characterRepository;

    public BattleService(BattleRepository battleRepository, CharacterRepository characterRepository) {
        this.battleRepository = battleRepository;
        this.characterRepository = characterRepository;
    }

    public Result simulate(Character c) {
        int level = c.getLevel();
        int opponentPower = Math.max(1, level + rnd.nextInt(5) - 2);

        int charPower = c.totalPower();
        int delta = charPower - opponentPower;

        String outcome;
        int gainedPoints = 0;

        if (delta > 0) {
            gainedPoints = 2 + Math.max(0, delta / 2);
            c.setPoints(c.getPoints() + gainedPoints);
            c.setPower(c.getPower() + 1);
            outcome = "POBJEDA";
        } else if (delta == 0) {
            gainedPoints = 1;
            c.setPoints(c.getPoints() + gainedPoints);
            outcome = "NERIJEŠENO";
        } else {
            c.setLives(Math.max(0, c.getLives() - 1));
            c.setPower(Math.max(0, c.getPower() - 1));
            outcome = "PORAZ";
        }

        // Povećaj level karaktera
        c.setLevel(level + 1);

        // Kreiraj Battle objekat sa 5 parametara (korektno)
        Battle battle = new Battle(
                c.getName(),               // playerName
                "Random Opponent",         // opponentName (može se nasumično generisati)
                charPower,                 // playerPower
                opponentPower,             // opponentPower
                outcome                    // result
        );

        // SAČUVAJ PROMJENE U BAZI
        characterRepository.save(c); // Sačuvaj ažurirani karakter
        battleRepository.save(battle); // Sačuvaj borbu u bazi

        // Vrati rezultat za view
        return new Result(outcome, opponentPower, gainedPoints, c.getLevel());
    }

    public static class Result {
        public final String outcome;
        public final int opponentPower;
        public final int gainedPoints;
        public final int newLevel;

        public Result(String outcome, int opponentPower, int gainedPoints, int newLevel) {
            this.outcome = outcome;
            this.opponentPower = opponentPower;
            this.gainedPoints = gainedPoints;
            this.newLevel = newLevel;
        }
    }
}