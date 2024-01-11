package org.jundragon.blogsearcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jundragon.blogsearcher.core.blog.application.port.output.event.KeywordCountEvent;
import org.jundragon.blogsearcher.core.blog.application.port.input.dto.IncreaseKeywordCountCommand;
import org.jundragon.blogsearcher.core.blog.application.service.BlogStatisticCommandService;
import org.jundragon.blogsearcher.tokenizer.KeywordTokenizer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordCountConsumeService {

    private final ObjectMapper objectMapper;
    private final BlogStatisticCommandService blogStatisticCommandService;
    private final KeywordTokenizer tokenizer;

    @SneakyThrows
    @KafkaListener(topics = {"keywords"}, groupId = "blogs")
    public void consumerMessage(String message) {
        KeywordCountEvent event = objectMapper.readValue(message, new TypeReference<>() {
        });

        if (Objects.isNull(event.keyword()) || event.keyword().isEmpty()) {
            log.debug("처리할 이벤트가 없음 : {}", message);
            return;
        }

        // 키워드는 토큰화 하여 저장하여 수집한다.
        List<String> keywordTokens = tokenizer.tokenize(event.keyword());
        for (String token : keywordTokens) {
            blogStatisticCommandService.increaseKeywordCount(
                IncreaseKeywordCountCommand.builder()
                    .keyword(token).build()
            );
        }
    }
}
