package com.scryptan.springlab.dao;

import com.scryptan.springlab.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {
    private final EntityManager entityManager; // Не @Autowired, потому что инъекция в поле не рекомендуется

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> getAllStudents() {
        Query query = entityManager.createQuery("FROM Student");
        List<Student> result = query.getResultList();
        log.debug("all students: " + result);
        return result;
    }

    @Override
    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public void deleteStudent(int id) {
        entityManager.createQuery("delete from Student where id = :student_id")
        .setParameter("student_id", id)
        .executeUpdate();
    }
}
