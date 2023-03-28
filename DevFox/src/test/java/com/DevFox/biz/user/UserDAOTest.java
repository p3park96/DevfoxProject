package com.DevFox.biz.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.DevFox.biz.user.UserVO;
import com.DevFox.biz.user.impl.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDAOTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDAO userDAO;

    private TransactionStatus transactionStatus;

    @Before
    public void setUp() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionStatus = new DataSourceTransactionManager(dataSource).getTransaction(definition);
    }

    @Test
    public void testCreateUser() throws SQLException {
	    try {
        UserVO user = new UserVO();
        user.setUsername("junit");
        user.setPassword("junit");
        user.setEmail("junit@test.com");
        int newId= userDAO.createUser(user);
        user.setUserId(newId);
        UserVO resultuser = userDAO.getUserById(user.getUserId());
        assertNotNull(resultuser);
        assertEquals(user.getUsername(), resultuser.getUsername());
        assertEquals(user.getPassword(), resultuser.getPassword());
        assertEquals(user.getEmail(), resultuser.getEmail());
        System.out.println(user.getUserId());
    } catch (SQLException e) {
        fail(e.getMessage());
    }
	}
    @Test
    public void testGetUserById() throws SQLException {
    	try {
			
    		UserVO user = new UserVO();
    	    user.setUsername("junit2");
            user.setPassword("junit2");
            user.setEmail("junit2@test.com");
    	   int newId= userDAO.createUser(user);
           user.setUserId(newId);
           UserVO resultuser = userDAO.getUserById(user.getUserId());
           assertNotNull(resultuser);
           assertEquals(user.getUsername(), resultuser.getUsername());
           assertEquals(user.getPassword(), resultuser.getPassword());
           assertEquals(user.getEmail(), resultuser.getEmail());
	        int expectedUserId = user.getUserId();
	        int actualUserId = resultuser.getUserId();
	        assertEquals(expectedUserId, actualUserId);
		    System.out.println(resultuser.getUserId());
	    } catch (SQLException e) {
	        fail(e.getMessage());
	    }
	}
    

    @Test
    public void testGetAllUsers() throws SQLException {
    	try {
        List<UserVO> userList = userDAO.getAllUsers();
        assertNotNull(userList);
        assertTrue(userList.size() > 0);
        System.out.println(userList.get(userList.size()-1).getUserId());
	} catch (SQLException e) {
		fail(e.getMessage());
	}
}


    @Test
    public void testUpdateUser() throws SQLException {
  	  try {
        UserVO user = new UserVO();
        user.setUsername("junit3");
        user.setPassword("junit3");
        user.setEmail("junit3@test.com");
        int newId= userDAO.createUser(user);
        user.setUserId(newId);
        user.setUsername("update");
        user.setPassword("update");
        user.setEmail("update@test.com");
        userDAO.updateUser(user);
        UserVO resultuser = userDAO.getUserById(user.getUserId());
        assertNotNull(resultuser);
        assertEquals(user.getUsername(), resultuser.getUsername());
        assertEquals(user.getPassword(), resultuser.getPassword());
        assertEquals(user.getEmail(), resultuser.getEmail());
    }catch (SQLException e) {
	    fail(e.getMessage());
	  }
	}
    
 

    @Test
    public void testDeleteUser() throws SQLException {
	    try {
        UserVO user = new UserVO();
        user.setUsername("junit4");
        user.setPassword("junit4");
        user.setEmail("junit4@test.com");
        int newId= userDAO.createUser(user);
        user.setUserId(newId);
        System.out.println(user.getUserId());
        userDAO.deleteUser(user.getUserId());
        assertNull(userDAO.getUserById(user.getUserId()));
    } catch (SQLException e) {
        fail(e.getMessage());
    }
}
}