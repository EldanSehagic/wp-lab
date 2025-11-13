package unze.ptf.battlearena.repository;
import unze.ptf.battlearena.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByName(String name);
    boolean existsByName(String name);
}