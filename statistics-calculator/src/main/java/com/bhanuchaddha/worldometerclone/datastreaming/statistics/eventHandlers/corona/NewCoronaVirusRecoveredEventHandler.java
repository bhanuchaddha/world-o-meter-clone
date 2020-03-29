package com.bhanuchaddha.worldometerclone.datastreaming.statistics.eventHandlers.corona;

import com.bhanuchaddha.worldometerclone.datastreaming.data.StatisticsRepository;
import com.bhanuchaddha.worldometerclone.datastreaming.messaging.StatisticsPublisher;
import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import com.bhanuchaddha.worldometerclone.datastreaming.statistics.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewCoronaVirusRecoveredEventHandler implements EventHandler {

    private final EventType eventType = EventType.NEW_CORONA_VIRUS_RECOVERED;

    private final StatisticsRepository statisticsRepository;
    private final StatisticsPublisher statisticsPublisher;

    @Override
    public void handle(Event event) {
        CoronaStatisticsKeyFactory kf = new CoronaStatisticsKeyFactory(event.getCountry(), event.getCity());

        statisticsRepository.increment(kf.getWorldTotalRecoveredKey(), event.getCount());
        statisticsRepository.decrement(kf.getWorldTotalActiveKey(), event.getCount());

        statisticsRepository.increment(kf.getCountryTotalRecoveredKey(), event.getCount());
        statisticsRepository.decrement(kf.getCountryTotalActiveKey(), event.getCount());

        statisticsRepository.increment(kf.getCityTotalRecoveredKey(), event.getCount());
        statisticsRepository.decrement(kf.getCityTotalActiveKey(), event.getCount());

        statisticsPublisher.publish(kf.getWorldTotalRecoveredKey());
        statisticsPublisher.publish(kf.getWorldTotalActiveKey());
        statisticsPublisher.publish(kf.getCountryTotalRecoveredKey());
        statisticsPublisher.publish(kf.getCountryTotalActiveKey());
        statisticsPublisher.publish(kf.getCityTotalRecoveredKey());
        statisticsPublisher.publish(kf.getCityTotalActiveKey());

    }

    @Override
    public EventType handles() {
        return eventType;
    }
}
