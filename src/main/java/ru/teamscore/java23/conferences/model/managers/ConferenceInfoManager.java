package ru.teamscore.java23.conferences.model.managers;

import ru.teamscore.java23.conferences.model.entities.Author;
import ru.teamscore.java23.conferences.model.entities.Conference;
import ru.teamscore.java23.conferences.model.entities.Section;

import java.util.*;
import java.util.stream.Collectors;

public class ConferenceInfoManager {

    public ConferenceInfoManager(Conference conference){
        this.conference = conference;
    }

    Conference conference;

    public int getOrganizationsCount() {

        return conference.getSections().stream()
                .flatMap(section -> section.getReports().stream()
                .flatMap(report -> report.getAuthors().stream()))
                .map(Author::getOrganization)
                .map(String::new)
                .collect(Collectors.toSet())
                .size();
    }

    public int getAuthorsCount() {
        return (int) conference.getSections().stream()
                .flatMap(section -> section.getReports().stream()
                .flatMap(report -> report.getAuthors().stream()))
                .distinct()
                .count();
    }
    public int getReportsCount(){
        int count = 0;
        for (Section section : conference.getSections()){
            count += section.getReports().size();
        }
        return count;
    }

    // функционал прописан в самой конференции, потому что секции содержатся в её поле
    // и благодаря ломбоку класс не особо перегружен
    // но если кто-то попытается вызвать методы через менеджер - они тут есть
    public ArrayList<Section> getSections(){
        return conference.getSections();
    }
    public int getSectionsCount(){
        return conference.getSectionsCount();
    }
}