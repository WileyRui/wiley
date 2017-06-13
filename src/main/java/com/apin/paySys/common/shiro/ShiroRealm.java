package com.apin.paySys.common.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.apin.paySys.settings.kernel.entity.SysUser;
import com.apin.paySys.settings.kernel.mapper.SysUserMapper;

/**
 * shiroRealm
 * 
 * @author wiley
 *
 * @time 2017年6月5日上午11:11:36
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	SysUserMapper sysUserMapper;

	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("##################执行Shiro权限认证##################");
		// 获取当前登录输入的用户名，等价于(String)
		// principalCollection.fromRealm(getName()).iterator().next();
		String loginName = (String) super.getAvailablePrincipal(principalCollection);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", loginName);
		SysUser user = sysUserMapper.findByName(map);
		if (user != null) {
			// 权限信息对象info，用来存储用户所有的角色和权限
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		}
		return null;
	}

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// UsernamePasswordToken : 存放提交的用户登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		logger.info(
				"验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", token.getUsername());
		SysUser user = sysUserMapper.findByName(map);
		if (user == null) {
			throw new AccountException("用户名或密码不正确！");
		}
		if(!token.getPassword().equals(user.getPassword())){
		    throw new AccountException("密码错误！");
		}
		return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
	}

}
