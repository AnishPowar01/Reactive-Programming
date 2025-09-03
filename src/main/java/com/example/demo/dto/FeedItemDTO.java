package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedItemDTO {

    private String questionId;
    private String authorId;
    private String content;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FeedItemDTO) obj;
        return Objects.equals(this.questionId, that.questionId)&&
                Objects.equals(this.authorId, that.authorId) &&
                Objects.equals(this.content, that.content) &&
                Objects.equals(this.createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, authorId, content, createdAt);
    }
}
