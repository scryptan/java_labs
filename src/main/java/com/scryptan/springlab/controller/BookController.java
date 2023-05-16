package com.scryptan.springlab.controller;

import com.scryptan.springlab.entity.Book;
import com.scryptan.springlab.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

    final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository BookRepository) {
        this.bookRepository = BookRepository;
    }

    @GetMapping({"/list", "/"})
    public ModelAndView getAllBooks(){
        log.info("list -> connection");

        ModelAndView mav = new ModelAndView("list-books");
        mav.addObject("books", bookRepository.findAll());

        return mav;
    }

    @GetMapping("/addBookForm")
    public ModelAndView addBookForm(){

        ModelAndView mav = new ModelAndView("add-book-form");
        Book Book = new Book();
        mav.addObject("book", Book);
        return mav;
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/books/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long bookId){

        ModelAndView mav = new ModelAndView("add-book-form");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book Book = new Book();

        if(optionalBook.isPresent())
            Book = optionalBook.get();

        mav.addObject("book", Book);
        return mav;
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam Long bookId){
        bookRepository.deleteById(bookId);
        return "redirect:/books/list";
    }

}
