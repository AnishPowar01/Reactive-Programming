package com.example.demo.controller;

import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import com.example.demo.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/create")
    public Mono<UserDTO> createUser(UserCreateDTO userCreateDTO)
    {
        return userService.createUser(userCreateDTO);
    }

    @GetMapping("/{userHandle}")
    public Mono<UserDTO> getUser(@PathVariable String userHandle)
    {
        return userService.findUser(userHandle);
    }

}
