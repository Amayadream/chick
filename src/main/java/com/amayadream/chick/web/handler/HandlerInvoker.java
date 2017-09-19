package com.amayadream.chick.web.handler;

import com.amayadream.chick.web.mapping.HandlerInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : Amayadream
 * @date :   2017-09-18 17:05
 */
public interface HandlerInvoker {

    void invoke(HttpServletRequest req, HttpServletResponse resp, HandlerInfo handlerInfo) throws Exception;

}
