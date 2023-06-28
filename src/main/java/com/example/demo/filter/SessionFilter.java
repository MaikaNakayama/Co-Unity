package com.example.demo.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * セッションのチェックを行うクラス
 * @author 青木
 *
 */
@Component
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		//セッションが存在しなければエラーとする。
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		//現在のURLパスを検索し値を取得する
		System.out.println(request.getRequestURI());
		String url = request.getRequestURI();
		
		//現在のURLにadminが含まれているかをチェックする
		int index = url.indexOf("admin");
		
		//現在のURLにadminがある場合の処理
		if (index != -1) {
			
			//セッションのチェックを行う
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("key") == null) {
				response.sendRedirect(request.getContextPath() + "/err");
				return;
			}
		}
		//現在のURLにadminがない場合の処理
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
