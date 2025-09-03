package com.example.demo.adaptor;

import com.example.demo.dto.FeedItemDTO;
import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Question;

public class QuestionAdaptor {

    public static QuestionResponseDTO toQuestionResponseDto(Question question)
    {
        return QuestionResponseDTO.builder().id(question.getId()).
                title(question.getTitle()).content(question.getContent()).createdAt(question.getCreatedAt()).build();
    }


    public static FeedItemDTO fromQuestionResponseDtoToFeedItemDto(Question questionResponseDTO) {
        System.out.println("Adapting QuestionResponseDTO to FeedItemDto: " + questionResponseDTO);
        return FeedItemDTO.builder()
                .questionId(questionResponseDTO.getId())
                .authorId(questionResponseDTO.getAuthorId())
                .content(questionResponseDTO.getContent())
                .createdAt(questionResponseDTO.getCreatedAt())
                .build();
    }
}
