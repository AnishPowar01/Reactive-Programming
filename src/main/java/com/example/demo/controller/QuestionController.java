package com.example.demo.controller;

import com.example.demo.adaptor.QuestionAdaptor;
import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Question;
import com.example.demo.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService service;

    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO)
    {
        return service.createQuestion(questionRequestDTO).doOnSuccess(response -> System.out.println("Quwartion os ready"))
                .doOnError(error -> System.out.println("Error" + error));
    }


    @GetMapping
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id)
    {
        return null;
    }

    @GetMapping
    public Flux<QuestionResponseDTO> getAllQuestions()
    {
        return null;
    }

    @DeleteMapping
    public Mono<QuestionResponseDTO> deleteQuestionById()
    {
        return null;
    }

    @GetMapping
    public Flux<QuestionResponseDTO> searchQuestions(RequestParam String , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        return null;
    }

}
