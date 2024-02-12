package ru.teamscore.java23.conferences.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConferenceTest {

    @Test
    void deepCopyTest(){
        var title = "название1";
        var eventDate = LocalDate.of(2000,1,1);
        var eventOrganizer = "организатор1";
        var section1 = new Section("секция1","модератор1");
        var section2 = new Section("секция2","модератор2");
        var sections = new ArrayList<Section>();
        sections.add(section1);
        sections.add(section2);
        var conference1 = new Conference(title, eventDate, eventOrganizer, sections);
        var conference2 = new Conference(conference1);

        conference1.setTitle("название1 изменено");
        conference1.setEventDate(conference1.getEventDate().plusDays(1));
        conference1.setEventOrganizer("организатор1 изменено");
        conference1.removeSection(section1);

        Assertions.assertEquals("название1", conference2.getTitle());
        Assertions.assertEquals(eventDate, conference2.getEventDate());
        Assertions.assertEquals("организатор1", conference2.getEventOrganizer());
        Assertions.assertTrue(conference2.getSections().contains(section1));
    }
}
