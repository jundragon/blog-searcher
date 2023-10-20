package org.jundragon.blogsearcher.core.blog.application.event;

public interface KeywordEventPublisher {

    void publish(BlogStatisticEvent blogStatisticEvent);
}
