package com.amayadream.chick.web.mapping;

import com.amayadream.chick.web.bind.annotation.RequestMethod;

/**
 * @author : Amayadream
 * @date :   2017-09-18 15:00
 */
public class RequestInfo {

    private RequestMethod[] method;
    private String path;

    public RequestInfo(RequestMethod[] method, String path) {
        this.method = method;
        this.path = path;
    }

    public RequestMethod[] getMethod() {
        return method;
    }

    public void setMethod(RequestMethod[] method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
