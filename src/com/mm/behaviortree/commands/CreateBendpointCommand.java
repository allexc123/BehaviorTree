package com.mm.behaviortree.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.AbstractConnectionModel;

public class CreateBendpointCommand extends Command {
	private AbstractConnectionModel connection; 
	private Point location; //bend 点的位置
	private int index; //bend 点的索引

	@Override
	public void execute() {
		connection.addBendpoint(index, location);
	}
	
	public void setConnection(Object object) {
		this.connection = (AbstractConnectionModel) object;
	}
	
	public void setIndex(int i) {
		this.index = i;
	}
	
	public void setLocation(Point loc) {
		this.location = loc;
	}
	
	@Override
	public void undo() {
		connection.removeBendpoint(index);
	}
}

