package com.bhanuchaddha.worldometerclone.datastreaming.service;

import com.bhanuchaddha.worldometerclone.datastreaming.data.EventRepository;
import com.bhanuchaddha.worldometerclone.datastreaming.data.entity.EventEntity;
import com.bhanuchaddha.worldometerclone.datastreaming.messaging.RedisMessagePublisher;
import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final RedisMessagePublisher messagePublisher;


    public Mono<Event> registerAndPublish(Event event){
        return eventRepository.save(event.toEventEntity())
                .doOnSuccess(e -> messagePublisher.publish(e.toEvent()))
                .map(EventEntity::toEvent);
    }

    public Flux<Event> getEvents(){
        return eventRepository.findAll()
                .map(EventEntity::toEvent);
    }
}
