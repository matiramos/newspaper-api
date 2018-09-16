package com.utn.newspaper.repository;

import com.utn.newspaper.model.Reporter;
import com.utn.newspaper.repository.projection.ReporterNoNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReporterRepository extends JpaRepository<Reporter, Long> {

    @Query(value = "SELECT id, name, nationality, bio FROM reporter", nativeQuery = true)
    List<ReporterNoNews> findAllBy();

    @Query(value = "SELECT r.id, r.name, r.nationality, r.bio FROM reporter r where r.id = ?1", nativeQuery = true)
    Optional<ReporterNoNews> findBy(Long id);
}
