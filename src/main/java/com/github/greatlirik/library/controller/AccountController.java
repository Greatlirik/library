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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;

    @GetMapping("/admin")
    public ModelAndView adminPage(@RequestParam(name = "account", required = false) String account) {
            final Iterable<BookEntity> books = Optional.ofNullable(account)
                    .map(bookRepository::findAllByTitle)
                    .orElseGet(bookRepository::findAll);

            final Map<String, Object> model = Map.of(
                    "books", books
            );

        return new ModelAndView("admin", model);
    }

    @GetMapping("/account")
    public ModelAndView account() {
        final AccountEntity accountEntity = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(auth -> auth instanceof DefaultUserDetailsService.SimpleUserDetails)
                .map(auth -> (DefaultUserDetailsService.SimpleUserDetails) auth)
                .map(DefaultUserDetailsService.SimpleUserDetails::getAccount)
                .map(AccountEntity::getId)
                .flatMap(accountRepository::findById)
                .orElseThrow();
        final Map<String, Object> model = Map.of(
                "name", accountEntity.getName(),
                "books", accountEntity.getBooks()
        );
        return new ModelAndView("account", model);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String toLibrary() {
        return "redirect:/library";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(AccountEntity user, Map<String, Object> model) {
        final Optional<AccountEntity> foundUser = accountRepository.findByName(user.getName());
        if (foundUser.isPresent()) {
            model.put("message", "!User exists!");
            return "registration";
        }
        accountRepository.save(user
                .setActive(true)
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRoles(Set.of(Role.USER))
        );
        return "redirect:/login";
    }

//    @PostMapping("/account")
//    public String returnBook (){
//     Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//            .map(Authentication::getPrincipal)
//                .filter(auth -> auth instanceof DefaultUserDetailsService.SimpleUserDetails)
//            .map(auth -> (DefaultUserDetailsService.SimpleUserDetails) auth)
//            .map(DefaultUserDetailsService.SimpleUserDetails::getAccount)
//                .map(AccountEntity::getId)
//                .flatMap(accountId -> accountRepository
//
//    }
}
