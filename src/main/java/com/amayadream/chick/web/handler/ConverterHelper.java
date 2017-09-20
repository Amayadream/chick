package com.amayadream.chick.web.handler;

import com.amayadream.chick.web.util.ClassUtils;
import com.amayadream.chick.web.util.Constants;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数类型转换帮助类
 * @author : Amayadream
 * @date :   2017-09-19 14:25
 */
public class ConverterHelper {

    private static final Map<String, ConverterHandler> converters = new HashMap<>();

    static {
        Class clazz = Converter.class;
        List<Class<?>> classes = ClassUtils.getClassListBySuper(Constants.BASE_PACKAGE, clazz);
        for (Class<?> cls : classes) {
            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                if (method.getName().equals("convert") && !method.getGenericReturnType().equals(Object.class)) {
                    Type returnType = method.getReturnType();
                    ConverterHandler handler = null;
                    try {
                        handler = new ConverterHandler(cls, method);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    converters.put(returnType.getTypeName(), handler);
                }
            }
        }
    }

    /**
     *
     * @param source
     * @param targetClass
     * @return
     */
    public static Object convert(Object source, Class<?> targetClass) {
        if (source == null) {
            return null;
        }
        for (Map.Entry<String, ConverterHandler> entry : converters.entrySet()) {
            if (entry.getKey().equals(targetClass.getName())) {
                ConverterHandler handler = entry.getValue();
                try {
                    return handler.getMethod().invoke(handler.getInstance(), source);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return source;
    }

}
