package com.bhanuchaddha.worldometerclone.datastreaming.statistics.eventHandlers.corona;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CoronaStatisticsKeyFactory {
    private String country;
    private String city;

    public String getWorldTotalRegisteredCaseKey(){
        return createStatisticsKey("corona", "world", "total", "registered");
    }

    public String getWorldTotalDeathKey(){
        return createStatisticsKey("corona", "world", "total", "death");
    }

    public String getWorldTotalActiveKey(){
        return createStatisticsKey("corona", "world", "total", "active");
    }

    public String getWorldTotalRecoveredKey(){
        return createStatisticsKey("corona", "world", "total", "recovered");
    }


    public String getCountryTotalRegisteredCaseKey(){
        return createStatisticsKey("corona", country, "total", "registered");
    }

    public String getCountryTotalDeathKey(){
        return createStatisticsKey("corona", country, "total", "death");
    }

    public String getCountryTotalActiveKey(){
        return createStatisticsKey("corona", country, "total", "active");
    }

    public String getCountryTotalRecoveredKey(){
        return createStatisticsKey("corona", country, "total", "recovered");
    }


    public String getCityTotalRegisteredCaseKey(){
        return createStatisticsKey("corona", country, city, "total", "registered");
    }

    public String getCityTotalDeathKey(){
        return createStatisticsKey("corona", country, city, "total", "death");
    }

    public String getCityTotalActiveKey(){
        return createStatisticsKey("corona", country, city, "total", "active");
    }

    public String getCityTotalRecoveredKey(){
        return createStatisticsKey("corona", country, city, "total", "recovered");
    }


    private String createStatisticsKey(String... parts){
        return Arrays.stream(parts).map(String::toUpperCase)
                .collect(Collectors.joining("_"));

    }
}
