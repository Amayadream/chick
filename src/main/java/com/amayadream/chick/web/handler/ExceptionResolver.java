package com.amayadream.chick.web.handler;

import com.amayadream.chick.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理器
 * @author :  Amayadream
 * @date :  2017.09.25 21:38
 */
public interface ExceptionResolver {

    ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Exception e);

}
