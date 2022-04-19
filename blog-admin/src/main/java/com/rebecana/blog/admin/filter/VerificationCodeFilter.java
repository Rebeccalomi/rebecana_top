package com.rebecana.blog.admin.filter;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerificationCodeFilter extends OncePerRequestFilter {

    private final AuthenticationFailureHandler authenticationFailureHandler = new MyAuthenticationFailureHandler();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //非登录请求不校验验证码
        String requestURI = httpServletRequest.getRequestURI();
        if (!"/login".equalsIgnoreCase(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            try {
                verificationCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (VerificationCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private void verificationCode(HttpServletRequest request) throws VerificationCodeException {
        // 在form表单中获取对应输入的值
        String captcha = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String saveCode = (String) session.getAttribute("captcha");
        if (StringUtils.hasLength(saveCode)) {
            session.removeAttribute("captcha");
        }
        if (!StringUtils.hasLength(captcha) || !StringUtils.hasLength(saveCode) || !captcha.equals(saveCode)) {

            throw new VerificationCodeException();
        }
    }
}
