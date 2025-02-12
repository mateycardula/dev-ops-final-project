package mk.ukim.finki.devops.flashcards.repository;

import mk.ukim.finki.devops.flashcards.models.FlashCard;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {
    List<FlashCard> findByCollection(FlashCardCollection collection);
}
