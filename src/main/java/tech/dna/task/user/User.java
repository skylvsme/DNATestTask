package tech.dna.task.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @JsonIgnore
    private String password;
    private String name;
    private LocalDate creationDate;

}