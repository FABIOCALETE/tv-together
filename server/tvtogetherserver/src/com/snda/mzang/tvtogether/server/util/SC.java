package com.snda.mzang.tvtogether.server.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public interface SC {

	String resBase = "/root/mymise/target/";

	SqlSessionFactoryBuilder sqlBuilder = new SqlSessionFactoryBuilder();
	SqlSessionFactory sqlFactory = sqlBuilder.build(SC.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));

}
