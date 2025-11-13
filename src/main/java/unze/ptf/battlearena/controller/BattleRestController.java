package unze.ptf.battlearena.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import unze.ptf.battlearena.model.Battle;
import unze.ptf.battlearena.repository.BattleRepository; // ISPRAVLJENO
import java.util.List;

@RestController
public class BattleRestController {

    private final BattleRepository battleRepository; // ISPRAVLJENO

    public BattleRestController(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    @GetMapping("/api/battles")
    public List<Battle> getAllBattles() {
        return battleRepository.findAll();
    }
}