package com.amayadream.chick.web.handler.impl;

import com.alibaba.fastjson.JSONWriter;
import com.amayadream.chick.web.handler.ViewResolver;
import com.amayadream.chick.web.servlet.ModelAndView;
import com.amayadream.chick.web.util.Constants;
import com.amayadream.chick.web.util.PathUtils;
import com.google.common.base.Charsets;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;

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

    @Override
    public void resolverView(HttpServletRequest req, HttpServletResponse resp, Object result) {
        //TODO 视图解析改造
        if (result == null || resp.isCommitted()) {
            return;
        }
        if (result instanceof ModelAndView) {
            ModelAndView view = (ModelAndView) result;
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
