package ru.belov.currency_converter.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.belov.currency_converter.service.security.TokenAuthentication;
import ru.belov.currency_converter.service.security.TokenDetails;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenAuthentication tokenAuthentication;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (antPathMatcher.match("/api/v1/users/login", request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenCookie = null;
        if (request.getCookies() != null) {
            tokenCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> "AUTHORIZED".equals(cookie.getName()))
                    .findAny()
                    .orElse(null)
                    .getValue();
        } else {
            response.sendError(401, "Token not exist");
        }

        if(tokenCookie != null) {
            TokenDetails tokenDetails = new TokenDetails(tokenCookie);

            try {
                Authentication authentication = tokenAuthentication.authenticate(tokenDetails);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (AuthenticationException e) {
                response.sendError(403, "Token invalid");
            }

        }

    }
}
