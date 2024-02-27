package ru.teamscore.java23.conferences.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class Author {

    // на случай если какой-то информации нет, но фамилия и имя у человека быть должны
    public Author(@NotNull String surname, @NotNull String firstName) {
        this.firstName = firstName;
        this.surname = surname;
    }

    @Setter
    private String firstName;
    @Setter
    private String surname;
    public String getFullName(){
        return surname + " " + firstName;
    }

    @Getter @Setter
    private String organization;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String contactInfo;

}
