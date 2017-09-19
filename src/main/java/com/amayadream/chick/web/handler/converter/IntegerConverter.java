package com.amayadream.chick.web.handler.converter;

import com.amayadream.chick.web.handler.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Amayadream
 * @date :   2017-09-19 15:13
 */
public class IntegerConverter implements Converter<String, Integer> {

    private static Logger logger = LoggerFactory.getLogger(IntegerConverter.class);

    @Override
    public Integer convert(String source) {
        Integer i;
        try {
            i = Integer.valueOf(source);
            return i;
        } catch (NumberFormatException e) {
            logger.error("格式化失败!", e);
            throw e;
        }
    }

}
