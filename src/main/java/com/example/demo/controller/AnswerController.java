package com.example.demo.controller;

import com.example.demo.dto.AnswerRequestDTO;
import com.example.demo.dto.AnswerResponseDTO;
import com.example.demo.services.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ans")

public class AnswerController {

    @Autowired
    private IAnswerService answerService;

    @PostMapping("/reply")
    public Mono<AnswerResponseDTO> addAnswer(@RequestBody AnswerRequestDTO requestDTO)
    {
        return answerService.addAnswer(requestDTO).doOnError(err -> System.out.println("Got some error"))
                .doOnSuccess(response -> System.out.println("Successfully added the ans"));
    }


    @GetMapping("/details/{questionId}")
    public Flux<AnswerResponseDTO> getAllAnswers(@PathVariable String questionId)
    {
        return answerService.getAnswersByQuestionId(questionId);
    }

}
