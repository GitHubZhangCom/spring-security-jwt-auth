package com.jwt.server.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jwt.server.exception.UserAuthException;

@Component
public class ADAuthenticationProvider
        implements AuthenticationProvider {

    
    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
        	throw new UserAuthException("用户名或密码不能为空");
        }
        if (/*userService.get(username) != null &&*/ (username.equals("zhang")&& password.equals("zhang")) ) {
        	return new UsernamePasswordAuthenticationToken(username, password, AUTHORITIES);
        } else {
        	throw new UserAuthException("登录失败，用户名或密码有误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

}
