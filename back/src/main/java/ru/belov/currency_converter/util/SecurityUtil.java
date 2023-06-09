package ru.belov.currency_converter.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.belov.currency_converter.entity.Users;

@Slf4j
public class SecurityUtil {

    public static Users currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof Users) {
            return (Users) principal;
        }
        throw new IllegalArgumentException("Principal don cast user");
    }
}
