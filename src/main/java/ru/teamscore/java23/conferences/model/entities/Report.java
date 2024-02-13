package ru.teamscore.java23.conferences.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Report {
    // доклада без темы и хотя бы одного автора не может быть
    public Report(String theme, ArrayList<Author> authors, String annotation){
        new Report(theme,authors);
        this.setAnnotation(annotation);
    }
    public Report(@NotNull String theme, @NotNull ArrayList<Author> authors){
        this.theme = theme;
        this.authors = authors;
    }

    @Setter @Getter
    private String theme;

    @Getter
    private ArrayList<Author> authors;
    public void addAuthor(Author author){
        authors.add(author);
    }
    public void removeAuthor(Author author){
        authors.remove(author);
    }

    @Getter
    private String annotation;
    private final int ANNOTATION_LENGTH_LIMIT = 1000;
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
