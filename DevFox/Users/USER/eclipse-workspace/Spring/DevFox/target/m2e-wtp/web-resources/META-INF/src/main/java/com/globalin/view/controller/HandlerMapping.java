package com.globalin.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.globalin.view.board.GetBoardListContoller;
import com.globalin.view.user.LoginController;

/*
 * ��� ��Ʈ�ѷ� ��ü ����, ����� ��û�� ������ ��û�� ó����
 * Ư���� ��Ʈ�ѷ��� �˻��ϴ� ����� ������
 * Map Ÿ���� �÷��� ��������� ������ ���� 
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
