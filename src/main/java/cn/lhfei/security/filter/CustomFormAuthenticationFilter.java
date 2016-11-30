/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 28, 2016
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);
	

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		
		HttpServletRequest req =  (HttpServletRequest)request;
		
		HttpServletResponse rep =  (HttpServletResponse)response;
		
		String originUrl = req.getParameter("url");	
		
		String username = super.getUsername(request);
		log.info("Username: [{}]", username);
		
		//你在这里写入cookie
		
		String contextPath = WebUtils.getContextPath(req);
		log.info(contextPath);
		
		String requestUrl = req.getContextPath();
		
		if(SecurityUtils.getSubject().isAuthenticated()){
			Cookie cookie = new Cookie("pin", username);
			cookie.setDomain(domain);
			cookie.setPath(requestUrl);
			cookie.setMaxAge(-1); // 30 days
			
			rep.addCookie(cookie);
		}
		
		
		WebUtils.issueRedirect(request, response, originUrl);
	}
	
	
	
	public void setDomain(String domain) {
		this.domain = domain;
	}

	private String domain;
}
