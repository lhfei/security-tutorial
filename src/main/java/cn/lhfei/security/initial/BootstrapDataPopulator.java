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

package cn.lhfei.security.initial;

import javax.sql.DataSource;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * A data populator that creates a set of security tables and test data that can be used by the
 * Shiro Spring sample application to demonstrate the use of the {@link org.apache.shiro.realm.jdbc.JdbcRealm}
 * The tables created by this class follow the default table and column names that {@link org.apache.shiro.realm.jdbc.JdbcRealm} uses.
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 20, 2016
 */
public class BootstrapDataPopulator implements InitializingBean {

	private static final String CREATE_TABLES = "create table if not exists users (\n" + "    username varchar(255) primary key,\n"
			+ "    password varchar(255) not null\n" + ");\n" + "\n" + "create table if not exists roles (\n"
			+ "    role_name varchar(255) primary key\n" + ");\n" + "\n" + "create table if not exists user_roles (\n"
			+ "    username varchar(255) not null,\n" + "    role_name varchar(255) not null \n);\n" + "\n"
			+ "create table if not exists roles_permissions (\n" + "    role_name varchar(255) not null,\n"
			+ "    permission varchar(255) not null \n);";
	
	private static final String DROP_DATA = "DELETE FROM `roles`; DELETE FROM `roles_permissions`; DELETE FROM  `user_roles`; DELETE FROM `users`;";

	private static final Logger log = LoggerFactory.getLogger(BootstrapDataPopulator.class);

	protected DataSource dataSource = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// because we're using an in-memory hsqldb for the sample app, a new one
		// will be created each time the
		// app starts, so create the tables and insert the 2 sample users on
		// bootstrap:

		log.info("\n{}", CREATE_TABLES);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		
		for(String ddl : CREATE_TABLES.split("[;]")){
			jdbcTemplate.execute(ddl);
		}
		
		log.info("\n{}", DROP_DATA);
		
		for(String ddl : DROP_DATA.split("[;]")){
			jdbcTemplate.execute(ddl);
		}
		//jdbcTemplate.execute(DROP_DATA);

		// password is 'user1' SHA hashed and base64 encoded:
		// The first argument to the hash constructor is the actual value to be
		// hased. The 2nd is the
		// salt. In this simple demo scenario, the username and the password are
		// the same, but to clarify the
		// distinction, you would see this in practice:
		// new Sha256Hash( <password>, <cryptographically strong randomly
		// generated salt> (not the username!) )
		String query = "insert into users values ('user1', '" + new Sha256Hash("user1", "user1").toBase64() + "' )";
		jdbcTemplate.execute(query);
		log.debug("Created user1.");

		// password is 'user2' SHA hashed and base64 encoded:
		query = "insert into users values ( 'user2', '" + new Sha256Hash("user2", "user2").toBase64() + "' )";
		jdbcTemplate.execute(query);
		log.debug("Created user2.");

		query = "insert into roles values ( 'role1' )";
		jdbcTemplate.execute(query);
		log.debug("Created role1");

		query = "insert into roles values ( 'role2' )";
		jdbcTemplate.execute(query);
		log.debug("Created role2");

		query = "insert into roles_permissions values ( 'role1', 'permission1')";
		jdbcTemplate.execute(query);
		log.debug("Created permission 1 for role 1");

		query = "insert into roles_permissions values ( 'role1', 'permission2')";
		jdbcTemplate.execute(query);
		log.debug("Created permission 2 for role 1");

		query = "insert into roles_permissions values ( 'role2', 'permission1')";
		jdbcTemplate.execute(query);
		log.debug("Created permission 1 for role 2");

		query = "insert into user_roles values ( 'user1', 'role1' )";
		jdbcTemplate.execute(query);
		query = "insert into user_roles values ( 'user1', 'role2' )";
		jdbcTemplate.execute(query);
		log.debug("Assigned user1 roles role1 and role2");

		query = "insert into user_roles values ( 'user2', 'role2' )";
		jdbcTemplate.execute(query);
		log.debug("Assigned user2 role role2");
	}
}
