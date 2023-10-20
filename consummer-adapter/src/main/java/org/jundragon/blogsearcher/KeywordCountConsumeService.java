package org.jundragon.blogsearcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KeywordCountConsumeService {

    @KafkaListener(topics = {"keywords"}, groupId = "blogs")
    public void consumerMessage(String message) {
        log.info("Received Message : {}", message);
    }
}
