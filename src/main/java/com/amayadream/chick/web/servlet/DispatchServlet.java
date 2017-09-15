package com.amayadream.chick.web.servlet;

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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("welcome, this is dispatch servlet...");
        super.service(req, resp);
    }

}
