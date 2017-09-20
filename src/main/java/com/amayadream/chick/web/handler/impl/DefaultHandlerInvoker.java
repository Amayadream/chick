package com.amayadream.chick.web.handler.impl;

import com.amayadream.chick.web.handler.ConverterHelper;
import com.amayadream.chick.web.handler.HandlerInvoker;
import com.amayadream.chick.web.handler.ViewResolver;
import com.amayadream.chick.web.mapping.HandlerInfo;
import com.amayadream.chick.web.util.ClassUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Amayadream
 * @date :   2017-09-18 17:12
 */
public class DefaultHandlerInvoker implements HandlerInvoker {

    private ViewResolver viewResolver = new DefaultViewResolver();

    @Override
    public void invoke(HttpServletRequest req, HttpServletResponse resp, HandlerInfo handlerInfo) throws Exception {
        Class<?> clazz = handlerInfo.getClazz();
        Method method = handlerInfo.getMethod();
        Object controller = clazz.newInstance();

        List<Object> params = createMethodParams(req, resp, handlerInfo);
        method.setAccessible(true);
        Object result = method.invoke(controller, params.toArray());

        viewResolver.resolverView(req, resp, result);
    }

    /**
     * 参数解析器
     * @param req
     * @param resp
     * @param handlerInfo
     * @return
     */
    public List<Object> createMethodParams(HttpServletRequest req, HttpServletResponse resp, HandlerInfo handlerInfo) {
        List<Object> params = new ArrayList<>();
        String[] paramNames = ClassUtils.getMethodParamNames(handlerInfo.getMethod());
        Class<?>[] classes = handlerInfo.getMethod().getParameterTypes();
        if (ArrayUtils.isNotEmpty(paramNames)) {
            for (int i = 0; i < paramNames.length; i++) {
                if (HttpServletRequest.class.equals(classes[i])) {
                    params.add(req);
                    continue;
                }
                if (HttpServletResponse.class.equals(classes[i])) {
                    params.add(resp);
                    continue;
                }
                String source = req.getParameter(paramNames[i]);
                if (source == null) {
                    params.add(null);
                    continue;
                }
                if (String.class.equals(classes[i])) {
                    params.add(source);
                    continue;
                }
                Object convertResult = ConverterHelper.convert(source, classes[i]);
                params.add(convertResult);
            }
        }
        return params;
    }


}
