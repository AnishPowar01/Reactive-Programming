package com.example.demo.services;

import com.example.demo.adaptor.QuestionAdaptor;
import com.example.demo.dto.CursorDTO;
import com.example.demo.dto.FeedPageDto;
import com.example.demo.models.Follows;
import com.example.demo.repositories.FollowRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.utils.CursorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FollowRepository followRepository;
    private final QuestionRepository questionRepository;
    private final ReactiveStringRedisTemplate redisTemplate;
    private final ReactiveListOperations<String,String> listOps;

    @Value("${app.feed.cacheSize}")
    private int cacheSize;

    @Value("${app.feed.pageSize}")
    private int pageSize;


   public Mono<FeedPageDto> generateFeedFromDB(String userId, String cursor)
    {
        CursorDTO cursorDTO = CursorUtil.decode(cursor);

        return followRepository.findByFollowerId(userId)
                .map(Follows::getFolloweeId)
                .collectList()
                .flatMapMany(authorId -> {
                    if(authorId.isEmpty()) return Flux.empty();
                    return questionRepository.findAllByAuthorIdInOrderByCreatedAtDesc(authorId);
                }).filter(question -> {
                    if(cursorDTO.questionId() == null) return true;
                    if(question.getCreatedAt().isBefore(cursorDTO.createdAt())) return true;
                    if (question.getCreatedAt().isEqual(cursorDTO.createdAt())) return question.getId().compareTo(cursorDTO.questionId()) < 0;

                    return false;
                }).map(QuestionAdaptor::fromQuestionResponseDtoToFeedItemDto)
                .take(pageSize)
                .collectList()
                .map(list -> new FeedPageDto(list,null));
    }

}
