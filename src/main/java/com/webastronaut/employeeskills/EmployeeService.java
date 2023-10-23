package com.webastronaut.employeeskills;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<EmployeeUsedLanguages> getEmployeesWithLanguagesInfo() {
        return employeeRepository.findAll().stream()
                .map(employee -> EmployeeUsedLanguages.builder()
                        .name(employee.getName())
                        .languageUsedInRepos(createEmployeeLanguagesMap(employee.getRepositories()))
                        .build())
                .toList();
    }

    public List<EmployeeRanked> getEmployeeByLanguage(String language) {
        List<EmployeeUsedLanguages> employeesWithLanguages = getEmployeesWithLanguagesInfo();

        if (language != null) {
            String languageNormalized = normalizeLanguageSearchString(language);
            // TODO Ranking!
            return employeesWithLanguages.stream()
                    .filter(employee -> employee.getLanguageUsedInRepos().containsKey(languageNormalized))
                    .map(employeeLanguages -> EmployeeRanked.builder()
                            .rank(0)
                            .name(employeeLanguages.getName())
                            .build())
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    String normalizeLanguageSearchString(String language) {
        return language.toUpperCase();
    }

    /**
     * Sorts languages map in descending order
     */
    Map<String, Integer> sortLanguages(Map<String, Integer> languages) {
        return languages.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                // merge into new map
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (entry1, entry2) -> entry1, LinkedHashMap::new));
    }

    Map<String, Integer> createEmployeeLanguagesMap(Set<Repository> repositories) {
        Map<String, Integer> languages = new HashMap<>();
        repositories.forEach(repository -> {
            repository.getLanguages().forEach(language -> {
                // init new entry with 1, otherwise increment
                languages.compute(language.getName(), (key, value) -> value == null ? 1 : value + 1);
            });
        });

        return sortLanguages(languages);
    }
}
