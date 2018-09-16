package com.utn.newspaper.repository;

import com.utn.newspaper.model.News;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query(value = "SELECT n  FROM News n WHERE news_reporter_id = ?1")
    List<News> findAllByReporter(Long id, Sort sort);

    @Query(value = "SELECT * FROM News WHERE news_category_id = ?1", nativeQuery = true)
    List<News> findAllByCategory(Long id);
}
