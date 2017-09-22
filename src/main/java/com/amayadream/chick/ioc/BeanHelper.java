package com.amayadream.chick.ioc;

import com.amayadream.chick.ioc.annotation.Component;
import com.amayadream.chick.web.util.ClassUtils;
import com.amayadream.chick.web.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Amayadream
 * @date :   2017-09-22 16:34
 */
public class BeanHelper {

    private static Logger logger = LoggerFactory.getLogger(BeanHelper.class);

    private static final Map<Class<?>, Object> beanMap = new HashMap<>();

    static {
        List<Class<?>> classes = ClassUtils.getClassList(Constants.BASE_PACKAGE);
        classes.forEach(
                cls -> {
                    if (cls.isAnnotationPresent(Component.class)) {
                        try {
                            Object instance = cls.newInstance();
                            beanMap.put(cls, instance);
                        } catch (InstantiationException | IllegalAccessException e) {
                            logger.error("初始化BeanHelper失败", e);
                        }
                    }
                }
        );

    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }


    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            logger.error("无法加载bean实例");
            return null;
        }
        return (T) beanMap.get(cls);
    }

    public static void setBean(Class<?> cls, Object obj) {
        beanMap.put(cls, obj);
    }
}