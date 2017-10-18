package com.utn.newspaper.repository;

import com.utn.newspaper.model.Reporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReporterRepository extends JpaRepository<Reporter, Long> {
}
