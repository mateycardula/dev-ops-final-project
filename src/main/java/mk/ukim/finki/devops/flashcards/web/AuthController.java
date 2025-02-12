package mk.ukim.finki.devops.flashcards.web;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.devops.flashcards.models.AppUser;
import mk.ukim.finki.devops.flashcards.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            AppUser newUser = new AppUser();
            newUser.setUsername(username);
            newUser.setPassword(password);
            userService.saveUser(newUser);

            model.addAttribute("successMessage", "User registered successfully! You can now log in.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            model.addAttribute("username", username);
            return "redirect:/";
        } catch (BadCredentialsException e) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login?logout";
    }
}
