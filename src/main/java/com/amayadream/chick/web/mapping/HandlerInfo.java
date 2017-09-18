package com.amayadream.chick.web.mapping;

import java.lang.reflect.Method;

/**
 * @author : Amayadream
 * @date :   2017-09-18 15:00
 */
public class HandlerInfo {

    private Class<?> clazz;
    private Method method;

    public HandlerInfo(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
