package com.wybb.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wybb.entity.User;
import com.wybb.util.cache.TokenCache;
import com.wybb.util.result.Code;
import com.wybb.util.result.Result;

@WebFilter(urlPatterns = "/shop/*") 
public class CustomFilter  implements Filter {
	@Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {  
    	System.out.println(123);
    	   HttpServletRequest req = (HttpServletRequest) request;
           HttpServletResponse rep = (HttpServletResponse) response;
           rep.setCharacterEncoding("UTF-8");    
           rep.setContentType("application/json; charset=utf-8");  
    	PrintWriter out = null ;  
    	Result result = new Result();
    	ObjectMapper om = new ObjectMapper();
    	try{
    		   //获取请求参数
    		
    		String code = (String) req.getParameter("code");
    		System.out.println(code);
    		User user = TokenCache.get(code);
    		if(user == null){
    			result.setCode(Code.TOKENERROR.getCode());  
        		result.setMsg(Code.TOKENERROR.getMsg());  
        	    out = rep.getWriter();  
        	    rep.getWriter().write(om.writeValueAsString(result));
        	    return;
    		}
    	}  
    	catch (Exception e){  
    	    e.printStackTrace();  
    	    result.setCode(Code.FAILED.getCode());  
    		result.setMsg(e.getMessage());  
    	    out.write(om.writeValueAsString(result)); 
    	    return;
    	}  
    	   // 防止流读取一次后就没有了, 所以需要将流继续写出去


        filterChain.doFilter(request, response);  
    }  
  
    @Override  
    public void destroy() {  
  
    }  
}
