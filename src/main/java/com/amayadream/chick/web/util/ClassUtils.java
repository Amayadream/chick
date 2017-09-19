package com.amayadream.chick.web.util;

import com.amayadream.chick.web.util.clazz.AnnotationClassScanner;
import com.amayadream.chick.web.util.clazz.ClassScanner;
import com.amayadream.chick.web.util.clazz.SuperClassClassScanner;
import org.objectweb.asm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author : Amayadream
 * @date :   2017-09-18 10:42
 */
public class ClassUtils {

    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 获取指定包名中所有的类
     * @param packageName 包名
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

    /**
     * 通过ASM获取方法的参数名列表
     * @param method 方法
     * @return
     */
    public static String[] getMethodParamNames(final Method method) {
        final String[] paramNames = new String[method.getParameterTypes().length];
        final String className = method.getDeclaringClass().getName();
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //这里为了解决tomcat中ClassReader无法加载到class文件的问题, 所以手动加载字节码文件
        String classPath = className.replace(".", "/") + ".class";
        InputStream in = getClassLoader().getResourceAsStream(classPath);
        if (in == null) {
            logger.error("类 {} 未找到!", classPath);
            throw new RuntimeException("读取方法参数名称中出现错误: 类加载错误!");
        }
        ClassReader cr;
        try {
            cr = new ClassReader(in);
        } catch (IOException e) {
            logger.error("读取方法参数名称中出现错误: 类加载错误!", e);
            throw new RuntimeException(e);
        }
        cr.accept(new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(final int access,
                                             final String name, final String desc,
                                             final String signature, final String[] exceptions) {
                final Type[] types = Type.getArgumentTypes(desc);
                if (!name.equals(method.getName()) || !compareTo(types, method.getParameterTypes())) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }
                MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
                return new MethodVisitor(Opcodes.ASM5, mv) {
                    @Override
                    public void visitLocalVariable(String name, String desc,
                                                   String signature, Label start, Label end, int index) {
                        int i = index - 1;
                        if (Modifier.isStatic(method.getModifiers())) {
                            i = index;
                        }
                        if (i >= 0 && i < paramNames.length) {
                            paramNames[i] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start, end, index);
                    }
                };
            }
        }, 0);
        return paramNames;
    }

    /**
     * 类型比较
     * @param types
     * @param classes
     * @return
     */
    private static boolean compareTo(Type[] types, Class<?>[] classes) {
        if (types.length != classes.length) {
            return false;
        }
        for (int i = 0; i < types.length; i++) {
            if(!Type.getType(classes[i]).equals(types[i])) {
                return false;
            }
        }
        return true;
    }

}
