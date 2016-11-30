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
<title>用户注册</title>
</head>

<body onload="document.forms[0].elements[0].focus();" >

		<!-- <div class="container">
			<div class="row">
				<div class="col-md-6">
	
					<form class="form col-md-12 center-block" action="" method="POST">
						<fieldset>
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="username">用户名</label>
								<div class="col-sm-9">
									<input type="text" id="username" name="username" placeholder=""
										class="form-control input-sm">
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="password">登录密码</label>
								<div class="col-sm-9">
									<input type="password" id="password" name="password"
										placeholder="" class="form-control input-sm">
								</div>
							</div>
	
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="password_confirm">确认密码</label>
								<div class="col-sm-9">
									<input type="password" id="password_confirm"
										name="password_confirm" placeholder=""
										class="form-control input-sm">
								</div>
							</div>
							
							<div id="legend">
								<legend class=""></legend>
							</div>
	
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="email">真实姓名</label>
								<div class="col-sm-9">
									<input type="text" id="email" name="name" placeholder=""
										class="form-control input-sm">
									<p class="help-block"></p>
								</div>
							</div>
							
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="username">所属部门</label>
								<div class="col-sm-9">
									<input type="text" id="dept" name="dept" placeholder=""
										class="form-control input-sm">
									<p class="help-block"></p>
								</div>
							</div>
	
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="username">手机号码</label>
								<div class="col-sm-9">
									<input type="text" id="telphone" name="telphone" placeholder=""
										class="form-control input-sm">
									<p class="help-block"></p>
								</div>
							</div>
	
							<div class="form-group row">
								Button
								<div class="col-sm-9">
									<button class="btn btn-success">注册</button>
								</div>
							</div>
						</fieldset>
					</form>
	
				</div>
			</div>
		</div> -->

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row"></div>

				<div class="col-sm-3 middle-border"></div>

				<div class="col-sm-6">
					<div class="form-box">
						<div class="form-top">
							<div class="form-top-right">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form class="form col-md-12 center-block" action="regis" method="POST">
								<fieldset>
									<legend class="text-center">用户注册<span class="req"><small> (<span style="color:red">*</span>必输项)</small></span></legend>
									<div class="form-group row">
										<label class="col-sm-3 col-form-label" for="username"><span style="color:red">*</span>用户名</label>
										<div class="col-sm-9">
											<input type="text" id="username" name="username"
												placeholder="" class="form-control input-sm">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3 col-form-label" for="password"><span style="color:red">*</span>登录密码</label>
										<div class="col-sm-9">
											<input type="password" id="password" name="password"
												placeholder="" class="form-control input-sm">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3 col-form-label" for="password_confirm"><span style="color:red">*</span>确认密码</label>
										<div class="col-sm-9">
											<input type="password" id="password_confirm"
												name="password_confirm" placeholder=""
												class="form-control input-sm">
										</div>
									</div>

									<div id="legend">
										<legend class=""></legend>
									</div>

									<div class="form-group row">
										<label class="col-sm-3 col-form-label" for="email"><span style="color:red">*</span>真实姓名</label>
										<div class="col-sm-9">
											<input type="text" id="email" name="name" placeholder=""
												class="form-control input-sm">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3 col-form-label" for="username"><span style="color:red">*</span>所属部门</label>
										<div class="col-sm-9">
											<input type="text" id="dept" name="dept" placeholder=""
												class="form-control input-sm">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3 col-form-label" for="username"><span style="color:red">*</span>手机号码</label>
										<div class="col-sm-9">
											<input type="text" id="telphone" name="telphone"
												placeholder="" class="form-control input-sm">
											<p class="help-block"></p>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-12">
											<button class="btn btn-primary btn-block">注册</button>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>

	</div>

	<!-- Footer -->
	<footer>
		<div class="container">
			<div class="row"></div>
		</div>
	</footer>



	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->		


	<!-- script references -->
	<script src="<c:url value="/resources/node_modules/jquery/dist/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/node_modules/bootstrap/dist/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/system/js/jquery.backstretch.min.js"/>"></script>
	<script src="<c:url value="/resources/system/js/scripts.js"/>"></script>
	
</body>

</html>
