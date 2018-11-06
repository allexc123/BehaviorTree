package com.mm.behaviortree.commands;

import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.AbstractConnectionModel;
import com.mm.behaviortree.model.HelloModel;

public class ReconnectConnectionCommand extends Command {

	private AbstractConnectionModel connection;
	private HelloModel newSource,newTarget, oldSource, oldTarget;
	
	

	@Override
	public boolean canExecute() {
		if(newSource != null && !newSource.equals(oldTarget)) {
			return true;
		}else if(newTarget != null && !newTarget.equals(oldSource)) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(newSource != null) {
			connection.detachSource();
			connection.setSource(newSource);
			connection.attachSource();
		}else if(newTarget != null) {
			connection.detachTarget();
			connection.setTarget(newTarget);
			connection.attachTarget();
			
		}
	}
	
	public void setConnectionModel(Object model) {
		this.connection = (AbstractConnectionModel) model;
		oldSource = connection.getSource();
		oldTarget = connection.getTarget();
	}
	public void setNewSource(Object model) {
		this.newSource = (HelloModel) model;
	}
	
	public void setNewTarget(Object model) {
		this.newTarget = (HelloModel) model;
	}

	@Override
	public void undo() {
		
	}
	
	
}
