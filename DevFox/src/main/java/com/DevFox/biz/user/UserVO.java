
package com.DevFox.biz.user;

import java.util.Date;

public class UserVO {
    private int userId;
    private String username;
    private String password;
    private String email;
    private Date regDate;
    private String role;
    
    
    public UserVO(int userId, String username, String password, String email, Date regDate) {
	    this.userId = userId;
	    this.username = username;
	    this.password = password;
	    this.email = email;
	    this.regDate = regDate;
	    
	  }
    
    public UserVO() {}
    
    // getter, setter 메소드 생략
    

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	


}