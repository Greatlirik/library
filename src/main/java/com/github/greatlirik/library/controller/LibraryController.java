package com.github.greatlirik.library.controller;

import com.github.greatlirik.library.entity.BookEntity;
import com.github.greatlirik.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LibraryController {
    private final BookRepository bookRepository;

    @GetMapping("/library")
    public ModelAndView page(@RequestParam(name = "title", required = false) String title) {
        final Iterable<BookEntity> books = Optional.ofNullable(title)
                .map(bookRepository::findAllByTitle)
                .orElseGet(bookRepository::findAll);
        final Map<String, Object> model = Map.of(
                "books", books
        );
        return new ModelAndView("library", model);
    }
}
