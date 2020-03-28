package com.bhanuchaddha.worldometerclone.datastreaming.messaging;

import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import com.bhanuchaddha.worldometerclone.datastreaming.statistics.EventHandlerFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RedisMessageSubscriber implements MessageListener {

    private final EventHandlerFacade eventHandlerFacade;
    private final ObjectMapper objectMapper;


    public void onMessage(final Message message, final byte[] pattern) {
        try {
            Event event = objectMapper.readValue(message.toString(), Event.class);
            eventHandlerFacade.handle(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
