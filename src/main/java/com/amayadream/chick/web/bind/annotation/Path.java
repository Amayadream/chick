package com.amayadream.chick.web.bind.annotation;

import java.lang.annotation.*;

/**
 * @author : Amayadream
 * @date :   2017-09-15 17:46
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Path {

    String name() default "";

    String[] value() default {};

    RequestMethod[] method() default {};

    String[] params() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};

}
