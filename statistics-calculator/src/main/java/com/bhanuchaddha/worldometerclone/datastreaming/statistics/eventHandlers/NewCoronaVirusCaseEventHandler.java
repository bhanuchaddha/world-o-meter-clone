package com.bhanuchaddha.worldometerclone.datastreaming.statistics.eventHandlers;

import com.bhanuchaddha.worldometerclone.datastreaming.data.StatisticsRepository;
import com.bhanuchaddha.worldometerclone.datastreaming.messaging.StatisticsPublisher;
import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import com.bhanuchaddha.worldometerclone.datastreaming.statistics.EventHandler;
import com.bhanuchaddha.worldometerclone.datastreaming.statistics.StatisticsKeyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewCoronaVirusCaseEventHandler implements EventHandler {

    private final StatisticsRepository statisticsRepository;
    private final StatisticsPublisher statisticsPublisher;

    private final EventType eventType = EventType.NEW_CORONA_VIRUS_CASES;

    @Override
    public void handle(Event event) {
        StatisticsKeyFactory kf = new StatisticsKeyFactory(event.getCountry(), event.getCity());

        statisticsRepository.increment(kf.getWorldTotalRegisteredCaseKey(), event.getCount());
        statisticsRepository.increment(kf.getWorldTotalActiveKey(), event.getCount());

        statisticsRepository.increment(kf.getCountryTotalRegisteredCaseKey(), event.getCount());
        statisticsRepository.increment(kf.getCountryTotalActiveKey(), event.getCount());

        statisticsRepository.increment(kf.getCityTotalRegisteredCaseKey(), event.getCount());
        statisticsRepository.increment(kf.getCityTotalActiveKey(), event.getCount());

        statisticsPublisher.publish(kf.getWorldTotalRegisteredCaseKey());
        statisticsPublisher.publish(kf.getWorldTotalActiveKey());
        statisticsPublisher.publish(kf.getCountryTotalRegisteredCaseKey());
        statisticsPublisher.publish(kf.getCountryTotalActiveKey());
        statisticsPublisher.publish(kf.getCityTotalRegisteredCaseKey());
        statisticsPublisher.publish(kf.getCityTotalActiveKey());
    }

    @Override
    public EventType handles() {
        return eventType;
    }
}
