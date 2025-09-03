package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.models.User;
import com.example.demo.services.FeedService;
import com.example.demo.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private  FeedService feedService;

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

    @PostMapping("/follow")
    public Mono<Void> follow(@RequestBody FollowRequestDTO followRequestDTO)
    {
        return userService.follow(followRequestDTO);
    }

    @PostMapping("/unfollow")
    public Mono<Void> unFollow(@RequestBody FollowRequestDTO followRequestDTO)
    {
        return userService.unFollow(followRequestDTO);
    }

    @GetMapping("/feed/{userId}")
    public Mono<FeedPageDto> getFeed(@PathVariable String userId,
                                     @RequestParam(required = false) String cursor)
    {
        return feedService.generateFeedFromDB(userId,cursor);
    }


}
