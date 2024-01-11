package org.jundragon.blogsearcher.core.blog.application.port.output.event;

public interface KeywordEventPublisher {

    void publish(BlogStatisticEvent blogStatisticEvent);
}
