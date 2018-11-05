package com.mm.behaviortree.model;

import org.eclipse.draw2d.geometry.Rectangle;

public class HelloModel extends AbstractModel{
	private String text = "Hello World";
	private Rectangle constaint; //约束

	public static final String P_CONSTRAINT = "_constraint";
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Rectangle getConstaint() {
		return constaint;
	}

	public void setConstaint(Rectangle rect) {
		this.constaint = rect;
		
		firePropertyChange(P_CONSTRAINT, null, constaint);
	}
	
	
}
