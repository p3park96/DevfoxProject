package com.DevFox.biz.user.impl;

import java.sql.*;
import javax.sql.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class Test {

    private DataSource dataSource;

    public void testConnection() throws SQLException {
    	
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
    	if (context == null) {
    	    System.out.println("Failed to load applicationContext.xml");
    	}else {
    	    System.out.println("Loaded applicationContext.xml successfully");
    	    Test test = context.getBean(Test.class);
    	    System.out.println("빈가져온다");
            test.setDataSource(context.getBean("dataSource", DataSource.class));
            test.testConnection();
    	}
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}