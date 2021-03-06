package com.jwt.server.service.impl;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.server.domain.Employee;

/**
 * Created with IntelliJ IDEA.
 * Date: 2017/11/16
 * Time: 15:10
 * Email: hyf_spring@163.com
 *
 * @author huyunfan
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee = new Employee();
        return new User(employee.getUsername(), employee.getPassword(), emptyList());
    }
}
