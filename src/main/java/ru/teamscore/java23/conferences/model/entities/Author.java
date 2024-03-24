package ru.teamscore.java23.conferences.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author", schema = "authors")
public class Author {

    // на случай если какой-то информации нет, но фамилия и имя у человека быть должны
    public Author(@NonNull String surname, @NonNull String firstName) {
        this.firstName = firstName;
        this.surname = surname;
    }
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @ManyToMany(mappedBy="authors")
    private ArrayList<Report> reports = new ArrayList<>();

    @Setter
    @Column(name = "first_name", columnDefinition = "text")
    private String firstName;
    @Setter
    @Column(columnDefinition = "text")
    private String surname;
    public String getFullName(){
        return surname + " " + firstName;
    }

    @Getter @Setter
    @Column(columnDefinition = "text")
    private String organization;

    @Getter @Setter
    @Column(columnDefinition = "text")
    private String description;

    @Getter @Setter
    @Column(name = "contact_info", columnDefinition = "text")
    private String contactInfo;

}
