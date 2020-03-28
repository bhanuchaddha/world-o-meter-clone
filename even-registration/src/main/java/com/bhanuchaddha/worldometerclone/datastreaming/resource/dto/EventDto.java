package com.bhanuchaddha.worldometerclone.datastreaming.resource.dto;

import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor @Getter @Setter
@Builder
public class EventDto {
    private String country;
    private String city;
    private LocalDateTime dateTime;
    private EventType eventType;
    private int count;

}
