package com.rudhraa.library.Controller;

import com.rudhraa.library.DTO.UserRequestDTO;
import com.rudhraa.library.Model.User;
import com.rudhraa.library.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestBody UserRequestDTO request) {

        return ResponseEntity.ok(userService.register(request));
    }
}