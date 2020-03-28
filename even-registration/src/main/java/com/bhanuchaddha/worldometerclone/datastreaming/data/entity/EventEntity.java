package com.bhanuchaddha.worldometerclone.datastreaming.data.entity;

import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Builder
@Document(collection = "EVENTS")
public class EventEntity {
    @Id
    @Builder.Default
    private String id = RandomStringUtils.randomAlphanumeric(8);
    private String country;
    private String city;
    private LocalDateTime dateTime;
    private EventType eventType;
    private int count;

    public Event toEvent(){
        return Event.builder()
                .dateTime(dateTime)
                .city(city)
                .country(country)
                .eventType(eventType)
                .count(count)
                .build();
    }
}
