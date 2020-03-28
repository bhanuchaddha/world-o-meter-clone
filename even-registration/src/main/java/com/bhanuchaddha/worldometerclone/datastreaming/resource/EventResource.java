package com.bhanuchaddha.worldometerclone.datastreaming.resource;

import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import com.bhanuchaddha.worldometerclone.datastreaming.resource.dto.EventDto;
import com.bhanuchaddha.worldometerclone.datastreaming.resource.request.RegisterEventRequest;
import com.bhanuchaddha.worldometerclone.datastreaming.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@Slf4j
public class EventResource {

    private final EventService eventService;

    @PostMapping
    public Mono<EventDto> registerEvent(@RequestBody RegisterEventRequest request){
        return eventService.registerAndPublish(Event.builder()
                .country(request.getCountry())
                .city(request.getCity())
                .dateTime(request.getDateTime())
                .eventType(request.getEventType())
                .count(request.getCount())
                .build())
                .map(Event::toEventDto);
    }
    @GetMapping
    public Flux<EventDto> getEvents(){
        return eventService.getEvents()
                .map(Event::toEventDto);
    }
}
