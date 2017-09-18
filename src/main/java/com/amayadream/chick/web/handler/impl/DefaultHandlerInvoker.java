package com.amayadream.chick.web.handler.impl;

import com.amayadream.chick.web.handler.HandlerInvoker;
import com.amayadream.chick.web.handler.ViewResolver;
import com.amayadream.chick.web.mapping.HandlerInfo;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Amayadream
 * @date :   2017-09-18 17:12
 */
public class DefaultHandlerInvoker implements HandlerInvoker {

    private ViewResolver viewResolver = new DefaultViewResolver();

    @Override
    public void invokeHandler(HttpServletRequest req, HttpServletResponse resp, HandlerInfo handlerInfo) throws Exception {
        Class<?> clazz = handlerInfo.getClazz();
        Method method = handlerInfo.getMethod();
        Object controller = clazz.newInstance();

        List<Object> params = createMethodParams(req, handlerInfo);
        method.setAccessible(true);
        Object result = method.invoke(controller, params.toArray());

        viewResolver.resolverView(req, resp, result);
    }

    public List<Object> createMethodParams(HttpServletRequest req, HandlerInfo handlerInfo) {
        List<Object> params = new ArrayList<>();
        Parameter[] parameters = handlerInfo.getMethod().getParameters();
        if (ArrayUtils.isNotEmpty(parameters)) {
            for (Parameter parameter : parameters) {
                params.add(req.getParameter(parameter.getDeclaringExecutable().getName()));
            }
        }
        return params;
    }


}
