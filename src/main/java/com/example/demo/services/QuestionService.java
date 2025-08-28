package com.example.demo.services;

import com.example.demo.adaptor.QuestionAdaptor;
import com.example.demo.dto.QuestionRequestDTO;
import com.example.demo.dto.QuestionResponseDTO;
import com.example.demo.events.ViewCountEvent;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;
import com.example.demo.producers.KafkaEventProducer;
import com.example.demo.repositories.QuestionDocumentRepository;
import com.example.demo.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;

    private final KafkaEventProducer kafkaEventProducer;

    private final IQuestionIndexService indexService;

    private final QuestionDocumentRepository documentRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = Question.builder().title(questionRequestDTO.getTitle()).
                content(questionRequestDTO.getContent()).
                createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

        return questionRepository.save(question).
                map(savedQuestion -> {
                    indexService.createQuestionIndex(savedQuestion);
                    return QuestionAdaptor.toQuestionResponseDto(savedQuestion);
                }).
//                map(QuestionAdaptor::toQuestionResponseDto).
                doOnSuccess(response -> System.out.println("Quwartion os ready"))
                .doOnError(error -> System.out.println("Error" + error));
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id).map(QuestionAdaptor::toQuestionResponseDto).
                doOnSuccess(response -> {
                    System.out.println("Question fetched Successfully");

                    ViewCountEvent viewCountEvent = new ViewCountEvent(id,"question", LocalDateTime.now());
                    kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                }).
                doOnError(error -> System.out.println("Error" + error));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions() {
        return questionRepository.findAll().map(QuestionAdaptor::toQuestionResponseDto);
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int page, int size) {
        return questionRepository.findByTitleOrContentIgnoreCase(searchTerm, PageRequest.of(page,size))
                .map(QuestionAdaptor::toQuestionResponseDto).
                doOnError(error -> System.out.println("Error Searching Operation" + error)).
                doOnComplete(() -> System.out.println("Question Searched completed"));
    }

    @Override
    public List<QuestionElasticDocument> searchQuestionByElasticSearch(String query) {
        return documentRepository.findByTitleContainingOrContentContaining(query,query);
    }
}
