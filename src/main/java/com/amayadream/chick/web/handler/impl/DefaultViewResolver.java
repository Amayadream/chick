package com.amayadream.chick.web.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.amayadream.chick.web.handler.ViewResolver;
import com.amayadream.chick.web.servlet.View;
import com.google.common.base.Charsets;
import org.apache.commons.collections4.MapUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author : Amayadream
 * @date :   2017-09-18 17:06
 */
public class DefaultViewResolver implements ViewResolver {

    @Override
    public void resolverView(HttpServletRequest req, HttpServletResponse resp, Object result) {
        if (result instanceof View) {
            View view = (View) result;
            String path = "/WEB-INF/view/" + view.getPath();
            Map<String, Object> data = view.getAttributes();
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
            resp.setContentType("application/json"); // 指定内容类型为 JSON 格式
            resp.setCharacterEncoding(Charsets.UTF_8.name()); // 防止中文乱码
            // 向响应中写入数据
            PrintWriter writer = null;
            try {
                writer = resp.getWriter();
                writer.write(JSONObject.toJSONString(result)); // 转为 JSON 字符串
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
