package com.example.demo.repositories;

import com.example.demo.models.Follows;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FollowRepository extends ReactiveMongoRepository<Follows, String> {

    Mono<Follows> findByFollowerIdAndFolloweeId(String followerId, String followeeId);
    Flux<Follows> findByFollowerId(String followerId);
    Flux<Follows> findByFolloweeId(String followeeId);
}
