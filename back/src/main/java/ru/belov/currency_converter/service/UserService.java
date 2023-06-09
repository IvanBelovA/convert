package ru.belov.currency_converter.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import ru.belov.currency_converter.dto.user.UserDtoIn;
import ru.belov.currency_converter.dto.user.UserDtoOut;

@Component
public interface UserService {

    UserDtoOut getCurrent();

    void signIn(UserDtoIn dtoIn, HttpServletResponse response, HttpServletRequest request);
}
