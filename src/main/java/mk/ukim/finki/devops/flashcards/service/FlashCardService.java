package mk.ukim.finki.devops.flashcards.service;

import mk.ukim.finki.devops.flashcards.models.FlashCard;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;

import java.util.List;
import java.util.Optional;

public interface FlashCardService {
    FlashCard createFlashCard(FlashCardCollection collection, String frontFace, String backFace);
    List<FlashCard> getFlashCardsByCollection(FlashCardCollection collection);
    Optional<FlashCard> getFlashCardById(Long id);
    void deleteFlashCard(Long id);
}
