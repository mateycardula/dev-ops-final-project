package mk.ukim.finki.devops.flashcards.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.devops.flashcards.models.AppUser;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;
import mk.ukim.finki.devops.flashcards.repository.FlashCardCollectionRepository;
import mk.ukim.finki.devops.flashcards.service.FlashCardCollectionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashCardCollectionServiceImpl implements FlashCardCollectionService {

    private final FlashCardCollectionRepository collectionRepository;

    public FlashCardCollectionServiceImpl(FlashCardCollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public FlashCardCollection createCollection(AppUser owner, String name) {
        FlashCardCollection collection = new FlashCardCollection();
        collection.setOwner(owner);
        collection.setName(name);
        return collectionRepository.save(collection);
    }

    @Override
    public List<FlashCardCollection> getCollectionsByUser(AppUser owner) {
        return collectionRepository.findByOwner(owner);
    }

    @Override
    public Optional<FlashCardCollection> getCollectionById(Long id) {
        return collectionRepository.findById(id);
    }

    @Override
    public void deleteCollection(Long id) {
        collectionRepository.deleteById(id);
    }
}
