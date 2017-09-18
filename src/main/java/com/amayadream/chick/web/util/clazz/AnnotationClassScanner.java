package com.amayadream.chick.web.util.clazz;

import java.lang.annotation.Annotation;

/**
 * @author : Amayadream
 * @date :   2017-09-18 13:53
 */
public abstract class AnnotationClassScanner extends ClassScanner {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassScanner(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }

}
