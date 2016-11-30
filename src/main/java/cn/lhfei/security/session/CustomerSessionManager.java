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

package cn.lhfei.security.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 28, 2016
 */
public class CustomerSessionManager extends DefaultWebSessionManager {
	@Override
	protected void onStart(Session session, SessionContext context) {
		if (isSessionIdCookieEnabled()) {
			HttpServletRequest request = WebUtils.getHttpRequest(context);
			HttpServletResponse response = WebUtils.getHttpResponse(context);

			removeSessionCookieForFullDomain(request, response);
		}

		super.onStart(session, context);
	}

	private void removeSessionCookieForFullDomain(HttpServletRequest request, HttpServletResponse response) {
		Cookie sessionCookie = getSessionIdCookie();
		Cookie cookie = new SimpleCookie(sessionCookie.getName());

		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setComment(sessionCookie.getComment());

		// Setting the domain to null means use the fully qualified domain name
		cookie.setDomain(null);

		cookie.setMaxAge(sessionCookie.getMaxAge());
		cookie.setPath(sessionCookie.getPath());
		cookie.setValue(sessionCookie.getValue());
		cookie.setVersion(sessionCookie.getVersion());

		cookie.removeFrom(request, response);
	}
	
	
}
