package com.amayadream.chick.web.handler.impl;

import com.alibaba.fastjson.JSONWriter;
import com.amayadream.chick.web.handler.ViewResolver;
import com.amayadream.chick.web.servlet.ModelAndView;
import com.amayadream.chick.web.util.Constants;
import com.amayadream.chick.web.util.PathUtils;
import com.google.common.base.Charsets;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 默认视图解析器
 * @author : Amayadream
 * @date :   2017-09-18 17:06
 */
public class DefaultViewResolver implements ViewResolver {

    private static Logger logger = LoggerFactory.getLogger(DefaultViewResolver.class);

    public static final String REDIRECT_TAG = "redirect:";

    @Override
    public void resolverView(HttpServletRequest req, HttpServletResponse resp, Object result) {
        if (result == null || resp.isCommitted()) {
            logger.info("no view or response is committed!");
            return;
        }
        if (result instanceof ModelAndView) {
            ModelAndView view = (ModelAndView) result;
            if (StringUtils.isEmpty(view.getView())) {
                logger.info("no view!");
                return;
            }
            if (view.getView().startsWith(REDIRECT_TAG)) {   //重定向
                String redirectPath = view.getView().substring(REDIRECT_TAG.length(), view.getView().length());
                try {
                    Map<String, Object> data = view.getModel();
                    if (MapUtils.isNotEmpty(data)) {
                        for (Map.Entry<String, Object> entry : data.entrySet()) {
                            req.getSession().setAttribute(entry.getKey(), entry.getValue());
                        }
                    }
                    resp.sendRedirect(redirectPath);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String path = "/" + PathUtils.pure(Constants.VIEW_PREFIX) + "/" + PathUtils.pure(view.getView()) + Constants.VIEW_SUFFIX;
            Map<String, Object> data = view.getModel();
            if (MapUtils.isNotEmpty(data)) {
                for (Map.Entry<String, Object> entry : data.entrySet()) {
                    req.setAttribute(entry.getKey(), entry.getValue());
                }
            }
            try {
                req.getRequestDispatcher(path).forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            resp.setContentType("application/json");
            resp.setCharacterEncoding(Charsets.UTF_8.name());
            PrintWriter writer;
            try {
                writer = resp.getWriter();
                JSONWriter jsonWriter = new JSONWriter(writer);
                jsonWriter.writeObject(result);
                IOUtils.closeQuietly(jsonWriter);
                IOUtils.closeQuietly(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
