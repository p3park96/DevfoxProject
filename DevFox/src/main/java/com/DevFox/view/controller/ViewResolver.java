package com.DevFox.view.controller;

public class ViewResolver {
	
	public String prefix;
	public String sufix;
	
	public void setprefix(String prefix) {
		this.prefix = prefix;
	}
	public void setsufix(String suffix) {
		this.sufix = sufix;
	}
	
	public String getView(String viewName) {
		return prefix + viewName + sufix;
	}
	
	

}
