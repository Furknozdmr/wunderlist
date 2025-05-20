package com.furkanozdemir.config;



import com.furkanozdemir.adapter.user.entity.Users;
import com.furkanozdemir.adapter.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = authentication.getName();
        String password = authentication.getCredentials().toString();
        Users users = userRepository.findByEmail(userEmail).orElseThrow(() -> new BadCredentialsException("User not found.."));
        if (passwordEncoder.matches(password, users.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userEmail, password, null);
        }
        throw new BadCredentialsException("Invalid password..");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
