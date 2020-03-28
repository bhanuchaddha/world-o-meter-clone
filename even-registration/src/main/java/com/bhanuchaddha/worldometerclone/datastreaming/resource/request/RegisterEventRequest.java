package com.bhanuchaddha.worldometerclone.datastreaming.resource.request;

import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class RegisterEventRequest {
    private String country;
    private String city;
    private LocalDateTime dateTime;
    private EventType eventType;
    private int count;
}
