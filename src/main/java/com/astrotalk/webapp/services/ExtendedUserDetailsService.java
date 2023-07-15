package com.astrotalk.webapp.services;

import com.astrotalk.webapp.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExtendedUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = staffRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("No such user"));
        System.out.println("****->"+userDetails.toString());
        return userDetails;
    }
}
