package ru.teamscore.java23.conferences.model.managers;

import ru.teamscore.java23.conferences.model.entities.Author;
import ru.teamscore.java23.conferences.model.entities.Conference;
import ru.teamscore.java23.conferences.model.entities.Report;
import ru.teamscore.java23.conferences.model.entities.Section;

import java.util.ArrayList;

public class ConferenceInfoManager {
    private ConferenceInfoManager(){} // на текущий момент класс предполагается как набор статических методов, мне не нужен конструктор

    public static int getOrganizationsCount(Conference conference) {
        ArrayList<String> organizations = new ArrayList<>();
        for (Section section : conference.getSections()){
            for(Report report : section.getReports()){
                for(Author author : report.getAuthors()){
                    String organization = author.getOrganization();
                    if(!organizations.contains(organization)){
                        organizations.add(organization);
                    }
                }
            }
        }
        return organizations.size();
    }

    public static int getAuthorsCount(Conference conference) {
        ArrayList<Author> authors = new ArrayList<>();

        for (Section section : conference.getSections()){
            for(Report report : section.getReports()){
                for(Author author : report.getAuthors()){
                    if(!authors.contains(author)){
                        authors.add(author);
                    }
                }
            }
        }

        return authors.size();
    }
    public static int getReportsCount(Conference conference){
        int count = 0;
        for (Section section : conference.getSections()){
            count += section.getReports().size();
        }
        return count;
    }

    // функционал прописан в самой конференции, потому что секции содержатся в её поле
    // и благодаря ломбоку класс не особо перегружен
    // но если кто-то попытается вызвать методы через менеджер - они тут есть
    public static ArrayList<Section> getSections(Conference conference){
        return conference.getSections();
    }
    public static int getSectionsCount(Conference conference){
        return conference.getSectionsCount();
    }
}