package com.amayadream.chick.web;

import com.alibaba.fastjson.JSONObject;
import com.amayadream.chick.web.bind.annotation.Controller;
import com.amayadream.chick.web.bind.annotation.RequestMapping;
import com.amayadream.chick.web.bind.annotation.RequestMethod;
import com.amayadream.chick.web.servlet.ModelAndView;
import com.amayadream.chick.web.util.ClassUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author : Amayadream
 * @date :   2017-09-19 12:45
 */
@Controller
@RequestMapping(value = "/sample")
public class SampleController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello(String name) {
        ModelAndView view = new ModelAndView("hello");
        view.addObject("name", name);
        return view;
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public JSONObject json(Date a, String b, HttpServletRequest req) {
        JSONObject result = new JSONObject();
        result.put("a", a);
        result.put("b", b);
        System.out.println(req.getQueryString());
        return result;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws IOException {
        InputStream in = ClassUtils.getClassLoader().getResourceAsStream("logback.xml");
        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Length", in.available() +"");
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(("logback.xml").getBytes(), "iso-8859-1"));
        OutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }

}
