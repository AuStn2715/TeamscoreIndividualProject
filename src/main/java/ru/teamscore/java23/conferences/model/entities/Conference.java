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
    // конференция должна иметь название и организатора
    // даже если фактического организатора ещё нет - кто-то должен считаться ответственным по его поиску
    public Conference(@NotNull String title, @NotNull String eventOrganizer){
        this.title = title;
        this.eventOrganizer = eventOrganizer;
    }

    @Setter @Getter
    private String title;
    @Setter @Getter
    private LocalDate eventDate;
    @Setter @Getter
    private String eventOrganizer;

    @Getter
    private ArrayList<Section> sections = new ArrayList<Section>();
    public void addSection(Section section){
        sections.add(section);
    }
    public void removeSection(Section section){
        sections.remove(section);
    }
    public int getSectionsCount(){
        return sections.size();
    }
}
