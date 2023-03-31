package com.DevFox.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.DevFox.view.user.LoginController;
import com.DevFox.view.board.GetBoardListContoller;



public class HandlerMapping {
	
	private Map<String, Controller> mappings;
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", (Controller) new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListContoller());
		
		
	}

	
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
