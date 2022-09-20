package challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import challenge.security.IAuthenticationFacade;
import challenge.security.JwtUtil;
import challenge.security.SecUserDetailsService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/chalenge")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SecUserDetailsService userDetailsService;

    @Autowired
    IAuthenticationFacade authenticationFacade;
    
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/users/signIn")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}