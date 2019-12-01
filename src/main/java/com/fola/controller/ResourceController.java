package com.fola.controller;

import com.fola.dto.AuthenticationRequest;
import com.fola.dto.AuthenticationResponse;
import com.fola.model.Resource;
import com.fola.security.AppUserDetailsService;
import com.fola.security.JWTUtil;
import com.fola.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {

    private final ResourceService resourceService;

    private final AuthenticationManager authenticationManager;

    private final AppUserDetailsService userDetailsService;

    private final JWTUtil jwtTokenUtil;

    public ResourceController(ResourceService resourceService, AuthenticationManager authenticationManager, AppUserDetailsService userDetailsService, JWTUtil jwtTokenUtil) {
        this.resourceService = resourceService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/find")
    public List<Resource> findAll() {
        return resourceService.findAll();
    }

    @PostMapping("/saveResources")
    public List<Resource> save(@RequestBody List<Resource> resources) {
        return resourceService.saveAll(resources);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse((jwt)));
    }
}
