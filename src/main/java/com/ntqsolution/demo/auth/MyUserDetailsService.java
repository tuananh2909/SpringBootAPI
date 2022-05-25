package com.ntqsolution.demo.auth;

import com.ntqsolution.demo.entity.UserAccount;
import com.ntqsolution.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.getByUsername(username);
        Objects.requireNonNull(userAccount, "User not found!!!");
        return new MyUserDetails(userAccount);
    }
}
