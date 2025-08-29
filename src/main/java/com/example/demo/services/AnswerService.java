package com.example.demo.services;

import com.example.demo.adaptor.AnswerAdaptor;
import com.example.demo.dto.AnswerRequestDTO;
import com.example.demo.dto.AnswerResponseDTO;
import com.example.demo.models.Answer;
import com.example.demo.repositories.AnswerRepository;
import com.example.demo.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService{

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    @Override
    public Mono<AnswerResponseDTO> addAnswer(AnswerRequestDTO requestDTO) {

        return questionRepository.findById(requestDTO.getQuestionId())
                .switchIfEmpty(Mono.error(new RuntimeException("question not found")))
                .flatMap(question -> {
                    Answer answer = AnswerAdaptor.toAnswer(requestDTO);
                    answer.setCreatedAt(LocalDateTime.now());
                    answer.setUpdatedAt(LocalDateTime.now());
                    return answerRepository.save(answer);
                }).map(AnswerAdaptor::toAnswerResponseDTO);
    }

    @Override
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId) {
        return questionRepository.findById(questionId)
                .switchIfEmpty(Mono.error(new RuntimeException("question not found")))
                .flatMapMany(question -> {

                    return answerRepository.findByQuestionId(questionId).map(AnswerAdaptor::toAnswerResponseDTO);
                });
    }
}
