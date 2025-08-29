package com.example.demo.services;

import com.example.demo.adaptor.FollowAdaptor;
import com.example.demo.adaptor.UserAdaptor;
import com.example.demo.dto.FollowRequestDTO;
import com.example.demo.dto.UserCreateDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.Follows;
import com.example.demo.models.User;
import com.example.demo.repositories.FollowRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;

    private final FollowRepository followRepository;

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

    @Override
    public Mono<Void> follow(FollowRequestDTO req) {

        if(req.getFollowerId().equals(req.getFolloweeId())) return Mono.empty();

        return followRepository.findByFollowerIdAndFolloweeId(req.getFollowerId(), req.getFolloweeId()).
                flatMap(edge -> Mono.empty())
                .switchIfEmpty(
                        Mono.defer(() -> {

                            Follows follows = FollowAdaptor.toFollow(req);

                            Mono<Void> saveFollows = followRepository.save(follows).then();

                            Mono<Void> updateFollowee = userRepository.findById(req.getFolloweeId())
                                    .flatMap(user -> {
                                        user.setFollowerCount(user.getFollowerCount()  +1);
                                        return userRepository.save(user);
                                    }).then();

                            Mono<Void> updateFollower = userRepository.findById(req.getFollowerId())
                                    .flatMap(user -> {
                                        user.setFollowingCount(user.getFollowingCount()  +1);
                                        return userRepository.save(user);
                                    }).then();

                            return saveFollows.then(updateFollowee).then(updateFollower);
                        })
                ).then();
    }
}
