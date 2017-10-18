package com.utn.newspaper.repository;

import com.utn.newspaper.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NewsRepository extends JpaRepository<News, Long> {
}
