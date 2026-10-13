package com.hcl.training;

import java.util.Objects;

public record Employee(int id, String name, String department, double salary) {
    public Employee {
        if (id <= 0 || name == null || name.isBlank() || department == null || department.isBlank() || salary < 0) {
            throw new IllegalArgumentException("Invalid employee data");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Employee employee)) return false;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
