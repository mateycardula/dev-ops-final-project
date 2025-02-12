package mk.ukim.finki.devops.flashcards.service;

import mk.ukim.finki.devops.flashcards.models.AppUser;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;

import java.util.List;
import java.util.Optional;

public interface FlashCardCollectionService {
    FlashCardCollection createCollection(AppUser owner, String name);
    List<FlashCardCollection> getCollectionsByUser(AppUser owner);
    Optional<FlashCardCollection> getCollectionById(Long id);
    void deleteCollection(Long id);
}
