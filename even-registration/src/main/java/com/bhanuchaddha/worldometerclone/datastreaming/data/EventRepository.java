package com.bhanuchaddha.worldometerclone.datastreaming.data;

import com.bhanuchaddha.worldometerclone.datastreaming.data.entity.EventEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EventRepository extends ReactiveMongoRepository<EventEntity, String> {

}
