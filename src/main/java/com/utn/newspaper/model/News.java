package com.utn.newspaper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 150, message = "Title must be between 1 and 150 characters")
    private String title;

    @NotBlank
    @Size(min = 1, max = 200, message = "Subtitle must be between 1 and 200 characters")
    private String subtitle;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String body;

    @NotNull
    @Column(name = "DATE_FIELD")
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "news_category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "news_reporter_id", nullable = false)
    private Reporter reporter;
}
