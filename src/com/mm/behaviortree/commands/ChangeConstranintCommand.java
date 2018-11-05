package com.mm.behaviortree.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.HelloModel;

public class ChangeConstranintCommand extends Command {
	private HelloModel helloModel;
	private Rectangle constraint;
	private Rectangle oldConstraint;

	@Override
	public void execute() {
		helloModel.setConstaint(constraint);
	}
	
	public void setConstranint(Rectangle rect) {
		this.constraint = rect;
		oldConstraint = helloModel.getConstaint();
	}
	
	public void setModel(Object model) {
		this.helloModel = (HelloModel) model;
	}

	@Override
	public void undo() {
		helloModel.setConstaint(oldConstraint);
	}
	
	
}
