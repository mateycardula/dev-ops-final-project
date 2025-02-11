package mk.ukim.finki.devops.flashcards.repository;
import mk.ukim.finki.devops.flashcards.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}

