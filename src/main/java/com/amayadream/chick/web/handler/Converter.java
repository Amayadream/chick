package com.amayadream.chick.web.handler;

/**
 * 类型转换接口
 * @author : Amayadream
 * @date :   2017-09-19 14:27
 */
public interface Converter<S, T> {

    T convert(S source);

}
