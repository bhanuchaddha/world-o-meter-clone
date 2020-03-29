package statisticscalculator;

import com.bhanuchaddha.worldometerclone.datastreaming.messaging.StatisticsMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.with;

public class StatsTest {
    private static ObjectMapper objectMapper= new ObjectMapper();

    @SneakyThrows
    Response registerEvent(RegisterEventRequest request){
        RestAssured.reset();
        RestAssured.baseURI="http://127.0.0.1:8080";

        Response response = with()
                .body(objectMapper.writeValueAsString(request))
                .contentType(ContentType.JSON)
                .when()
                .request(Method.POST,"/")
                .then()
                .extract()
                .response();
        return response;
    }

    @Test
    public void testStats(){
        int cases = 100;
        Jedis jedis = new Jedis();
        jedis.subscribe(new JedisPubSub() {
            @Override
            @SneakyThrows
            public void onMessage(String channel, String message) {
                StatisticsMessage statisticsMessage = objectMapper.readValue(message.toString(), StatisticsMessage.class);
                if(statisticsMessage.getKey().equals("CORONA_WORLD_TOTAL_REGISTERED")){
                    Assert.assertEquals(cases, statisticsMessage.getValue().intValue());
                    jedis.quit();
                    System.out.println("boom");
                }
            }
        }, "statistics");
        registerEvent(RegisterEventRequest.builder()
                .city("Copemhagen")
                .country("Denmark")
                .dateTime(LocalDateTime.now())
                .count(cases)
                .build()
        );
    }
}
