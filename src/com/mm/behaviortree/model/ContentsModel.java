package com.mm.behaviortree.model;

import java.util.ArrayList;
import java.util.List;

public class ContentsModel extends AbstractModel{
	public static final String P_CHILDREN = "_children";
	
	private List<Object> children  = new ArrayList<>(); //子模型列表
	
	
	//添加子模型
	public void addChild(Object child) {
		this.children.add(child); 
		firePropertyChange(P_CHILDREN, null, null);
	}
	//获取子模型
	public List getChildren() {
		return this.children;
	}
	//删除一个子模型
	public void removeChild(Object child) {
		//删除子模型
		children.remove(child);
		//通知EditPart
		firePropertyChange(P_CHILDREN, null, null);
	}
}
