package com.example.demo.controller;

import com.example.demo.adaptor.QuestionAdaptor;
import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;
import com.example.demo.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService service;

    @PostMapping("/create")
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO)
    {
        return service.createQuestion(questionRequestDTO).doOnSuccess(response -> System.out.println("Quwartion os ready"))
                .doOnError(error -> System.out.println("Error" + error));
    }


    @GetMapping("/fetch/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id)
    {
        return service.getQuestionById(id).doOnSuccess(response -> System.out.println("Quwartion os ready"))
                .doOnError(error -> System.out.println("Error" + error));
    }

    @GetMapping("/all")
    public Flux<QuestionResponseDTO> getAllQuestions()
    {
        return service.getAllQuestions();
    }

    @DeleteMapping("/{id}")
    public Mono<QuestionResponseDTO> deleteQuestionById()
    {
        return null;
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestions
            (@RequestParam String str , @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "10") int size)
    {
        return service.searchQuestions(str,page,size).
                doOnError(error -> System.out.println("Found Error" + error));
    }

    @GetMapping("/elasticsearch")
    public List<QuestionElasticDocument> searchQuestionByElasticSearch(@RequestParam String query)
    {
        return service.searchQuestionByElasticSearch(query);
    }


}
