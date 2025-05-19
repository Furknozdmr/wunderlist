package com.furkanozdemir.adapter.authorization.model;


import com.furkanozdemir.user.model.UserDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class UserAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

    @Serial
    private static final long serialVersionUID = -8304788705860424214L;

    private final Object principal;

    private Object credentials;

    @Getter
    private final String userId;

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final String email;

    public UserAuthenticationToken(Object principal, Object credentials, UserDto userDto) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.userId = userDto.userId();
        this.firstName = userDto.firstName();
        this.lastName = userDto.lastName();
        this.email = userDto.email();
        setAuthenticated(false);
    }

    public UserAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UserDto userDto) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.userId = userDto.userId();
        this.firstName = userDto.firstName();
        this.lastName = userDto.lastName();
        this.email = userDto.email();
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

}
