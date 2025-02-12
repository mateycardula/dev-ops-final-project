package mk.ukim.finki.devops.flashcards.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flashcards")
public class FlashCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String frontFace;

    @Column(nullable = false)
    private String backFace;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private FlashCardCollection collection;
}
