package mk.ukim.finki.devops.flashcards.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.devops.flashcards.models.AppUser;
import mk.ukim.finki.devops.flashcards.models.FlashCard;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;
import mk.ukim.finki.devops.flashcards.service.FlashCardCollectionService;
import mk.ukim.finki.devops.flashcards.service.FlashCardService;
import mk.ukim.finki.devops.flashcards.service.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class FlashCardController {

    private final FlashCardCollectionService collectionService;
    private final FlashCardService flashCardService;
    private final CustomUserDetailsService userService;

    public FlashCardController(FlashCardCollectionService collectionService, FlashCardService flashCardService, CustomUserDetailsService userService) {
        this.collectionService = collectionService;
        this.flashCardService = flashCardService;
        this.userService = userService;
    }

    @GetMapping
    public List<FlashCardCollection> getAllCollections() {
        AppUser user = getAuthenticatedUser();
        return collectionService.getCollectionsByUser(user);
    }

    @GetMapping("/{id}/flashcards")
    public List<FlashCard> getFlashCardsByCollection(@PathVariable Long id) {
        FlashCardCollection collection = collectionService.getCollectionById(id)
                .orElseThrow(() -> new RuntimeException("Collection not found"));
        return flashCardService.getFlashCardsByCollection(collection);
    }

    private AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
