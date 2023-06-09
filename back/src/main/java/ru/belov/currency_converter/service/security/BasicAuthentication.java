package ru.belov.currency_converter.service.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BasicAuthentication implements AuthenticationManager {

    private final UserDetailsServiceImpl userDetailsService;

    private final BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        if (userDetails == null) {
            throw new BadCredentialsException("User details in null");
        }

        if (cryptPasswordEncoder.matches(authentication.getCredentials().toString(),userDetails.getPassword())) {
            String token = userDetails.getUsername();
            return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
        } else {
            throw new NullPointerException("password incorrect");
        }
    }
}
