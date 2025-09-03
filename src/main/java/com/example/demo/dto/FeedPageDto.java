package com.example.demo.dto;

import java.util.List;

public record FeedPageDto(
        List<FeedItemDTO> item,
        String nextCursor
) {
}
