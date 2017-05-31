package com.apin.paySys.settings.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apin.paySys.common.annotation.SysLog;

@Controller
@Scope("prototype")
@RequestMapping("/apin/user/")
public class SysUserController {
	@RequestMapping("list")
	public String userUI(Model model) {
		model.addAttribute("name", "hello");
		return "sys/user/list";
	}

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
