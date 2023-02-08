package com.yedam.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {

	// 필드
	private static SqlSessionFactory sqlSessionFactory;

	// 생성자
	private DataSource() {}

	// 메소드
	public static SqlSessionFactory getInstance() {

		String resource = "config/mybatis-config.xml";
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {

			e.printStackTrace();

		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
}
