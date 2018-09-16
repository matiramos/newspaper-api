package com.utn.newspaper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reporter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotBlank
    @Size(min = 1, max = 50, message = "Nationality must be between 1 and 50 characters")
    private String nationality;

    @NotBlank
    @Size(min = 1, max = 250, message = "Bio must be between 1 and 250 characters")
    private String bio;

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    private List<News> news;
}
