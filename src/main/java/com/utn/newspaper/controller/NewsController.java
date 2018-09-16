package com.utn.newspaper.controller;

import com.utn.newspaper.model.News;
import com.utn.newspaper.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping(path = "/news")
    ResponseEntity<Page> getAll(Pageable p) {
        return ResponseEntity.ok(this.newsRepository.findAll(p));
    }

    @GetMapping(path = "/reporter/{id}/news")
    ResponseEntity<Collection<News>> getAllByReporter(@PathVariable Long id, Sort sort) {
        return ResponseEntity.ok(this.newsRepository.findAllByReporter(id, sort));
    }

    @GetMapping(path = "/category/{id}/news")
    ResponseEntity<Collection<News>> getAllByCategory(@PathVariable Long id) {
        return ResponseEntity.ok(this.newsRepository.findAllByCategory(id));
    }

    @GetMapping(path = "/news/{id}")
    ResponseEntity<News> get(@PathVariable Long id) {
        return this.newsRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping(path = "/news/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return this.newsRepository
                .findById(id)
                .map(news -> {
                    this.newsRepository.delete(news);
                    return ResponseEntity.accepted().build();
                })
                .orElse(ResponseEntity.noContent().build());
    }
}
