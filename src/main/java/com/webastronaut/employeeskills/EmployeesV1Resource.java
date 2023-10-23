package com.webastronaut.employeeskills;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employees/v1")
@RequiredArgsConstructor
public class EmployeesV1Resource {
    private final EmployeeService employeeService;

    @GetMapping(value = "", produces = "application/json")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/languages", produces = "application/json")
    public List<EmployeeUsedLanguages> getEmployeesWithLanguagesInfo() {
        return employeeService.getEmployeesWithLanguagesInfo();
    }

    @GetMapping(value = "/find", produces = "application/json")
    public List<EmployeeRanked> getEmployeeByLanguage(@RequestParam(name = "language", required = false) String language) {
        return employeeService.getEmployeeByLanguage(language);
    }

}
