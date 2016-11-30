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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.security.test.SecurityTestSuite;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 25, 2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class RemoteAuthenticateHandlerTest extends SecurityTestSuite {
	
	@Test
	public void authenticate() {
		Map<String, Object> accountInfo = getAccount();
		boolean isOk = authenticateHandler.authenticate(accountInfo);
		
		log.info("{}", isOk);
		
		Assert.assertFalse(isOk);
		
	}
	
	@Test
	public void getOrgInfo() {
		Map<String, Object> accountInfo = getAccount();
		String orgs = authenticateHandler.getOrganization(accountInfo);
		
		log.info("{}", orgs);
		
		
		accountInfo.put("deptId", "18");
		log.info("Second Level Org: {}", orgs);
		
	}
	
	private Map<String, Object> getAccount() {
		Map<String, Object> accountInfo = new HashMap<>();
		accountInfo.put("username", "admin");
		accountInfo.put("password", "admin");
		
		return accountInfo;
	}
	
	
	@Autowired
	private RemoteAuthenticateHandler authenticateHandler;
}
