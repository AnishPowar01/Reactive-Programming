package com.example.demo.services;

import com.example.demo.dto.AnswerRequestDTO;
import com.example.demo.dto.AnswerResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAnswerService {

    Mono<AnswerResponseDTO> addAnswer(AnswerRequestDTO requestDTO);
    Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId);
}
