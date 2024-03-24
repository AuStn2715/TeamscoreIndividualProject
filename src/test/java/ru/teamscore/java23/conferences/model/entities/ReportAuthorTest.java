package ru.teamscore.java23.conferences.model.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportAuthorTest {
    private static SessionFactory sessionFactory;

    @Test
    public void persistThenSelect() {
        prepareData();

        Session session = sessionFactory.openSession();
        List<Report> reports = session.createQuery("FROM conferences.report").list();
        List<Author> authors = session.createQuery("FROM authors.author").list();
        session.getTransaction().commit();
        session.close();
        assertNotNull(reports);
        assertNotNull(authors);
        assertEquals(2, reports.size());
        assertEquals(2, authors.size());

        for(Report report : reports) {
            assertNotNull(report.getAuthors());
            assertEquals(2, report.getAuthors().size());
        }
        for(Author author : authors) {
            assertNotNull(author.getReports());
            assertEquals(2, author.getReports().size());
        }
    }

    private void prepareData() {
        Session session = sessionFactory.openSession();
        ArrayList<Author> authors1 = new ArrayList<>();
        ArrayList<Author> authors2 = new ArrayList<>();
        Author author1 = new Author("Алексеев", "А.");
        Author author2 = new Author("<Бубнов>", "Б.");
        Author author3 = new Author("<Вареньев>", "В.");
        authors1.add(author1);
        authors1.add(author2);
        authors2.add(author2);
        authors2.add(author3);

        Report report1 = new Report("Доклад1", authors1);
        for (Author author : authors1) {
            author.getReports().add(report1);
        }
        session.persist(report1);
        Report report2 = new Report("Доклад2", authors2);
        for (Author author : authors2) {
            author.getReports().add(report2);
        }
        session.persist(report2);
        session.getTransaction().commit();
        session.close();
    }
}
