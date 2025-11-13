package unze.ptf.battlearena.repository;

import unze.ptf.battlearena.model.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BattleRepository extends JpaRepository<Battle, Long> {
    List<Battle> findByPlayerNameOrderByBattleTimeDesc(String playerName);
    List<Battle> findByResultOrderByBattleTimeDesc(String result);
}