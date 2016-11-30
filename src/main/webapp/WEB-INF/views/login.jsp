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
<%@ include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="zh_CN">

<head>

<!--[if lt IE 9]>
	<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/node_modules/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/system/css/styles.css"/>" rel="stylesheet">
<title>用户登录</title>
</head>

<body onload="document.forms[0].elements[0].focus();" style="background:url(<c:out value="resources/system/images/reg_bg.jpg"></c:out>) no-repeat 50% #196eba; height:710px;">

	<%-- <div id="contentBox">
		<form action="login" method="POST" modelAttribute="userForm">
			Username: <input id="username" name="username" type="text" /><br />
			<br /> Password: <input name="password" type="password" /><br />
			<br /> <input name="url" , type="hidden" , value="${url}" /> <input
				type="submit" value="Login" />
		</form>

		<p>Try logging in with username/passwords: user1/user1 and
			user2/user2.</p>
	</div> --%>
	
	<!--login modal-->
	<div id="loginModal" class="modal show" tabindex="-1" 
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					
					<h2 class="text-center">宿迁政务云 </h2>
				</div>
				<div class="modal-body">
					<form class="form col-md-12 center-block" action="login" method="POST" modelAttribute="userForm">
						<div class="form-group row">
							<label for="username" class="col-xs-2 col-form-label">用户名</label>
							<div class="col-xs-10">
								<input type="text" class="form-control" name="username" placeholder="登录名">
							</div>
						</div>
						<div class="form-group row">
							<label for="password" class="col-xs-2 col-form-label">密码</label>
							<div class="col-xs-10">
								<input type="password" class="form-control" name="password" placeholder="登录密码">
								<input name="url" , type="hidden" , value="${url}" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-12">
								<button class="btn btn-primary btn-block">登录</button>
								<span class="pull-right"><a
									href="http://inf.sqgov.local/user/reg">注册</a></span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<div class="col-md-12">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- <div class="container">
		<div class="wrapper">
			<form class="form col-md-12 center-block">
			
				<div class="form-group row">
					<label for="username" class="col-xs-2 col-form-label">用户名</label>
					<div class="col-xs-10">
						<input type="text" class="form-control"
							placeholder="登录名">
					</div>
				</div>
				<div class="form-group row">
					<label for="password" class="col-xs-2 col-form-label">密码</label>
					<div class="col-xs-10">
						<input type="password" class="form-control"
							placeholder="登录密码">
					</div>
				</div>
				<div class="form-group row">
					<button class="btn btn-primary btn-block">登录</button>
					<span class="pull-right"><a
						href="http://inf.sqgov.local/user/reg">注册</a></span>
				</div>
			</form>	
		</div>
	</div> -->
	
	<!-- script references -->
	<script src="<c:url value="/resources/node_modules/jquery/dist/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/node_modules/bootstrap/dist/js/bootstrap.min.js"/>"></script>
	
</body>

</html>
