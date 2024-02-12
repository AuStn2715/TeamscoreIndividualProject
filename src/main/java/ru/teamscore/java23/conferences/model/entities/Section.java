package ru.teamscore.java23.conferences.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
public class Section {
    // врядли секция может обойтись без ответственного человека и названия
    // возможно на момент создания ещё неизвестны время и место начала и регламент
    public Section(String title, String moderator){
        this.title = title;
        this.moderator = moderator;
    }

    @Getter @Setter
    private String title;
    @Getter @Setter
    private String venue;
    @Getter @Setter
    private String moderator;
    @Getter @Setter
    private LocalDateTime startDateTime;
    @Getter @Setter
    private int reportDurationLimit;

    @Getter
    private ArrayList<Report> reports;
    public void addReport(Report report){
        reports.add(report);
    }
    public void removeReport(Report report){
        reports.remove(report);
    }
}
