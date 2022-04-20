package com.lkh.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lkh.example.demo.service.MemberService;
import com.lkh.example.demo.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		//이제는 Rq 객체가 자동으로 만들어짐

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
