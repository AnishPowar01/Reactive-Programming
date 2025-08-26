package com.example.demo.repositories;

import com.example.demo.models.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepositoy extends ReactiveMongoRepository<Answer, String> {
}
