package com.bhanuchaddha.worldometerclone.datastreaming.messaging;

import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;

public interface MessagePublisher {
    void publish(Event event);
}

