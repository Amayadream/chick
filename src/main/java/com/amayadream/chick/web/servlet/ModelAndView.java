package com.amayadream.chick.web.servlet;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : Amayadream
 * @date :   2017-09-18 17:41
 */
public class ModelAndView {

    private String view;
    private Map<String, Object> model = new LinkedHashMap<>();

    public ModelAndView() {

    }

    public ModelAndView(String view) {
        this.view = view;
    }

    public ModelAndView(String view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }

    public ModelAndView addObject(String key, Object value) {
        this.getModel().put(key, value);
        return this;
    }

    public ModelAndView addAllObjects(Map<String, Object> model) {
        this.getModel().putAll(model);
        return this;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
