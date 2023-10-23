package com.webastronaut.employeeskills;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    @Id
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_login", referencedColumnName = "login", nullable = false)
    private Employee owner;

    @ManyToMany
    @JoinTable(
            name = "repo_languages",
            joinColumns = @JoinColumn(name = "repository_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<RepositoryLanguage> languages = new HashSet<>();
}
