package ru.teamscore.java23.conferences.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@AllArgsConstructor
public class Conference {

    public Conference(@NotNull Conference copyOf){
        title = copyOf.getTitle();
        eventDate = copyOf.getEventDate();
        eventOrganizer = copyOf.getEventOrganizer();
        sections = new ArrayList<>();
        sections.addAll(copyOf.getSections());

    }

    @Setter @Getter
    private String title;
    @Setter @Getter
    private LocalDate eventDate;
    @Setter @Getter
    private String eventOrganizer;

    @Getter
    private ArrayList<Section> sections;
    public void addSection(Section section){
        sections.add(section);
    }
    public void removeSection(Section section){
        sections.remove(section);
    }
}
