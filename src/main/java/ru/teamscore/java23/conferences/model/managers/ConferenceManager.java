package ru.teamscore.java23.conferences.model.managers;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.teamscore.java23.conferences.model.entities.Conference;
import ru.teamscore.java23.conferences.model.entities.Report;
import ru.teamscore.java23.conferences.model.entities.Section;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public class ConferenceManager {
    private final EntityManager entityManager;

    //@Getter()
    //private final InfoManager info = new InfoManager();

    public long getSectionsCount(long conferenceId){
        return entityManager
                .createQuery("select count(*) from Section where conference.id = :param",Long.class)
                .setParameter("param", conferenceId)
                .getSingleResult() - 1; // цепляет несуществующую или пустую строку селектом?
    }
    public Long[] getAllSectionsId(long conferenceId) {
        return entityManager
                .createQuery("select id from Section where conference.id = :param",Long.class)
                .setParameter("param", conferenceId)
                .getResultList()
                .toArray(Long[]::new);
    }


    public long getReportsCount(long conferenceId) {
        //String[] ids = (String[]) Arrays.stream(getAllSectionsId(conferenceId)).map(String::valueOf).toArray();
        //String str = "(" + String.join(", ", ids);

        long count = 0;
        for (long id:getAllSectionsId(conferenceId)){
            count += entityManager
                    .createQuery("select count(*) from Report where section.id = :sections", Long.class)
                    .setParameter("sections", id)
                    .getSingleResult();
        }
        return count;
    }

    /*
    public Report[] getReportsAll() {
        return entityManager
                .createQuery("from Report order by id", Report.class)
                .getResultList()
                .toArray(Report[]::new);
    }

    нужны: количество секций, докладов, авторов, организаций
    расписание всех докладов в секции, разбитых по времени
    поиск доклада по теме и автору
    редактирование и копирование конференций
    редактирование секций
    редактирование докладов и авторов
    хранение авторов между конференциями

    public Optional<Report> getReport(long id) {
        return Optional.of(
                entityManager.find(Report.class, id)
        );
    }
    public Optional<Section> getSection(long id) {
        return Optional.of(
                entityManager.find(Section.class, id)
        );
    }
    public void addReport(Report report) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(report);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public void updateReport(@NonNull Report report) {
        entityManager.getTransaction().begin();
        entityManager.merge(report);
        entityManager.getTransaction().commit();
    }
     */
}
