package com.hcl.training.service;

import com.hcl.training.model.Student;
import com.hcl.training.repository.StudentRepository;

import java.util.List;

public final class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository) { this.repository = repository; }
    public void register(Student student) { repository.save(student); }
    public List<Student> listStudents() { return repository.findAll(); }
}
