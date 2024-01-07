package org.jundragon.blogsearcher.persistence.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jundragon.blogsearcher.core.blog.application.event.BlogStatisticEvent;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordEventPublisher;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreamBridgeKeywordEventPublisher implements KeywordEventPublisher {

    public static final String BIND = "keywords-out-0";
    private final ObjectMapper objectMapper;
    private final StreamBridge streamBridge;

    @SneakyThrows
    @Override
    public void publish(BlogStatisticEvent blogStatisticEvent) {
        String message = objectMapper.writeValueAsString(blogStatisticEvent);
        streamBridge.send(BIND, message);
    }
}
