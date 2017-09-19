package com.amayadream.chick.web;

import com.alibaba.fastjson.JSONObject;
import com.amayadream.chick.web.bind.annotation.Controller;
import com.amayadream.chick.web.bind.annotation.RequestMapping;
import com.amayadream.chick.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author : Amayadream
 * @date :   2017-09-19 12:45
 */
@Controller
@RequestMapping(value = "/sample")
public class SampleController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public JSONObject hello(Date a, String b, HttpServletRequest req) {
        JSONObject result = new JSONObject();
        result.put("a", a);
        result.put("b", b);
        System.out.println(req.getQueryString());
        return result;
    }

}
