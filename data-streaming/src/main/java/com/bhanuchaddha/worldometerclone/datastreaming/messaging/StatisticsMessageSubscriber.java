package com.bhanuchaddha.worldometerclone.datastreaming.messaging;

import com.bhanuchaddha.worldometerclone.datastreaming.model.StatisticsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StatisticsMessageSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate template;


    public void onMessage(final Message message, final byte[] pattern) {
        try {
            StatisticsMessage statisticsMessage = objectMapper.readValue(message.toString(), StatisticsMessage.class);
            this.template.convertAndSend("/stats", statisticsMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
