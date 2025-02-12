package mk.ukim.finki.devops.flashcards.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.devops.flashcards.models.FlashCard;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class FlashCardController {

    private static final List<FlashCardCollection> MOCK_COLLECTIONS = Arrays.asList(
            new FlashCardCollection(1L, "Science Facts", null, null),
            new FlashCardCollection(2L, "History Trivia", null, null),
            new FlashCardCollection(3L, "Programming Terms", null, null)
    );

    private static final List<FlashCard> MOCK_FLASHCARDS = Arrays.asList(
            new FlashCard(1L, "What is the speed of light?", "299,792 km/s", MOCK_COLLECTIONS.get(0)),
            new FlashCard(2L, "Who discovered gravity?", "Isaac Newton", MOCK_COLLECTIONS.get(1)),
            new FlashCard(3L, "What does OOP stand for?", "Object-Oriented Programming", MOCK_COLLECTIONS.get(2))
    );

    @GetMapping
    public List<FlashCardCollection> getAllCollections() {
        return MOCK_COLLECTIONS;
    }

    @GetMapping("/{id}/flashcards")
    public List<FlashCard> getFlashCardsByCollection(@PathVariable Long id) {
        return MOCK_FLASHCARDS.stream()
                .filter(card -> card.getCollection().getId().equals(id))
                .toList();
    }
}
