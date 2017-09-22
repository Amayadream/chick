package com.amayadream.chick.web.context;

import com.amayadream.chick.ioc.BeanHelper;
import com.amayadream.chick.ioc.IocHelper;
import com.amayadream.chick.web.util.Constants;
import com.amayadream.chick.web.util.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * 上下文加载监听器
 * @author : Amayadream
 * @date :   2017-09-15 17:29
 */
@WebListener
public class ContextLoadListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(ContextLoadListener.class);

    private BeanHelper beanHelper = new BeanHelper();
    private IocHelper iocHelper = new IocHelper();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("ContextLoadListener is init...");

        ServletContext servletContext = event.getServletContext();
        registerDefaultServlet(servletContext);
        registerJspServlet(servletContext);
    }

    private void registerDefaultServlet(ServletContext context) {
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping("/index.html");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping("/" + PathUtils.pure(Constants.STATIC_RESOURCES) + "/*");
    }

    private void registerJspServlet(ServletContext context) {
        ServletRegistration jspServlet = context.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping("/" + PathUtils.pure(Constants.VIEW_PREFIX) + "/" + "*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("ContextLoadListener is destroyed...");
    }

}
