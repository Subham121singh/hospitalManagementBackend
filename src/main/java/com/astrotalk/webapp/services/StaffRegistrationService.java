package com.astrotalk.webapp.services;


import com.astrotalk.webapp.entities.Staff;
import com.astrotalk.webapp.entities.StaffRegistrationRequest;
import com.astrotalk.webapp.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffRegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StaffRepository staffRepository;

    public void registerStaff(StaffRegistrationRequest staffRegistrationRequest){
        Staff userDetails = new Staff();
        // safecheck to see if the username is already taken
        Optional<Staff> mayBeUser = staffRepository.findByUsername(staffRegistrationRequest.getUsername());
        if(mayBeUser.isPresent()){
            throw new RuntimeException("A user with these credentials already exists");
        }
        userDetails.setUsername(staffRegistrationRequest.getUsername());
        userDetails.setPassword(passwordEncoder.encode(staffRegistrationRequest.getPassword()));
        userDetails.setEmail(staffRegistrationRequest.getEmail());
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setEnabled(true);
        userDetails.setCredentialNonExpired(true);


        staffRepository.save(userDetails);

    }

}
