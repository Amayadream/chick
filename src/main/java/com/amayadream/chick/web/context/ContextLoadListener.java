package com.amayadream.chick.web.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 上下文加载监听器
 * @author : Amayadream
 * @date :   2017-09-15 17:29
 */
@WebListener
public class ContextLoadListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(ContextLoadListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("ContextLoadListener is init...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("ContextLoadListener is destroyed...");
    }

}
