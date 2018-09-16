package com.utn.newspaper.controller;

import com.utn.newspaper.model.Reporter;
import com.utn.newspaper.repository.ReporterRepository;
import com.utn.newspaper.repository.projection.ReporterNoNews;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/reporters")
public class ReporterController {

    private final ReporterRepository reporterRepository;

    public ReporterController(ReporterRepository reporterRepository) {
        this.reporterRepository = reporterRepository;
    }

    @GetMapping
    ResponseEntity<Collection<ReporterNoNews>> getAll() {
        return ResponseEntity.ok(this.reporterRepository.findAllBy());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ReporterNoNews> get(@PathVariable Long id) {
        return this.reporterRepository
                .findBy(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    ResponseEntity<Reporter> add(@Valid @RequestBody Reporter reporter) {
        final Reporter r = this.reporterRepository
                .save(new Reporter(null,
                        reporter.getName(),
                        reporter.getNationality(),
                        reporter.getBio(),
                        reporter.getNews()));

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + r.getId())
                .build()
                .toUri();

        return ResponseEntity.created(uri).body(r);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return this.reporterRepository
                .findById(id)
                .map(reporter -> {
                    this.reporterRepository.delete(reporter);
                    return ResponseEntity.accepted().build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Reporter reporter) {
        Optional<Reporter> r = this.reporterRepository.findById(id);
        if (!r.isPresent()) return ResponseEntity.noContent().build();

        reporter.setId(r.get().getId());
        this.reporterRepository.save(reporter);

        return ResponseEntity.accepted().body(reporter);
    }
}
