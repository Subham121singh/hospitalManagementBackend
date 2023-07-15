package com.astrotalk.webapp.controllers;


import com.astrotalk.webapp.entities.AuthRequest;
import com.astrotalk.webapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenService;

    @PostMapping("/authenticate")
    public ResponseEntity login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            Map<String, String> mp = new HashMap<>();
            String authorities = authentication.getAuthorities().stream().
                    map(auth->(auth.getAuthority())).collect(Collectors.joining(","));

            ;
            mp.put("jwt", jwtTokenService.generateToken(authRequest.getUsername()));
            return ResponseEntity.ok(mp);
        } else {
            return ResponseEntity.status(401).body("Invalid Username or Password");
        }
    }
}