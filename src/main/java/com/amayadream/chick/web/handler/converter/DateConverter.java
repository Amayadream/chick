package com.amayadream.chick.web.handler.converter;

import com.amayadream.chick.web.handler.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Amayadream
 * @date :   2017-09-19 14:26
 */
public class DateConverter implements Converter<String, Date> {

    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public Date convert(String source) {
        String pattern = source.length() == 10 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(source);
        } catch (ParseException e) {
            logger.error("格式化失败!", e);
            e.printStackTrace();
        }
        return null;
    }

}
