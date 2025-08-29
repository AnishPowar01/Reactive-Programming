package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "answers")
public class Answer {
    @Id
    private String id;

    @NotBlank(message = "Content should not be blanked")
    @Size(min = 10, max = 1000)
    private String content;

    @Indexed     // indexing in mongo
    private String questionId;

    @CreatedDate
    @Indexed
    private LocalDateTime createdAt;

    private String authorId;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
