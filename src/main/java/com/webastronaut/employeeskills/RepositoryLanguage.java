package com.webastronaut.employeeskills;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryLanguage {
    @Id
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "languages")
    private Set<Repository> repositories = new HashSet<>();
}
