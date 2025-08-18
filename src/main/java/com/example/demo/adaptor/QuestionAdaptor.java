package com.example.demo.adaptor;

import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Question;

public class QuestionAdaptor {

    public static QuestionResponseDTO toQuestionResponseDto(Question question)
    {
        return QuestionResponseDTO.builder().id(question.getId()).
                title(question.getTitle()).content(question.getContent()).createdAt(question.getCreatedAt()).build();
    }
}
