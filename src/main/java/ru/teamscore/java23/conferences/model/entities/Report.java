package ru.teamscore.java23.conferences.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report", schema = "conferences")
public class Report {
    // доклада без темы и хотя бы одного автора не может быть
    public Report(String theme, ArrayList<Author> authors, String annotation){
        this(theme,authors);
        this.setAnnotation(annotation);
    }
    public Report(@NonNull String theme, @NonNull ArrayList<Author> authors){
        this.theme = theme;
        this.authors = authors;
    }

    @Getter
    @Id
    @Column(nullable = false)
    private long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Setter @Getter
    @Column(columnDefinition = "text")
    private String theme;

    @ManyToMany
    @JoinTable(name="author", schema = "authors")
    private ArrayList<Author> authors = new ArrayList<>();
    public void addAuthor(Author author){
        authors.add(author);
    }
    public void removeAuthor(Author author){
        authors.remove(author);
    }

    @Getter
    @Column(columnDefinition = "text")
    private String annotation;
    public static final int ANNOTATION_LENGTH_LIMIT = 1000;
    /* по хорошему стоило бы кинуть exeption на случай когда в куче записываемых докладов один не влезет и нам придётся
    теперь его искать чтобы исправить ошибку. Но аннотация не имеет значения для кода, так что просто поставим
    ограничение и сделаем своё универсальное решение проблемы - удаление лишних символов
    */
    public void setAnnotation(String annotation){
        if (annotation.length() > ANNOTATION_LENGTH_LIMIT) {
            annotation = annotation.subSequence(0, ANNOTATION_LENGTH_LIMIT).toString();
            // throw new SomeException();
        }
        this.annotation = annotation;
    }
}
