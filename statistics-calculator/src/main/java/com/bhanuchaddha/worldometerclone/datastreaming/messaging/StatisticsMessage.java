package com.bhanuchaddha.worldometerclone.datastreaming.messaging;

import lombok.*;

@NoArgsConstructor @Getter @Setter
@AllArgsConstructor
@Builder
public class StatisticsMessage {
    private String key;
    private Integer value;
}
