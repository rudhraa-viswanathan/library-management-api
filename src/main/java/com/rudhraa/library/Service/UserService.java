package com.rudhraa.library.Service;

import com.rudhraa.library.DTO.UserRequestDTO;
import com.rudhraa.library.Exception.ResourceAlreadyExistsException;
import com.rudhraa.library.Model.User;
import com.rudhraa.library.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserRequestDTO request) {


        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "Username already exists"
            );
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }
}