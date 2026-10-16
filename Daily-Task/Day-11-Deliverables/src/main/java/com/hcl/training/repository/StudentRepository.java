package com.hcl.training.repository;

import com.hcl.training.model.Student;

public final class StudentRepository extends GenericRepository<Student, Long> {
    public StudentRepository() { super(Student::getId); }
}
