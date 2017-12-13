package com.wiley.shiro.settings.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.wiley.shiro.common.annotation.SysLog;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/apin/user/")
public class SysUserController {

    @RequestMapping("addUI")
    @SysLog(module = "用户管理", methods = "addUI", description = "测试aop")
    public String addUI() {
        return "sys/user/add";
    }

    @RequestMapping("add")
    public String add(HttpServletRequest request) {
        String name = request.getParameter("userName");
        return name + "hello";
    }
}
