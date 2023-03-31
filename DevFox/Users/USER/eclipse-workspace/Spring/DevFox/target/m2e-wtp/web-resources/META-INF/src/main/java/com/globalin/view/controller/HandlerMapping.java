package com.globalin.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.globalin.view.board.GetBoardListContoller;
import com.globalin.view.user.LoginController;

/*
 * 모든 컨트롤러 객체 저장, 사용자 요청이 들어오면 요청을 처리할
 * 특정한 컨트롤러를 검색하는 기능을 제공함
 * Map 타입의 컬렉션 멤버변수로 가지고 있음 
 * 
 * 
 */

public class HandlerMapping {
	
	private Map<String, Controller> mappings;
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/getGoardList.do", new GetBoardListContoller());
		
		
	}

	
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
