package com.amayadream.chick.web.util;

import com.amayadream.chick.web.util.clazz.AnnotationClassScanner;
import com.amayadream.chick.web.util.clazz.ClassScanner;
import com.amayadream.chick.web.util.clazz.SuperClassClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author : Amayadream
 * @date :   2017-09-18 10:42
 */
public class ClassUtils {

    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 获取指定包名中所有的类
     * @param packageName   包名
     * @return
     */
    public static List<Class<?>> getClassList(String packageName) {
        return new ClassScanner(packageName) {
            @Override
            public boolean checkAddClass(Class<?> clazz) {
                String className = clazz.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));
                return pkgName.startsWith(packageName);
            }
        }.getClassList();
    }

    /**
     * 获取指定包名中指定注解的相关类
     * @param packageName       包名
     * @param annotationClass   注解类
     * @return
     */
    public static List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new AnnotationClassScanner(packageName, annotationClass) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    /**
     * 获取指定包名中指定父类或接口的相关类
     * @param packageName   包名
     * @param superClass    父类/接口
     * @return
     */
    public static List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return new SuperClassClassScanner(packageName, superClass) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return superClass.isAssignableFrom(cls) && !superClass.equals(cls);
            }
        }.getClassList();
    }

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类（将自动初始化）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("加载类出错！", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

}
