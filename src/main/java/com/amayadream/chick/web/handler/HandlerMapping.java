package com.amayadream.chick.web.handler;

import com.amayadream.chick.web.bind.annotation.RequestMethod;
import com.amayadream.chick.web.mapping.HandlerInfo;

/**
 * @author : Amayadream
 * @date :   2017-09-18 16:25
 */
public interface HandlerMapping {

    HandlerInfo getHandlerInfo(RequestMethod requestMethod, String requestPath);

}
