package com.xxd.aspects;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xxd.annotations.AdminLogin;
import com.xxd.annotations.HomeLogin;
import com.xxd.models.XxdPower;
import com.xxd.models.XxdUser;
import com.xxd.utils.Constans;
import com.xxd.utils.U;

/**
 * 所有接口的切面
 * @author Liang
 * @version 1.0
 */

@Component
@Aspect
public class aspects {
	
	
	/**
	 * 登录检测、权限检测、密匙检测
	 * @param pjd
	 * @return
	 */
	@Around("execution(* com.xxd.controllers.*.*(..))")
	public Object checkLoginPower(ProceedingJoinPoint pjd) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		Object result = null;
		MethodSignature joinPointObject = (MethodSignature) pjd.getSignature();
        //获得请求的方法
        Method method = joinPointObject.getMethod();
        //操作日志
        U.logAction("-1  " + request.getRemoteAddr() + "  " + request.getRequestURL());
        //登录检测
        if(hasAnnotationOnMethod(method, AdminLogin.class)) {//后台登录检测
        	Object obj = request.getSession().getAttribute("adminUserInfo");
        	if(null == obj) {
        		//排除登录页面和登录请求
        		if(!request.getRequestURI().equals("/admin/login.html") && !request.getRequestURI().equals("/admin/logins") && !request.getRequestURI().equals("/admin/loginOut")) {
        			//如果没有登录，则自动跳转到登录界面，提示用户登录
            		try {
            			//判断当前方法返回类型,如果是ajax,那么就给出返回值,如果不是ajax,那么就跳转页面
            			if(hasAnnotationOnMethod(method, ResponseBody.class)) {
            				result = Constans.returnCon(-1, null);
            			}else {
            				//展示无权限对应页面板块
                			response.sendRedirect("/admin/login.html");
                			return null;
            			}
    				} catch (IOException e) {
    					U.exceptionLog(e, request.getRemoteAddr());
    				}
        		}else {
        			try {
        				result = pjd.proceed();
        			} catch (Throwable e) {
        				//执行时的异常记录
        				U.exceptionLog(new RuntimeException(e), request.getRemoteAddr());
        			}
        		}
        	}else {
        		//如果用户登录，那么就进行日志记录
        		//用户id+用户请求+时间
        		U.logLogin(((XxdUser)obj).getUid() + "  " + request.getRemoteAddr() + "  " + request.getRequestURL());
        		//检测用户是否具有访问该请求的权限，以及页面的请求权限(二次限制)
        		Object obj1 = request.getSession().getAttribute("adminUserPowerList");
        		boolean isAble = false;
        		//boolean isAble = true;//先把权限放开，最后再将所有权限入库，进行测试
        		if(null != obj1) {
        			ArrayList<XxdPower> powerList = (ArrayList<XxdPower>) obj1;
        			for(int i = 0;i < powerList.size();i ++) {
        				if(powerList.get(i).getUri().equals(request.getRequestURI())) {
        					isAble = true;
        					break;
        				}
        			}
        		}
        		//排除登录页面和登录请求
        		if(request.getRequestURI().equals("/admin/login.html") || request.getRequestURI().equals("/admin/logins") || request.getRequestURI().equals("/admin/loginOut")) {
        			isAble = true;
        		}
        		if(isAble) {
        			try {
        				result = pjd.proceed();
        			} catch (Throwable e) {
        				//执行时的异常记录
        				U.exceptionLog(new RuntimeException(e), request.getRemoteAddr());
        			}
            	}else {
            		try {
            			//判断当前方法返回类型,如果是ajax,那么就给出返回值,如果不是ajax,那么就跳转页面
            			if(hasAnnotationOnMethod(method, ResponseBody.class)) {
            				result = Constans.returnCon(-1, null);
            			}else {
            				//展示无权限对应页面板块
                			response.sendRedirect("/admin/login.html");
                			return null;
            			}
    				} catch (IOException e) {
    					U.exceptionLog(e, request.getRemoteAddr());
    				}
            	}
        	}
            	
        }else {
        	try {
				result = pjd.proceed();
			} catch (Throwable e) {
				//执行时的异常记录
				U.exceptionLog(new RuntimeException(e), request.getRemoteAddr());
			}
        }
		return result;
	}
	
	/**
     * 判断某方法上是否含有某注解
     * @param method
     * @param annotationClazz
     * @return
     */
    private boolean hasAnnotationOnMethod(Method method,Class annotationClazz){
        //使用反射获取注解信息
        Annotation a = method.getAnnotation(annotationClazz);
        if (a == null){
            return false;
        }
        return true;
    }

}
