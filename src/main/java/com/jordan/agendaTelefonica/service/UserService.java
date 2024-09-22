package com.jordan.agendaTelefonica.service;

import com.jordan.agendaTelefonica.domain.user.User;
import com.jordan.agendaTelefonica.dto.UserRegistrationDto;
import com.jordan.agendaTelefonica.infra.excepition.ValidationExcepition;
import com.jordan.agendaTelefonica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return user;
    }


    public ResponseEntity<User> registerUser(UserRegistrationDto dto) {
        if (userRepository.existsByLogin(dto.login())) {
            throw new ValidationExcepition("Usuário já existe com esse login");
        }

        User user = new User();
        user.setName(dto.name());
        user.setLogin(dto.login());
        user.setPassword(passwordEncoder.encode(dto.password()));

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
