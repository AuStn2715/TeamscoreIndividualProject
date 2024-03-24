package ru.teamscore.java23.conferences.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conference", schema = "conferences")
public class Conference {
    public Conference(@NonNull Conference copyOf){
        title = copyOf.getTitle();
        eventDate = copyOf.getEventDate();
        eventOrganizer = copyOf.getEventOrganizer();
        sections = new ArrayList<>();
        sections.addAll(copyOf.getSections());
    }
    // конференция должна иметь название и организатора
    // даже если фактического организатора ещё нет - кто-то должен считаться ответственным по его поиску
    public Conference(@NonNull String title, @NonNull String eventOrganizer){
        this.title = title;
        this.eventOrganizer = eventOrganizer;
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter @Getter
    @Column(columnDefinition = "text")
    private String title;

    @Setter @Getter
    @Column(name ="event_date", columnDefinition = "date")
    private LocalDate eventDate;

    @Setter @Getter
    @Column(name = "event_organizer", columnDefinition = "text")
    private String eventOrganizer;

    @Setter @Getter @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Section> sections = new ArrayList<Section>();
    public void addSection(@NonNull Section section){
        section.setConference(this);
        sections.add(section);
    }
    public void removeSection(@NonNull Section section){
        section.setConference(null);
        sections.remove(section);
    }
    public int getSectionsCount(){
        return sections.size();
    }
}
