package com.bhanuchaddha.worldometerclone.datastreaming.statistics;

import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EventHandlerFacade implements EventHandler {

    private final Map<EventType, EventHandler> eventHandlers;

    @Autowired
    public EventHandlerFacade(List<EventHandler> eventHandlerList) {
        this.eventHandlers = eventHandlerList.stream()
                .collect(Collectors.toMap(EventHandler::handles, e -> e));
    }

    @Override
    public void handle(Event event) {
        eventHandlers.get(event.getEventType()).handle(event);
    }

    @Override
    public EventType handles() {
        return null;
    }
}
