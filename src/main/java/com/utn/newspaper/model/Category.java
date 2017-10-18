package com.utn.newspaper.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "news_category")
public class Category {

    private Long id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
