package com.apin.paySys.settings.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apin.paySys.common.annotation.SysLog;
import com.apin.paySys.settings.kernel.mapper.SysUserMapper;

/**
 * 用户登录
 * 
 * @author wiley create by 2017/6/2
 */
@Controller
@RequestMapping("/")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("userLogin")
	@ResponseBody
	@SysLog(module = "用户管理", methods = "登录", description = "测试切面")
	public Map<String, Object> login(String userName, String password, Boolean rememberMe, String securityCode,
			RedirectAttributes redirectAttributes) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		if (rememberMe == null) {
			rememberMe = false;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
		// 获取当前的Subject=
		Subject currentUser = SecurityUtils.getSubject();
		Session session = SecurityUtils.getSubject().getSession();
		String code = (String) session.getAttribute("securityCode");
		if (securityCode == null || securityCode.equals("")) {
			resultMap.put("status", 500);
			resultMap.put("message", "验证码不能为空！");
			return resultMap;
		}

		// 转化成小写字母
		securityCode = securityCode.toLowerCase();
		// 还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
		// session.removeAttribute("_come");
		if (!code.equals(securityCode)) {
			resultMap.put("status", 500);
			resultMap.put("message", "验证码错误！");
			return resultMap;
		}
		try {
			logger.info("对用户[" + userName + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + userName + "]进行登录验证..验证通过");
			resultMap.put("status", 200);
			resultMap.put("userName", userName);
		} catch (Exception e) {
			logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addFlashAttribute("message", "未知账户");
			resultMap.put("status", 500);
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}

	@RequestMapping("index")
	public String indexUI(String userName, Model model) {
		model.addAttribute("userName", userName);
		return "index";
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		return "login";
	}
}
