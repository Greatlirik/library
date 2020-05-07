package com.github.greatlirik.library.controller;

import com.github.greatlirik.library.entity.BookEntity;
import com.github.greatlirik.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/books/{id}")
    public ModelAndView addBookPage(@PathVariable("id") Integer id) {
        final BookEntity book = bookRepository.findById(id)
                .orElse(null);//throw 404 here
        final Map<String, Object> model = Map.of(
                "book", book
        );
        return new ModelAndView("book", model);
    }

    @GetMapping("/books")
    public String addBookPage() {
        return "book-add";
    }

    @PostMapping("/books")
    public String addBook(@RequestParam(name = "title") String title,@RequestParam(name = "genre") String genre,@RequestParam(name = "year") Integer year,@RequestParam(name="quantity") Integer quantity) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(year);
        book.setQuantity(quantity);
        book = bookRepository.save(book);
        return String.format("redirect:/books/%s", book.getId());
    }
    @PostMapping("/books/{id}")
    public String takeBook(@PathVariable("id") Integer id) {
        bookRepository.findById(id)
                        .map(bookEntity -> {
                            bookEntity.setQuantity(bookEntity.getQuantity()-1);
                            return bookEntity;
                        })
                        .ifPresent(bookRepository::save);
        return String.format("redirect:/");

    }



}
