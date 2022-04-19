package com.rebecana.blog.handler;

import com.alibaba.fastjson.JSON;
import com.rebecana.blog.dao.pojo.SysUser;
import com.rebecana.blog.service.LoginService;
import com.rebecana.blog.utils.UserThreadLocal;
import com.rebecana.blog.vo.ErrorCode;
import com.rebecana.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component //spring识别
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        /**
         * 在执行controll方法（Handler）之前进行执行
         * 1。需要判断 请求的借口路径 是否为handlermethod（controller方法）
         * 2。判断token是否为空，如果为空 未登陆
         * 3。如果token不为空，进行登陆验证loginservice checktoken验证
         * 4。验证成功放行
         */
        if(!(handler instanceof HandlerMethod)){
            //handler可能是request resourcehandler springboot程序访问静态资源，默认去classpath目录下的static目录查询
            return true;
        }
        String token=request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if(StringUtils.isBlank(token)){
            Result result= Result.fail(ErrorCode.NO_LOGIN.getCode(),"未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if(sysUser==null){
            Result result= Result.fail(ErrorCode.NO_LOGIN.getCode(),"未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        //登录验证成功，放行
        //我希望在controller中 直接获取用户的信息 怎么获取?
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除 ThreadLocal中用完的信息 会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
