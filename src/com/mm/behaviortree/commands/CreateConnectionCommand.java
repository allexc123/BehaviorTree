package com.mm.behaviortree.commands;

import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.AbstractConnectionModel;
import com.mm.behaviortree.model.AbstractModel;
import com.mm.behaviortree.model.HelloModel;

public class CreateConnectionCommand extends Command {
	//this connectionʹs tip is connected to source 链接的尾端添加到 target
	private HelloModel source,target;
	//连接的模型
	private AbstractConnectionModel connection;
	
	
	
	@Override
	public boolean canExecute() {
		if(source == null || target == null) {
			return false;
		}
		if(source.equals(target)) {
			return false;
		}
		return true;
	}
	
	@Override
	public void execute() {
		//执行的时候分两步:连接起点和连接终点
		connection.attachSource();
		connection.attachTarget();
	
	}

	public void setConnection(Object model) {
		this.connection = (AbstractConnectionModel) model;
	}
	public void setSource(Object model) {
		this.source = (HelloModel) model;
		this.connection.setSource(source);
	}
	public void setTarget(Object model) {
		this.target = (HelloModel) model;
		this.connection.setTarget(target);
	}

	@Override
	public void undo() {
		connection.detachSource();
		connection.detachTarget();
	}


	
	
	
}
