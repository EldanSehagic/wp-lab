package unze.ptf.battlearena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import unze.ptf.battlearena.data.GameData;

@SpringBootApplication
public class BattleArenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BattleArenaApplication.class, args);
    }

    /**
     * GameData komponenta je već označena sa @Component,
     * ali možemo napraviti i @Bean ako želimo ručnu konfiguraciju.
     * Ovo je opcionalno jer Spring već upravlja komponentom.
     */
    @Bean
    public GameData gameData() {
        return new GameData();
    }
}
