package tech.dna.task.offer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import tech.dna.task.category.Category;
import tech.dna.task.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
@Getter
@Setter
public class Offer {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;

    private LocalDate startDate;
    private LocalDate endDate;

}
