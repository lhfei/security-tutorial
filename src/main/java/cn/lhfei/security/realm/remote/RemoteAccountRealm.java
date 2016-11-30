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

package cn.lhfei.security.realm.remote;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.lhfei.security.web.model.BasicAccountInfo;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 24, 2016
 */
public class RemoteAccountRealm extends AuthorizingRealm {
    public static Logger log = LoggerFactory.getLogger(RemoteAccountRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
    
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo info = null;
		UsernamePasswordToken account = (UsernamePasswordToken)token;
	
		String username = account.getUsername();
		String password = new String((char[])token.getCredentials());
		
		Map<String, Object> accountMap = new HashMap<>();
		accountMap.put("username", username);
		accountMap.put("password", password);
		
		try {
			//boolean isAuthenticated = authenticateHandler.authenticate(accountInfo);
			
			// @ DEBUG
			 boolean isAuthenticated = true;
			
			if(isAuthenticated){
				SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(username, password, getName());
				log.info("User: {}, Password: {}", username, password);
				saInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
				info = saInfo;
				
				Session session = SecurityUtils.getSubject().getSession();
				BasicAccountInfo accountInfo = new BasicAccountInfo();
				accountInfo.setUsername(username);
				
				session.setAttribute("accountInfo", accountInfo);
				
			} else {
				throw new AuthenticationException("Authenticating user[" +username+ "] failed.");
			}
			
		} catch (Exception e) {
			log.warn("Authenticated failed. {}", e.getMessage(), e);
			throw new AuthenticationException(e.getMessage(), e);
		} 
		
		return info;
	}

	public void setRemoteUri(String remoteUri) {
		this.remoteUri = remoteUri;
	}


	private String remoteUri;
	
	@Autowired
	private RemoteAuthenticateHandler authenticateHandler;


}
