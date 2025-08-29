package com.example.demo.adaptor;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;

public class UserAdaptor {

    public static UserDTO toUserDTO(User user)
    {
        return UserDTO.builder().userHandle(user.getUserHandle())
                .followerCount(user.getFollowerCount())
                .followingCount(user.getFollowingCount())
                .noOfPosts(user.getNoOfPosts())
                .build();
    }

    public static User toUser(UserDTO userDTO)
    {
        return User.builder().userHandle(userDTO.getUserHandle())
                .followerCount(userDTO.getFollowerCount())
                .followingCount(userDTO.getFollowingCount())
                .noOfPosts(userDTO.getNoOfPosts())
                .build();
    }
}
