package com.example.demo.dto;

import java.time.LocalDateTime;

public record CursorDTO(LocalDateTime createdAt, String questionId) {
}
