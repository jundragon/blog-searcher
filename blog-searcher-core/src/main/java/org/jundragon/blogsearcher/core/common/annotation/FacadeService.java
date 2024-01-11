package org.jundragon.blogsearcher.core.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

/**
 * 파사드 서비스는 다른 서비스에서 참조하지 않도록 한다.
 * controller 계층에서 진입하는 서비스
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface FacadeService {

    @AliasFor(annotation = Service.class)
    String value() default "";
}
