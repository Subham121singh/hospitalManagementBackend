package com.astrotalk.webapp.controllers;

import com.astrotalk.webapp.entities.Staff;
import com.astrotalk.webapp.entities.StaffRegistrationRequest;
import com.astrotalk.webapp.services.StaffRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffRegistration {

    @Autowired
    StaffRegistrationService staffRegistrationService;

    @PostMapping("/public/register")
    public ResponseEntity<String> createStaff(@RequestBody StaffRegistrationRequest staffRegistrationRequest)
    {
        try{
            staffRegistrationService.registerStaff(staffRegistrationRequest);
            return ResponseEntity.ok().body("Succefully Registered");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Couldn't Register Staff");
        }

    }
}
