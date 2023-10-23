package com.webastronaut.employeeskills;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class EmployeeUsedLanguages {
    private String name;
    private Map<String, Integer> languageUsedInRepos;
}
