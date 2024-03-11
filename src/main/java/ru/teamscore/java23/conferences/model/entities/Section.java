package ru.teamscore.java23.conferences.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section", schema = "conferences")
public class Section {
    // врядли секция может обойтись без ответственного человека и названия
    // возможно на момент создания ещё неизвестны время и место начала и регламент
    public Section(String title, String moderator){
        this.title = title;
        this.moderator = moderator;
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @Getter @Setter
    @Column(columnDefinition = "text")
    private String title;

    @Getter @Setter
    @Column(columnDefinition = "text")
    private String venue;

    @Getter @Setter
    @Column(columnDefinition = "text")
    private String moderator;

    @Getter @Setter
    @Column(name = "start_date_time", columnDefinition = "timestamp")
    private LocalDateTime startDateTime;

    @Getter @Setter
    @Column(name = "report_duration_limit", columnDefinition = "int")
    private int reportDurationLimit;

    @Getter @OneToMany
    private ArrayList<Report> reports = new ArrayList<Report>();
    public void addReport(Report report){
        reports.add(report);
    }
    public void removeReport(Report report){
        reports.remove(report);
    }
}
