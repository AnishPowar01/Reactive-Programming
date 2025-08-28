package com.example.demo.services;

import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;
import com.example.demo.repositories.QuestionDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService {

    private final QuestionDocumentRepository repository;

    @Override
    public void createQuestionIndex(Question question) {

        QuestionElasticDocument elasticDocument = QuestionElasticDocument.builder().title(question.getTitle()).
                id(question.getId()).content(question.getContent()).build();

        repository.save(elasticDocument);
    }
}
