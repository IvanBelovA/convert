package ru.belov.currency_converter.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.belov.currency_converter.entity.Users;
import ru.belov.currency_converter.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TokenAuthentication implements AuthenticationManager {

    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        Optional<Users> user = userRepository.findById(principal);

        String token = null;
        if (user.isPresent()) {
            token = user.get().getId();
        }

        if (token == null) {
            throw new BadCredentialsException("User undefined");
        }
        return new UsernamePasswordAuthenticationToken(user, token);
    }
}
