package com.utn.newspaper.repository;

import com.utn.newspaper.model.Category;
import com.utn.newspaper.repository.projection.CategoryNoNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT id, name FROM news_category", nativeQuery = true)
    List<CategoryNoNews> findAllBy();

    @Query(value = "SELECT n.id, n.name FROM news_category n where n.id = ?1", nativeQuery = true)
    Optional<CategoryNoNews> findBy(Long id);
}
