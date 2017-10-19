package com.utn.newspaper.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
public class News {

    private Long id;
    private UUID uid = UUID.randomUUID();
    private String title;
    private String body;
    private Calendar date;
    private Category category;
    private Reporter reporter;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "TEXT")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name = "DATE_FIELD")
    @Temporal(TemporalType.DATE)
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "news_category_id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "news_reporter_id", nullable = false)
    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }
}
