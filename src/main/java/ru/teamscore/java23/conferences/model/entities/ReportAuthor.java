package ru.teamscore.java23.conferences.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "report_author", schema = "conferences")
public class ReportAuthor {
    @Embeddable
    static class ReportAuthorPK{
        @Getter
        @Setter
        @ManyToOne
        @JoinColumn(name = "report_id")
        private Report report;

        @Getter
        @Setter
        @ManyToOne
        @JoinColumn(name = "author_id")
        private Author author;
    }

    @EmbeddedId
    private ReportAuthorPK pk = new ReportAuthorPK();

    @Transient
    public Report getReport() {
        return pk.getReport();
    }

    @Transient
    public Author getAuthor() {
        return pk.getAuthor();
    }

    public ReportAuthor(@NonNull Report report, @NonNull Author author) {
        pk.report = report;
        pk.author = author;
    }
}
