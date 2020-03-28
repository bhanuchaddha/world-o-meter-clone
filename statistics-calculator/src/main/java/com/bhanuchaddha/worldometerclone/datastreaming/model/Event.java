package com.bhanuchaddha.worldometerclone.datastreaming.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor @Setter @Getter
public class Event {
    private String country;
    private String city;
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime dateTime;
    private EventType eventType;
    private int count;
}
