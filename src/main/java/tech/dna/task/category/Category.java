package tech.dna.task.category;

import lombok.Getter;

import javax.persistence.*;

@Table(name = "categories")
@Entity
@Getter
public class Category {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}