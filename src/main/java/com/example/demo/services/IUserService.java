package com.example.demo.services;

import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<UserDTO> createUser(UserCreateDTO userCreateDTO);
    Mono<UserDTO> findUser(String userHandle);
}
