package com.example.demo.adaptor;

import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import com.example.demo.dto.AnswerRequestDTO;
import com.example.demo.dto.AnswerResponseDTO;
import com.example.demo.models.Answer;

public class AnswerAdaptor {

    public static Answer toAnswer(AnswerRequestDTO requestDTO)
    {
        return Answer.builder().content(requestDTO.getContent())
                .authorId(requestDTO.getAuthorId())
                .questionId(requestDTO.getQuestionId()).build();

    }

    public static AnswerResponseDTO toAnswerResponseDTO(Answer answer)
    {
        return AnswerResponseDTO.builder().id(answer.getId())
                .authorId(answer.getAuthorId())
                .questionId(answer.getQuestionId())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt()).build();
    }


}
