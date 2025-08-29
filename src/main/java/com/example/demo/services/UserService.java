package com.example.demo.services;

import com.example.demo.adaptor.UserAdaptor;
import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;

    @Override
    public Mono<UserDTO> createUser(UserCreateDTO userCreateDTO) {
        User user = User.builder().userHandle(userCreateDTO.getUserHandle())
                .followerCount(0).followingCount(0).noOfPosts(0).build();

        return userRepository.save(user).map(UserAdaptor::toUserDTO);
    }

    @Override
    public Mono<UserDTO> findUser(String userHandle) {

        return userRepository.findByUserHandle(userHandle).map(UserAdaptor::toUserDTO);
    }
}
