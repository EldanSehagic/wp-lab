package unze.ptf.battlearena.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import unze.ptf.battlearena.data.GameData;
import unze.ptf.battlearena.model.Battle;
import java.util.List;

@RestController
public class BattleRestController {

    private final GameData data;

    public BattleRestController(GameData data) {
        this.data = data;
    }

    @GetMapping("/api/battles")
    public List<Battle> getAllBattles() {
        return data.findAllBattles();
    }
}
