package com.apin.paySys.settings.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apin.paySys.settings.kernel.mapper.SysUserMapper;

/**
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
    public Map<String, Object> login(String userName, String password,RedirectAttributes redirectAttributes) {
        // if (bindingResult.hasErrors()) {
        // return "login";
        // }
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            logger.info("对用户[" + userName + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + userName + "]进行登录验证..验证通过");
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }
}
