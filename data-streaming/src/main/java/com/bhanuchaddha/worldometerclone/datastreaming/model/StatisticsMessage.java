package com.bhanuchaddha.worldometerclone.datastreaming.model;

import lombok.*;

@NoArgsConstructor @Getter @Setter
@Builder @AllArgsConstructor
public class StatisticsMessage {
    private String key;
    private Integer value;
}
