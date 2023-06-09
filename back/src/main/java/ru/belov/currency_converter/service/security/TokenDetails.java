package ru.belov.currency_converter.service.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Objects;

public class TokenDetails extends AbstractAuthenticationToken {

    private String token;

    public TokenDetails(String token) {
        super(null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TokenDetails that = (TokenDetails) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), token);
    }
}
