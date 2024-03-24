package ru.teamscore.java23.conferences.model.managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import ru.teamscore.java23.conferences.model.SqlScripts;
import ru.teamscore.java23.conferences.model.entities.*;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConferenceManagerTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ConferenceManager conferenceManager;
    @BeforeAll
    public static void setup() throws IOException {
        entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Conference.class)
                .addAnnotatedClass(Section.class)
                .addAnnotatedClass(Report.class)
                .addAnnotatedClass(Author.class)
                .buildSessionFactory();
        SqlScripts.runFromFile(entityManagerFactory, "createSchema.sql");
    }

    @AfterAll
    public static void tearDown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
    @BeforeEach
    public void openSession() throws IOException {
        SqlScripts.runFromFile(entityManagerFactory, "insertTestData.sql");
        entityManager = entityManagerFactory.createEntityManager();
        conferenceManager = new ConferenceManager(entityManager);
    }

    @AfterEach
    public void closeSession() throws IOException {
        if (entityManager != null) {
            entityManager.close();
        }
        SqlScripts.runFromFile(entityManagerFactory, "clearTestData.sql");
    }

    @Test
    void getSectionsCount(){
        assertEquals(2, conferenceManager.getSectionsCount(2L));
    }

    @Test
    void getAllSectionsId() {
        assertArrayEquals(new Long[]{1L,2L},
                conferenceManager.getAllSectionsId(1L));
    }

    @Test
    void getReportsCount() {
        assertEquals(9,
                conferenceManager.getReportsCount(1L));
    }
}
