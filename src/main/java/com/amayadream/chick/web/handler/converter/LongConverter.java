package com.amayadream.chick.web.handler.converter;

import com.amayadream.chick.web.handler.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Amayadream
 * @date :   2017-09-19 16:00
 */
public class LongConverter implements Converter<String, Long> {

    private static Logger logger = LoggerFactory.getLogger(IntegerConverter.class);

    @Override
    public Long convert(String source) {
        Long i;
        try {
            i = Long.valueOf(source);
            return i;
        } catch (NumberFormatException e) {
            logger.error("格式化失败!", e);
            throw e;
        }
    }

}
