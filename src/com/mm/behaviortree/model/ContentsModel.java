package com.mm.behaviortree.model;

import java.util.ArrayList;
import java.util.List;

public class ContentsModel {
	private List<Object> children  = new ArrayList<>(); //子模型列表
	
	//添加子模型
	public void addChild(Object child) {
		this.children.add(child); 
	}
	//获取子模型
	public List getChildren() {
		return this.children;
	}
}
