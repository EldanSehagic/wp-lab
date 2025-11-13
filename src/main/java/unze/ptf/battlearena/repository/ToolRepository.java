package unze.ptf.battlearena.repository;

import unze.ptf.battlearena.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    List<Tool> findByCharacterId(Long characterId);
}