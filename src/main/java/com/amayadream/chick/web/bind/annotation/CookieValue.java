package com.amayadream.chick.web.bind.annotation;

import java.lang.annotation.*;

/**
 * @author : Amayadream
 * @date :   2017-09-22 16:06
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CookieValue {

    String name() default "";

    boolean required() default true;

    String defaultValue() default "";

}
