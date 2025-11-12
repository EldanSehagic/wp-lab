package unze.ptf.battlearena.service;

import org.springframework.stereotype.Service;
import unze.ptf.battlearena.model.Character;

import java.util.Random;

@Service
public class BattleService {
    private final Random rnd = new Random();

/* nakon prve borbe atributi power i lives karaktera se mijenjaju, ali se ne resetuju nikad više;
i ako jednom postane jako snažan (npr. 10 powera), svi protivnici na nižim levelima su preslabi;
ili ako izgubi snagu i živote, postaje preslab da ikad više pobijedi.*/
    public Result simulate(Character c) {
        int level = c.getLevel(); // koristi level iz samog karaktera
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

        // ⬇️ povećaj level nakon borbe
        c.setLevel(level + 1);

        return new Result(outcome, opponentPower, gainedPoints, c.getLevel());
    }
/* Opcija 2 za balansiranu demo logiku je da Svaka borba kreće sa privremenim kopijama atributa – karakter “ne pamti” rezultate između borbi.
Može se mijenjati servis tako da računa ishod, ali ne mijenja karakterove vrijednosti u trajnoj memoriji (tj. bez setLives() i setPower()).

U tom slučaju funkcija simulate bi izgledala ovako:
public Result simulate(Character c) {
    int level = c.getLevel();
    int opponentPower = Math.max(1, level + rnd.nextInt(5) - 2);
    int charPower = c.totalPower();
    int delta = charPower - opponentPower;

    String outcome;
    int gainedPoints = 0;

    if (delta > 0) {
        gainedPoints = 2 + Math.max(0, delta / 2);
        outcome = "POBJEDA";
    } else if (delta == 0) {
        gainedPoints = 1;
        outcome = "NERIJEŠENO";
    } else {
        outcome = "PORAZ";
    }

    c.setPoints(c.getPoints() + gainedPoints);
    c.setLevel(level + 1);

    return new Result(outcome, opponentPower, gainedPoints, c.getLevel());
}
*/


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
