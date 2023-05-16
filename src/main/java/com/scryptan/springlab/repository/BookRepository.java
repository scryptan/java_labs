package com.scryptan.springlab.repository;

import com.scryptan.springlab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
