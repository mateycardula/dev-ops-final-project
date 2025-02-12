package mk.ukim.finki.devops.flashcards.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.devops.flashcards.models.AppUser;
import mk.ukim.finki.devops.flashcards.models.FlashCardCollection;
import mk.ukim.finki.devops.flashcards.service.CustomUserDetailsService;
import mk.ukim.finki.devops.flashcards.service.FlashCardCollectionService;
import mk.ukim.finki.devops.flashcards.service.FlashCardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/collections")
public class FlashCardViewController {

    private final FlashCardCollectionService collectionService;
    private final FlashCardService flashCardService;
    private final CustomUserDetailsService userService;

    public FlashCardViewController(FlashCardCollectionService collectionService, FlashCardService flashCardService, CustomUserDetailsService userService) {
        this.collectionService = collectionService;
        this.flashCardService = flashCardService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public String showCreateCollectionForm() {
        return "create-collection";
    }

    @PostMapping("/create")
    public String createCollection(@RequestParam String name) {
        AppUser user = getAuthenticatedUser();
        collectionService.createCollection(user, name);
        return "redirect:/";
    }

    @GetMapping("/{id}/flashcards/new")
    public String showCreateFlashCardForm(@PathVariable Long id, Model model) {
        FlashCardCollection collection = collectionService.getCollectionById(id)
                .orElseThrow(() -> new RuntimeException("Collection not found"));
        model.addAttribute("collection", collection);
        return "create-flashcard";
    }

    @PostMapping("/{id}/flashcards/create")
    public String createFlashCard(
            @PathVariable Long id,
            @RequestParam String frontFace,
            @RequestParam String backFace) {

        FlashCardCollection collection = collectionService.getCollectionById(id)
                .orElseThrow(() -> new RuntimeException("Collection not found"));

        flashCardService.createFlashCard(collection, frontFace, backFace);
        return "redirect:/";
    }

    private AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
