package com.scryptan.springlab.repository;

import com.scryptan.springlab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
