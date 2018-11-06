package com.mm.behaviortree.commands;

import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.AbstractConnectionModel;

public class DeleteConnectionCommand extends Command {
	private AbstractConnectionModel connection;

	@Override
	public void execute() {
		connection.detachSource();
		connection.detachTarget();
	}
	public void setConnectionModel(Object model) {
		connection = (AbstractConnectionModel)model;
	}

	@Override
	public void undo() {
		connection.attachSource();
		connection.attachTarget();
	}

	
}
