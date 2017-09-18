package com.amayadream.chick.web.servlet;

import java.util.Map;

/**
 * @author : Amayadream
 * @date :   2017-09-18 17:41
 */
public class View {

    private String path;
    private Map<String, Object> attributes;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
