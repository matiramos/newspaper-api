package com.utn.newspaper.controller;

import com.utn.newspaper.model.Category;
import com.utn.newspaper.repository.CategoryRepository;
import com.utn.newspaper.repository.projection.CategoryNoNews;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    ResponseEntity<Collection<CategoryNoNews>> getAll() {
        return ResponseEntity.ok(this.categoryRepository.findAllBy());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<CategoryNoNews> get(@PathVariable Long id) {
        return this.categoryRepository
                .findBy(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    ResponseEntity<Category> add(@Valid @RequestBody Category category) {
        final Category c = this.categoryRepository
                .save(new Category(null,
                        category.getName(),
                        category.getNews()));

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/" + c.getId())
                .build()
                .toUri();

        return ResponseEntity.created(uri).body(c);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        return this.categoryRepository
                .findById(id)
                .map(category -> {
                    this.categoryRepository.delete(category);
                    return ResponseEntity.accepted().build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Category category) {
        Optional<Category> c = this.categoryRepository.findById(id);
        if (!c.isPresent()) return ResponseEntity.noContent().build();

        category.setId(c.get().getId());
        this.categoryRepository.save(category);

        return ResponseEntity.accepted().body(category);
    }
}
