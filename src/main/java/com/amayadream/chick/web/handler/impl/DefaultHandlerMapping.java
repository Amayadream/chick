package com.amayadream.chick.web.handler.impl;

import com.amayadream.chick.web.bind.annotation.RequestMethod;
import com.amayadream.chick.web.handler.HandlerMapping;
import com.amayadream.chick.web.mapping.HandlerInfo;
import com.amayadream.chick.web.mapping.HandlerInitializer;
import com.amayadream.chick.web.mapping.RequestInfo;
import com.amayadream.chick.web.util.PathUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

/**
 * @author : Amayadream
 * @date :   2017-09-18 16:26
 */
public class DefaultHandlerMapping implements HandlerMapping {

    @Override
    public HandlerInfo getHandlerInfo(RequestMethod requestMethod, String requestPath) {
        HandlerInfo handlerInfo = null;
        Map<RequestInfo, HandlerInfo> handlerMap = HandlerInitializer.getHandlerMapping();
        for (Map.Entry<RequestInfo, HandlerInfo> entry : handlerMap.entrySet()) {
            RequestInfo requestInfo = entry.getKey();
            if (requestInfo.getMethod() == null) {
                break;
            }
            if (requestInfo.getPath().equals(PathUtils.pure(requestPath))) {
                if (requestInfo.getMethod().length == 0 || ArrayUtils.contains(requestInfo.getMethod(), requestMethod)) {
                    handlerInfo = entry.getValue();
                    break;
                }
            }
        }
        return handlerInfo;
    }

}
