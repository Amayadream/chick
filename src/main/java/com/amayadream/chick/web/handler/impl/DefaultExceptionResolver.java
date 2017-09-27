package com.amayadream.chick.web.handler.impl;

import com.amayadream.chick.web.handler.ExceptionResolver;
import com.amayadream.chick.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author :  Amayadream
 * @date :  2017.09.25 21:40
 */
public class DefaultExceptionResolver implements ExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        //TODO 处理异常
        e.printStackTrace();
        try {
            resp.sendError(500, e.getMessage());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

}
