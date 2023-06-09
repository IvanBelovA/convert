package ru.belov.currency_converter.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.currency_converter.dto.user.UserDtoIn;
import ru.belov.currency_converter.dto.user.UserDtoOut;
import ru.belov.currency_converter.entity.Users;
import ru.belov.currency_converter.mapper.UserMapper;
import ru.belov.currency_converter.repository.UserRepository;
import ru.belov.currency_converter.service.UserService;
import ru.belov.currency_converter.service.security.BasicAuthentication;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final BasicAuthentication basicAuthentication;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public UserDtoOut getCurrent() {
        return userMapper.toDto(userRepository.current().get());
    }

    @Override
    @Transactional
    public void signIn(UserDtoIn dtoIn, HttpServletResponse response, HttpServletRequest request) {
        Optional<Users> user = userRepository.findById(dtoIn.getId());

        if (user.isPresent()) {
            Authentication authUser =
                    basicAuthentication.authenticate(
                            new UsernamePasswordAuthenticationToken(dtoIn.getId(), dtoIn.getPassword()));
            resolveAuthCookie(response, authUser.getName());
        } else {
            Users newUser = new Users();
            newUser.setId(dtoIn.getId());
            newUser.setPassword(cryptPasswordEncoder.encode(dtoIn.getPassword()));
            userRepository.save(newUser);

            Authentication authUser =
                    basicAuthentication.authenticate(
                            new UsernamePasswordAuthenticationToken(dtoIn.getId(), dtoIn.getPassword()));
            resolveAuthCookie(response, authUser.getName());
        }
    }

    private void resolveAuthCookie(HttpServletResponse response, String token) {
        response.addCookie(createAuthCookie(token));
    }

    private Cookie createAuthCookie(String token) {
        Cookie cookie = new Cookie("AUTHORIZED", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        return cookie;
    }
}
