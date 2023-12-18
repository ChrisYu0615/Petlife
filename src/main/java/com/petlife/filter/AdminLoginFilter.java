package com.petlife.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginFilter implements Filter {
	private FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 轉型成HttpServletRequest/HttpServletResponse物件
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 取得Session
		HttpSession session = req.getSession();
		Object admin = session.getAttribute("admin");

		// 沒有取得登入資訊(重導至登入頁面)
		if (admin == null) {
			// 把目前頁面存放到session中
			session.setAttribute("location", req.getRequestURI());
			// 重導至登入頁面
			res.sendRedirect(req.getContextPath() + "/login/backend_login.html");
			return;
		} else {
			// 有取得登入資訊，先從session中找是否有前頁面的資訊	
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		filterConfig = null;
	}

}
