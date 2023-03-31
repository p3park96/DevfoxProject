package com.DevFox.biz.user.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DevFox.biz.user.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class UserDAOTest {

	 @Autowired
	    private UserDAO userDAO;
	 
	 

	    @Before
	    public void setUp() {
	    }
    @Test
    public void testInsertUser() {
        // given
        UserVO vo = new UserVO();
        vo.setId("4테스트 제목");
        vo.setName("4테스트 작성자");
        vo.setPassword("4테스트 내용");

        // when
        userDAO.insertUser(vo);

        // then
        assertNotNull(vo.getNo());
    }

    @Test
    public void testUpdateUser() {
        // given
        UserVO vo = new UserVO();
        vo.setNo(2);
        vo.setId("1수정된 제목");
        vo.setPassword("1수정된 내용");

        // when
        userDAO.updateUser(vo);

        // then
        UserVO updatedVO = userDAO.getUser(vo);
        assertEquals(vo.getId(), updatedVO.getId());
        assertEquals(vo.getPassword(), updatedVO.getPassword());

        // Rollback
        vo.setId("1처음 제목");
        vo.setPassword("1처음 내용");
        userDAO.updateUser(vo);
    }
/*
    @Test
    public void testDeleteUser() {
        // given
        UserVO vo = new UserVO();
        vo.setNo(2);

        // when
        userDAO.deleteUser(vo);

        // then
        UserVO deletedVO = userDAO.getUser(vo);
        assertNull(deletedVO);
    }
*/
    @Test
    public void testGetUser() {
        // given
        UserVO vo = new UserVO();
        vo.setId("admin");
        vo.setPassword("admin");
        
        // when
        userDAO.getUser(vo);
        System.out.println(userDAO.getUser(vo).getNo());

        // then
        assertNotNull(vo);
        assertNotNull(userDAO.getUser(vo).getNo());
        assertNotNull(userDAO.getUser(vo).getId());
        assertNotNull(userDAO.getUser(vo).getName());
        assertNotNull(userDAO.getUser(vo).getPassword());
    }
    @Test
    public void testGetUserList() {
        // given
        UserVO vo = new UserVO();

        // when
        List<UserVO> userList = userDAO.getUserList(vo);

        // then
        assertNotNull(userList);
        assertTrue(userList.size() > 0);
    }

}