package com.jordan.agendaTelefonica.controller;

import com.jordan.agendaTelefonica.dto.AutheticationDto;
import com.jordan.agendaTelefonica.domain.user.User;
import com.jordan.agendaTelefonica.dto.UserRegistrationDto;
import com.jordan.agendaTelefonica.infra.excepition.ValidationExcepition;
import com.jordan.agendaTelefonica.infra.security.DataTokenJWT;
import com.jordan.agendaTelefonica.infra.security.TokenService;
import com.jordan.agendaTelefonica.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDto dto) {
        try {
            userService.registerUser(dto);
            System.out.println(dto);
            return ResponseEntity.ok("Usuário registrado com sucesso");
        } catch (ValidationExcepition e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> LogIn(@RequestBody @Valid AutheticationDto data) {
        System.out.println("Login: " + data.login() + ", Password: " + data.password());
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(new DataTokenJWT(tokenJwt));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
