package com.github.greatlirik.library.controller;

import com.github.greatlirik.library.entity.AccountEntity;
import com.github.greatlirik.library.entity.BookEntity;
import com.github.greatlirik.library.entity.Role;
import com.github.greatlirik.library.repository.AccountRepository;
import com.github.greatlirik.library.repository.BookRepository;
import com.github.greatlirik.library.service.DefaultUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LibraryController {
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;

    @GetMapping("/library")
    public ModelAndView page(@RequestParam(name = "title", required = false) String title) {
//        final AccountEntity accountEntity = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                .map(Authentication::getPrincipal)
//                .filter(auth -> auth instanceof DefaultUserDetailsService.SimpleUserDetails)
//                .map(auth -> (DefaultUserDetailsService.SimpleUserDetails) auth)
//                .map(DefaultUserDetailsService.SimpleUserDetails::getAccount)
//                .map(AccountEntity::getId)
//                .flatMap(accountRepository::findById)
//                .orElseThrow();
        final Iterable<BookEntity> books = Optional.ofNullable(title)
                .map(bookRepository::findAllByTitle)
                .orElseGet(bookRepository::findAll);
        final Map<String, Object> model = Map.of(
                "books", books
//                "name", accountEntity.getName()
        );
//            model.put("message",accountEntity.getName());
//        final Iterable<BookEntity> booksSearch = Optional.ofNullable(text)
//                .map(bookRepository::findAllByTitle)
//                .orElseGet(bookRepository::findAll);
//        final Map<String, Object> modelSearch = Map.of(
//                "books", booksSearch
//        );
        return new ModelAndView("library", model);
    }


//   @GetMapping("/library/{search}")
//    public ModelAndView page(@RequestParam(value = "text", required = false) String text) {
//        final Iterable<BookEntity> books = Optional.ofNullable(text)
//                .map(bookRepository::findAllByTitle)
//                .orElseGet(bookRepository::findAll);
//        final Map<String, Object> model = Map.of(
//                "books", books
//        );
//        return new ModelAndView("library/", model);
//    }

//    @GetMapping("/library")
//    public String showBooksByTitle(@RequestParam(value = "search", required = false) String text, Model model){
//        model.addAttribute("search",bookRepository.findAllByTitle(text));
//        return "library";
//    }
    @PostMapping("/library")
        public String searchResult(){
        return "redirect:/search-result";
    }

    @GetMapping("/search-result")
    public ModelAndView pageSearch(@RequestParam(name = "text", required = false) String text) {
        final Iterable<BookEntity> books = Optional.ofNullable(text)
                .map(bookRepository::findAllByTitle)
                .orElseGet(bookRepository::findAll);
        final Map<String, Object> model = Map.of(
                "books", books
        );
        return new ModelAndView("search-result", model);
    }
}