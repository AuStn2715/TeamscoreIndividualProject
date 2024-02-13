package ru.teamscore.java23.conferences.model.managers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.teamscore.java23.conferences.model.entities.Author;
import ru.teamscore.java23.conferences.model.entities.Conference;
import ru.teamscore.java23.conferences.model.entities.Report;
import ru.teamscore.java23.conferences.model.entities.Section;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConferenceInfoManagerTest {

    @Test
    void countTest(){
        var title = "конференция1";
        var eventOrganizer = "организатор1";

        var author1 = new Author("фамилия1", "имя1");
        var author2 = new Author("фамилия2", "имя2");
        var author3 = new Author("фамилия3", "имя3");
        author1.setOrganization("организация1");
        author2.setOrganization("организация1");
        author3.setOrganization("организация2");

        var report1 = new Report("доклад1", new ArrayList<Author>());
        report1.addAuthor(author1);
        report1.addAuthor(author3);
        var report2 = new Report("доклад2", new ArrayList<Author>());
        report2.addAuthor(author2);
        report2.addAuthor(author3);


        var section1 = new Section("секция1","модератор1");
        section1.addReport(report1);
        var section2 = new Section("секция2","модератор2");
        section2.addReport(report2);

        var conference = new Conference(title, eventOrganizer);
        conference.addSection(section1);
        conference.addSection(section2);


        Assertions.assertEquals(2, ConferenceInfoManager.getSectionsCount(conference));
        Assertions.assertEquals(3, ConferenceInfoManager.getAuthorsCount(conference));
        Assertions.assertEquals(2, ConferenceInfoManager.getOrganizationsCount(conference));
        Assertions.assertEquals(2, ConferenceInfoManager.getReportsCount(conference));
    }
}
