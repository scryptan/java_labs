package com.scryptan.springlab.dao;

import com.scryptan.springlab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
