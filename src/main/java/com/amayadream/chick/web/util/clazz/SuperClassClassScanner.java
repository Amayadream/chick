package com.amayadream.chick.web.util.clazz;

/**
 * @author : Amayadream
 * @date :   2017-09-18 13:54
 */
public abstract class SuperClassClassScanner extends ClassScanner {

    protected final Class<?> superClass;

    protected SuperClassClassScanner(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }

}
