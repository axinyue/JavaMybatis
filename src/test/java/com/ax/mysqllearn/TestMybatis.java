package com.ax.mysqllearn;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import com.ax.mysqllearn.Mapper.ZoneMapper;
import com.ax.mysqllearn.dao.Zone;

public class TestMybatis {
	SqlSessionFactory sqlSessionFactory;
	
	/**
	 * Mybatis使用配置文件
	 */
	@Test
	public void Test1() {
		String resource = "resources/mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		
		Zone zone = (Zone)sqlSession.selectOne("com.ax.mysqllearn.Mapper.ZoneMapper.selectOne",1001);
		System.out.println(zone.getZoneName());
	}
	
	/**
	 * 去除XML配置的Mybatis使用
	 */
	@Test
	public void test2() {
		//创建dataSource
		String driver = "com.mysql.jdbc.Driver";  
		String dburl = "jdbc:mysql://192.168.0.122:3306/test_platform"; 
		String user = "root";
		String password = "root";
		
		// UnpooledDataSource 是在这个 org.apache.ibatis.datasource 包里选的
		//这里使用代码创建连接 并注册Mapper接口(Mapper 接口需要使用 org.apache.ibatis.annotations下的注解关联SQL语句)
		DataSource dataSource = new UnpooledDataSource(driver,dburl,user,password);
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		
		//使用代码注册 Mapper接口
		configuration.addMapper(ZoneMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();

		ZoneMapper mapper = sqlSession.getMapper(ZoneMapper.class);
		Zone zone = mapper.selectOne(1001);
		System.out.println(zone.getZoneName());

	}
	
	
}
