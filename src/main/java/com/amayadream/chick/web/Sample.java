package com.amayadream.chick.web;

import com.amayadream.chick.ioc.annotation.Component;

/**
 * @author :  Amayadream
 * @date :  2017.09.27 22:20
 */
@Component
public class Sample {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
