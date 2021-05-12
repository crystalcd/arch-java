package org.example.use.dong.controller;

import org.example.framework.annotation.Action;
import org.example.framework.annotation.Controller;
import org.example.framework.bean.Data;
import org.example.framework.bean.Param;
import org.example.framework.bean.View;

import java.util.Map;

@Controller
public class CustomerController {

    @Action("get:/test")
    public View test(Param param) {
        return new View("Customer.jsp");
    }

    @Action("post:/posttest")
    public Data posttest(Param param) {
        Map<String, Object> map = param.getMap();
        map.put("return", "123");
        Data rs = new Data(map);
        return rs;
    }
}
