package mk.ukim.finki.devops.flashcards.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.devops.flashcards.models.FlashCard;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;
import mk.ukim.finki.devops.flashcards.repository.FlashCardRepository;
import mk.ukim.finki.devops.flashcards.service.FlashCardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlashCardServiceImpl implements FlashCardService {

    private final FlashCardRepository flashCardRepository;

    @Override
    public FlashCard createFlashCard(FlashCardCollection collection, String frontFace, String backFace) {
        FlashCard flashCard = new FlashCard();
        flashCard.setCollection(collection);
        flashCard.setFrontFace(frontFace);
        flashCard.setBackFace(backFace);
        return flashCardRepository.save(flashCard);
    }

    @Override
    public List<FlashCard> getFlashCardsByCollection(FlashCardCollection collection) {
        return flashCardRepository.findByCollection(collection);
    }

    @Override
    public Optional<FlashCard> getFlashCardById(Long id) {
        return flashCardRepository.findById(id);
    }

    @Override
    public void deleteFlashCard(Long id) {
        flashCardRepository.deleteById(id);
    }
}
