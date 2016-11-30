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

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.internal.LinkedTreeMap;

import cn.lhfei.security.authenticate.AbstractAuthenticateHandler;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 25, 2016
 */
@Service("authenticateHandler")
public class RemoteAuthenticateHandler extends AbstractAuthenticateHandler {
	private static Logger log = LoggerFactory.getLogger(RemoteAccountRealm.class);
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    public static final HttpRequestFactory REQUEST_FACTORY = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
		@Override
		public void initialize(com.google.api.client.http.HttpRequest request) throws IOException {
			request.setParser(new JsonObjectParser(JSON_FACTORY));
		}
    });
	
	@Override
	public boolean authenticate(Map<String, Object> accountInfo) {
		boolean isAuthorized = false;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		String loginMethod = "user";
		
		String username = (String) accountInfo.get("username");
		String password = (String) accountInfo.get("password");
		 
		HttpGet httpGet = null;
		String response = null;
		
		URIBuilder builder = null;
		URI uri = null;
		try {
			builder = new URIBuilder(remoteUri + loginMethod);
			builder.setParameter("action", "login").setParameter("userName", username).setParameter("password", password);
			httpGet = new HttpGet(uri);

			uri = builder.build();
			log.info(uri.toString());

			/*CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			response = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
			log.info("Response Body: {}", response);

			httpResponse.close();
			LinkedTreeMap<String, String> jsonMap = gson.fromJson(response, LinkedTreeMap.class);
			
			isAuthorized = (jsonMap.get("code") == null);*/
			
			GenericUrl url = new GenericUrl(uri.toString());
			HttpRequest request = REQUEST_FACTORY.buildGetRequest(url);
			
			HttpResponse rep = request.execute();
			
			String json = IOUtils.toString(rep.getContent(), rep.getContentCharset());
			log.info("Response Body: \r\n{}", json);
			
			isAuthorized = (rep.getStatusCode() == 200);
			
		} catch (Exception e) {
			log.warn("Connected to Server [{}], Error: {}", uri.getPath(), e.getMessage(), e);
			throw new RuntimeException("Connected to Server [{"+uri.toString()+"}] failed.", e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		
		return isAuthorized;
	}
	
	public String getOrganization(Map<String, Object> accountInfo) {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpGet = null;
		String response = null;
		String loginMethod = "department";
		
		try {
			URIBuilder builder = new URIBuilder(remoteUri + loginMethod);
			
			builder.setParameter("action", "queryOneLevelDepartments").setParameter("account", accountInfo.get("username").toString());
			if(accountInfo.containsKey("deptId")){
				builder.setParameter("deptId", ""+accountInfo.get("deptId"));
			}
			
			URI uri = builder.build();
			httpGet = new HttpGet(uri);

			log.info(uri.toString());

			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			response = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
			log.info("Response Body: {}", response);

			httpResponse.close();
		} catch (Exception e) {
			log.warn("Get User[{}] principls has an error. [{}]", accountInfo.get("username"), e.getMessage(), e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
			}
		}
		
		return response;
	}
	
	public boolean register(Map<String, Object> accountInfo) {
		boolean isAuthorized = false;
		
		
		
		return isAuthorized;
	}
	
	@Value("${sq.gov.local.uri}")
	private String remoteUri;

}
