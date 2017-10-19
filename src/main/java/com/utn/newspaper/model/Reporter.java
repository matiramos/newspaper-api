package com.utn.newspaper.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Reporter {

    private Long id;
    private UUID uid = UUID.randomUUID();
    private String name;
    private List<News> news;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
