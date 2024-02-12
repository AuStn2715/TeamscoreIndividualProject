package ru.teamscore.java23.conferences.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
public class Report {
    // доклада без темы и хотя бы одного автора не может быть, но без пояснений - вполне
    public Report(String theme, ArrayList<Author> authors){
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

    @Setter @Getter
    private String annotation;
}
