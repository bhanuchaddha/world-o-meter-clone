package com.bhanuchaddha.worldometerclone.datastreaming.service.model;

import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EventRegistrationRequest {
    private Event event;
}
