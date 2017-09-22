package com.amayadream.chick.ioc;

import com.amayadream.chick.web.util.ClassUtils;
import com.amayadream.chick.web.util.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author : xjding
 * @date :   2017-09-22 16:46
 */
public class IocHelper {

    private static Logger logger = LoggerFactory.getLogger(IocHelper.class);

    static {
        try {
            //获取并遍历所有的 Bean 类
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                //获取 Bean 类与 Bean 实例
                Class<?> beanClass = entry.getKey();
                Object beanInstance = entry.getValue();
                //获取 Bean 类中所有的字段
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtils.isEmpty(fields)) {
                    continue;
                }
                for (Field field : fields) {
                    //判断字段是否带有 Resource 注解
                    if (!field.isAnnotationPresent(Resource.class)) {
                        continue;
                    }
                    Class<?> clazz = field.getType();
                    Class<?> implementCls = getImplementClass(clazz);
                    if (implementCls == null) {
                        logger.error("依赖注入失败, class: {}, field: {}", beanClass.getSimpleName(), clazz.getSimpleName());
                        continue;   //FIXME 这里应该直接跳出
                    }
                    Object implementInstance = beanMap.get(implementCls);
                    field.setAccessible(true);
                    field.set(beanInstance, implementInstance);
                    System.out.println("hello");
                }
            }
        } catch (Exception e) {
            logger.error("初始化IOC失败!", e);
        }
    }

    public static Class<?> getImplementClass(Class<?> interfaceClass) {
        Class<?> implementClass = interfaceClass;
        List<Class<?>> implementClasses = ClassUtils.getClassListBySuper(Constants.BASE_PACKAGE, interfaceClass);
        if (!CollectionUtils.isEmpty(implementClasses)) {
            //FIXME 这里应该提示有多个实现类
            if (implementClasses.size() == 1) {
                implementClass = implementClasses.get(0);
            }
        }
        return implementClass;
    }

}
