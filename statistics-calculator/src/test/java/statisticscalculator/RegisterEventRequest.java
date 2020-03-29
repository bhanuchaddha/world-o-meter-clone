package statisticscalculator;

import com.bhanuchaddha.worldometerclone.datastreaming.model.EventType;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder @NoArgsConstructor
public class RegisterEventRequest {
    private String country;
    private String city;
    private LocalDateTime dateTime;
    private EventType eventType;
    private int count;
}
