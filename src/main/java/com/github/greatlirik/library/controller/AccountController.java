package com.github.greatlirik.library.controller;

import com.github.greatlirik.library.entity.AccountEntity;
import com.github.greatlirik.library.entity.Role;
import com.github.greatlirik.library.repository.AccountRepository;
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

    @GetMapping("/account")
    public ModelAndView account() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> model = Map.of(
                "name", auth.getName()
        );
        return new ModelAndView("account", model);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(AccountEntity user, Map<String, Object> model) {
        final Optional<AccountEntity> foundUser = accountRepository.findByName(user.getName());
        if (foundUser.isPresent()) {
            model.put("message", "User exists!");
            return "registration";
        }
        accountRepository.save(user
                .setActive(true)
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setRoles(Set.of(Role.USER))
        );
        return "redirect:/login";
    }
}
