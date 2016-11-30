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

package cn.lhfei.security.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lhfei.security.web.model.UserModel;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 23, 2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class ShiroMain {

	@Test
	public void getToken() {
		try {
			UserModel user = new UserModel();
			user.setUsername("user1");
			user.setPassword("user1");
			
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		
	}
}
