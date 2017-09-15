package com.amayadream.chick.web.bind.annotation;

import java.lang.annotation.*;

/**
 * @author : Amayadream
 * @date :   2017-09-15 17:45
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
