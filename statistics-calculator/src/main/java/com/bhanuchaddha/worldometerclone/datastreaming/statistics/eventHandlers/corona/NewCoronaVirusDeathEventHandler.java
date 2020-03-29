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
public class NewCoronaVirusDeathEventHandler implements EventHandler {

    private final StatisticsRepository statisticsRepository;
    private final StatisticsPublisher statisticsPublisher;

    private final EventType eventType = EventType.NEW_CORONA_VIRUS_DEATH;


    @Override
    public void handle(Event event) {
        CoronaStatisticsKeyFactory kf = new CoronaStatisticsKeyFactory(event.getCountry(), event.getCity());

        statisticsRepository.increment(kf.getWorldTotalDeathKey(), event.getCount());
        statisticsRepository.decrement(kf.getWorldTotalActiveKey(), event.getCount());

        statisticsRepository.increment(kf.getCountryTotalDeathKey(), event.getCount());
        statisticsRepository.decrement(kf.getCountryTotalActiveKey(), event.getCount());

        statisticsRepository.increment(kf.getCityTotalDeathKey(), event.getCount());
        statisticsRepository.decrement(kf.getCityTotalActiveKey(), event.getCount());

        statisticsPublisher.publish(kf.getWorldTotalDeathKey());
        statisticsPublisher.publish(kf.getWorldTotalActiveKey());
        statisticsPublisher.publish(kf.getCountryTotalDeathKey());
        statisticsPublisher.publish(kf.getCountryTotalActiveKey());
        statisticsPublisher.publish(kf.getCityTotalDeathKey());
        statisticsPublisher.publish(kf.getCityTotalActiveKey());

    }

    @Override
    public EventType handles() {
        return eventType;
    }
}
