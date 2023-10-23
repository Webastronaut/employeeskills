package com.webastronaut.employeeskills;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private String login;

    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private Set<Repository> repositories = new HashSet<>();

}
