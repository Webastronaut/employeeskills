package com.webastronaut.employeeskills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmployeeRanked {
    private int rank;
    private String name;
}
