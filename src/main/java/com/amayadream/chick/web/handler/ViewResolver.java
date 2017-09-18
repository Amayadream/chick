package com.amayadream.chick.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Amayadream
 * @date :   2017-09-18 17:05
 */
public interface ViewResolver {

    void resolverView(HttpServletRequest req, HttpServletResponse resp, Object result);

}
