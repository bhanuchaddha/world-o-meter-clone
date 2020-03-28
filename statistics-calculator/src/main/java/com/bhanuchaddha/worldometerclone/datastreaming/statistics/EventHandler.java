package com.bhanuchaddha.worldometerclone.datastreaming.statistics;

import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface EventHandler {

    void handle( Event event);

    EventType handles();

}
