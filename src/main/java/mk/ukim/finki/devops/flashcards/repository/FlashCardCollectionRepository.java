package mk.ukim.finki.devops.flashcards.repository;

import mk.ukim.finki.devops.flashcards.models.AppUser;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashCardCollectionRepository extends JpaRepository<FlashCardCollection, Long> {
    List<FlashCardCollection> findByOwner(AppUser owner);
}
