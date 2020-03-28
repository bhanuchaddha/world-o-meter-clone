package com.bhanuchaddha.worldometerclone.datastreaming.model;

import com.bhanuchaddha.worldometerclone.datastreaming.data.entity.EventEntity;
import com.bhanuchaddha.worldometerclone.datastreaming.resource.dto.EventDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Event {
    private String country;
    private String city;
    private LocalDateTime dateTime;
    private EventType eventType;
    private int count;


    public EventEntity toEventEntity() {
        return EventEntity.builder()
                .city(city)
                .country(country)
                .eventType(eventType)
                .dateTime(dateTime)
                .count(count)
                .build();
    }

    public EventDto toEventDto() {
        return EventDto.builder()
                .city(city)
                .country(country)
                .eventType(eventType)
                .dateTime(dateTime)
                .count(count)
                .build();
    }
}
