<%--
  ~ Licensed to the Apache Software Foundation (ASF) under one
  ~ or more contributor license agreements.  See the NOTICE file
  ~ distributed with this work for additional information
  ~ regarding copyright ownership.  The ASF licenses this file
  ~ to you under the Apache License, Version 2.0 (the
  ~ "License"); you may not use this file except in compliance
  ~ with the License.  You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing,
  ~ software distributed under the License is distributed on an
  ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  ~ KIND, either express or implied.  See the License for the
  ~ specific language governing permissions and limitations
  ~ under the License.
  --%>
<%@ page contentType="application/x-java-jnlp-file" %>

<jnlp spec="1.0+" codebase="${codebaseUrl}">
	<information>
	<title>Apache Shiro :: Samples :: Spring Client</title>
	<vendor>The Apache Software Foundation</vendor>
	<homepage href="http://shiro.apache.org/shiro-samples/samples-spring-client/"/>
	<description>A webstart application used to demonstrate Apache Shiro session and security management.</description>
	<icon kind="splash" href="logo.png"/>
	
		  
	</information>
	
		
	<security>
		<all-permissions/>
	</security>
		  
	<resources>
	<j2se version="1.5+"/>
			    
    <property name="jnlp.packEnabled" value="true" />
    <jar href="samples-spring-client-1.3.2.jar" main="true"/>
    <jar href="log4j-1.2.16.jar"/>
    <jar href="shiro-core-1.3.2.jar"/>
    <jar href="shiro-spring-1.3.2.jar"/>
    <jar href="shiro-web-1.3.2.jar"/>
    <jar href="spring-context-3.1.0.RELEASE.jar"/>
    <jar href="spring-aop-3.1.0.RELEASE.jar"/>
    <jar href="aopalliance-1.0.jar"/>
    <jar href="spring-beans-3.1.0.RELEASE.jar"/>
    <jar href="spring-core-3.1.0.RELEASE.jar"/>
    <jar href="spring-expression-3.1.0.RELEASE.jar"/>
    <jar href="spring-asm-3.1.0.RELEASE.jar"/>
    <jar href="spring-webmvc-3.1.0.RELEASE.jar"/>
    <jar href="spring-context-support-3.1.0.RELEASE.jar"/>
    <jar href="spring-web-3.1.0.RELEASE.jar"/>
    <jar href="slf4j-api-1.6.4.jar"/>
    <jar href="slf4j-log4j12-1.6.4.jar"/>
    <jar href="jcl-over-slf4j-1.6.4.jar"/>

		<%-- reading custom system properties requires more permissions than available in sandbox mode,
		thus we need to sign the jars (although we are using an argument instead of a property to set this)
		<property name="shiro.session.id" value="${sessionId}"/>
		--%>
	</resources>
	<application-desc main-class="org.apache.shiro.samples.spring.ui.WebStartDriver">
		<argument>${sessionId}</argument>
	</application-desc> 	
</jnlp>