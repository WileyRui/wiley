package com.apin.paySys.settings.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apin.paySys.settings.kernel.entity.SysUser;
import com.apin.paySys.settings.kernel.mapper.SysUserMapper;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @author wiley
 * create by 2017/6/2
 */
@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	SysUserMapper sysUserMapper;

	/**
	 * 进入登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("login")
	public String loginUI(Model model) {
		model.addAttribute("name", "hello");
		return "login";
	}
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("userLogin")
	@ResponseBody
	public String login(String userName,String password){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		SysUser user = sysUserMapper.findByName(map);
		String pwd = user.getPassword();
		return pwd;
	}
}
