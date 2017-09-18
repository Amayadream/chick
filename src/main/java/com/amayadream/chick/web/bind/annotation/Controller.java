package com.amayadream.chick.web.bind.annotation;

import java.lang.annotation.*;

/**
 * @author : Amayadream
 * @date :   2017-09-18 14:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {

    String value() default "";

}
