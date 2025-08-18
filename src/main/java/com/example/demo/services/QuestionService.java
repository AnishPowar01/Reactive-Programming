package com.example.demo.services;

import com.example.demo.adaptor.QuestionAdaptor;
import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Question;
import com.example.demo.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = Question.builder().title(questionRequestDTO.getTitle()).
                content(questionRequestDTO.getContent()).
                createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

        return questionRepository.save(question).
                map(QuestionAdaptor::toQuestionResponseDto).doOnSuccess(response -> System.out.println("Quwartion os ready"))
                .doOnError(error -> System.out.println("Erroer" + error));
    }
}
