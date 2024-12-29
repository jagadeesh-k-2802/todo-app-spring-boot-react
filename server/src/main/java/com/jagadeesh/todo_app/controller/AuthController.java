package com.jagadeesh.todo_app.controller;

import com.jagadeesh.todo_app.payload.request.AuthRequest;
import com.jagadeesh.todo_app.entity.User;
import com.jagadeesh.todo_app.payload.response.MessageResponse;
import com.jagadeesh.todo_app.service.AuthService;
import com.jagadeesh.todo_app.service.UserService;
import com.jagadeesh.todo_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<MessageResponse<String>> login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return ResponseEntity.ok(new MessageResponse<>(true, jwtUtil.generateToken(userDetails)));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse<String>> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse<>(false, "Username already exists"));
        }

        userService.saveUser(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return ResponseEntity.ok(new MessageResponse<>(true, jwtUtil.generateToken(userDetails)));
    }

    @GetMapping("/me")
    public ResponseEntity<MessageResponse<User>> me() {
        User user = authService.getAuthenticatedUser();
        user.setPassword(null);
        return ResponseEntity.ok(new MessageResponse<>(true, user));
    }
}
