package com.amayadream.chick.web.servlet;

import com.amayadream.chick.web.bind.annotation.RequestMethod;
import com.amayadream.chick.web.handler.HandlerInvoker;
import com.amayadream.chick.web.handler.HandlerMapping;
import com.amayadream.chick.web.handler.impl.DefaultHandlerInvoker;
import com.amayadream.chick.web.handler.impl.DefaultHandlerMapping;
import com.amayadream.chick.web.mapping.HandlerInfo;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 核心分发器
 * @author :  Amayadream
 * @date :  2017.09.14 21:34
 */
@WebServlet(name = "/dispatchServlet", urlPatterns = {"/*"}, loadOnStartup = 1)
public class DispatchServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(DispatchServlet.class);

    private HandlerMapping handlerMapping = new DefaultHandlerMapping();
    private HandlerInvoker handlerInvoker = new DefaultHandlerInvoker();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(Charsets.UTF_8.name());

        String requestMethod = req.getMethod();
        String requestPath = req.getServletPath() + StringUtils.defaultIfEmpty(req.getPathInfo(), "");

        //去除尾部/
        if (requestPath.endsWith("/")) {
            requestPath = requestPath.substring(0, requestPath.length() - 1);
        }

        HandlerInfo handlerInfo = handlerMapping.getHandler(RequestMethod.valueOf(requestMethod), requestPath);

        if (handlerInfo == null) {
            resp.sendError(404, "404 not found");
            return;
        }

        try {
            handlerInvoker.invoke(req, resp, handlerInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
