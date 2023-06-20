package com.piphub.springjwtauthentication.security.services;

import com.piphub.springjwtauthentication.constants.Constants;
import com.piphub.springjwtauthentication.models.User;
import com.piphub.springjwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        Optional<User> userName = userRepository.findByUsernameAndStatus(username, Constants.STATUS_ACTIVE);
        if (!userName.isPresent()) {
            user = userRepository.findByPhoneNumberAndStatus(username, Constants.STATUS_ACTIVE)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone: " + username));
        } else {
            user = userName.get();
        }

        return UserDetailsImpl.build(user);
    }

}
