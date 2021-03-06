package com.wiley.shiro.settings.web.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 操作日志
 * 
 */
@Aspect
@Component
public class LogAop {
	private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

	@Pointcut("@annotation(com.wiley.shiro.common.annotation.SysLog)")
	private void SysLogPointcut() {
	};

	@Before("SysLogPointcut()")
	private void startTime(JoinPoint point) {
		try {
			// 执行方法名
			String methodName = point.getSignature().getName();
			String className = point.getTarget().getClass().getSimpleName();
			// *========控制台输出=========*//
			System.out.println("=====通知开始=====");
			System.out.println(currentTime());
			System.out.println("请求方法:" + className + "." + methodName + "()");
			System.out.println(currentTime());
			System.out.println("=====通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==后置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	private static String currentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		return time;
	}
}
