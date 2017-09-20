package com.amayadream.chick.web.mapping;

import com.amayadream.chick.web.bind.annotation.Controller;
import com.amayadream.chick.web.bind.annotation.RequestMapping;
import com.amayadream.chick.web.util.ClassUtils;
import com.amayadream.chick.web.util.Constants;
import com.amayadream.chick.web.util.PathUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理器映射初始化类
 * @author : Amayadream
 * @date :   2017-09-18 15:08
 */
public class HandlerInitializer {

    private static Logger logger = LoggerFactory.getLogger(HandlerInitializer.class);

    private static final Map<RequestInfo, HandlerInfo> handlerMap = new LinkedHashMap<>();

    static {
        List<Class<?>> controllers = ClassUtils.getClassListByAnnotation(Constants.BASE_PACKAGE, Controller.class);
        if (CollectionUtils.isNotEmpty(controllers)) {
            Map<RequestInfo, HandlerInfo> commonMap = new HashMap<>();
            controllers.forEach(
                    (cls) -> {
                        Method[] methods = cls.getDeclaredMethods();
                        if (ArrayUtils.isNotEmpty(methods)) {
                            for (Method method : methods) {
                                handlerMethod(cls, method, commonMap);
                            }
                        }

                    }
            );
            handlerMap.putAll(commonMap);
        }
    }

    /**
     * 处理控制器方法
     * @param clazz     控制器
     * @param method    方法
     * @param commonMap 处理器映射
     */
    private static void handlerMethod(Class<?> clazz, Method method, Map<RequestInfo, HandlerInfo> commonMap) {
        RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
        if (methodMapping != null) {
            String path = "";
            RequestMapping classMapping = clazz.getAnnotation(RequestMapping.class);
            if (classMapping != null) {
                path = classMapping.value();
            }
            path = PathUtils.append(path, methodMapping.value());
            RequestInfo requestInfo = new RequestInfo(methodMapping.method(), path);
            HandlerInfo handlerInfo = new HandlerInfo(clazz, method);
            commonMap.put(requestInfo, handlerInfo);
            logger.info("add handler mapping: method={}, route={}", requestInfo.getMethod(), requestInfo.getPath());
        }
    }

    public static Map<RequestInfo, HandlerInfo> getHandlerMapping() {
        return handlerMap;
    }

}
