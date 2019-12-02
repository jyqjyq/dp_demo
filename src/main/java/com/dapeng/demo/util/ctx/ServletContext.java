package com.dapeng.demo.util.ctx;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ServletContext {

	public static ServletRequestAttributes getServletAttributes() {
		return  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}
	public static HttpServletRequest request() {
		return ServletContext.getServletAttributes().getRequest();
	}
	public static HttpServletResponse response() {
		return ServletContext.getServletAttributes().getResponse();
		
	}
	
	
	public static Optional<String> get(String name) {
		return Optional.ofNullable( ServletContext.request().getParameter(name));
	}
	public static Integer getInt(String name,int defaultValue) {
		Optional<String> opt = get(name);
		if ( opt.isPresent()) {
			return Integer.valueOf(opt.get());
		}else {
			return defaultValue;
		}
	}
	public static Long getLong(String name,long defaultValue) {
		Optional<String> opt = get(name);
		if ( opt.isPresent()) {
			return Long.valueOf(opt.get());
		}else {
			return defaultValue;
		}
	}
}
