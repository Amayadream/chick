package com.amayadream.chick.web.handler;

import java.lang.reflect.Method;

/**
 * @author : Amayadream
 * @date :   2017-09-19 16:06
 */
public class ConverterHandler {

    private Class clazz;
    private Method method;
    private Object instance;

    public ConverterHandler(Class clazz, Method method) throws IllegalAccessException, InstantiationException {
        this.clazz = clazz;
        this.method = method;
        this.instance = clazz.newInstance();
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
