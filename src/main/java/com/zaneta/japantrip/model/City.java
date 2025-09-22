package com.zaneta.japantrip.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "City name is required")
    @Column(unique = true)
    private String nameOfCity;

    private String nameOfPrefecture;

    @NotBlank(message = "Description is required")
    private String description;

    private Long population;

    @NotNull(message = "Area is required")
    private Double area;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Attraction> attractions = new HashSet<>();

}

