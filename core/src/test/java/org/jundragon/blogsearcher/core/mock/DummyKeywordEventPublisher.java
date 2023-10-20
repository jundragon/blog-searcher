package org.jundragon.blogsearcher.core.mock;

import org.jundragon.blogsearcher.core.blog.application.event.BlogStatisticEvent;
import org.jundragon.blogsearcher.core.blog.application.event.KeywordEventPublisher;

public class DummyKeywordEventPublisher implements KeywordEventPublisher {

    @Override
    public void publish(BlogStatisticEvent blogStatisticEvent) {
        // do nothing
    }
}
