package com.example.demo.adaptor;

import com.example.demo.dto.FollowRequestDTO;
import com.example.demo.models.Follows;

import java.time.LocalDateTime;

public class FollowAdaptor {

    public static Follows toFollow(FollowRequestDTO followRequestDTO)
    {
        return Follows.builder().followerId(followRequestDTO.getFollowerId())
                .followeeId(followRequestDTO.getFolloweeId())
                .createdAt(LocalDateTime.now()).build();
    }

}
