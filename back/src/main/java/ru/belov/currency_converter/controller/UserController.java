package ru.belov.currency_converter.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belov.currency_converter.config.BaseCurrency;
import ru.belov.currency_converter.dto.user.UserDtoIn;
import ru.belov.currency_converter.dto.user.UserDtoOut;
import ru.belov.currency_converter.service.UserService;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @GetMapping("current")
    public UserDtoOut getCurrent() {
        return userService.getCurrent();
    }

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @PostMapping("login")
    public ResponseEntity<?> signIn(
            @RequestBody UserDtoIn dtoIn,
            HttpServletResponse response,
            HttpServletRequest request) {
        userService.signIn(dtoIn, response, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
