package mk.ukim.finki.devops.flashcards.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.devops.flashcards.models.AppUser;
import mk.ukim.finki.devops.flashcards.service.CustomUserDetailsService;
import mk.ukim.finki.devops.flashcards.service.FlashCardCollectionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/collections")
public class FlashCardViewController {

    private final FlashCardCollectionService collectionService;
    private final CustomUserDetailsService userService; // ✅ Use CustomUserDetailsService

    public FlashCardViewController(FlashCardCollectionService collectionService, CustomUserDetailsService userService) {
        this.collectionService = collectionService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public String showCreateCollectionForm() {
        return "create-collection"; // Thymeleaf template
    }

    @PostMapping("/create")
    public String createCollection(@RequestParam String name) {
        AppUser user = getAuthenticatedUser();
        collectionService.createCollection(user, name);
        return "redirect:/"; // Reload dashboard
    }

    private AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
