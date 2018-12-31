package io.dreamstudio.architecture.user.annotation;

import java.lang.annotation.*;

/**
 * @author Ricky Fung
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OptionalAuth {

}
