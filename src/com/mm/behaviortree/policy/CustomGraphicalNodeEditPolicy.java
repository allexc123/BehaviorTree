	package com.mm.behaviortree.policy;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.mm.behaviortree.commands.CreateConnectionCommand;
import com.mm.behaviortree.commands.ReconnectConnectionCommand;

public class CustomGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		//命令是从 request 中获得
	 	CreateConnectionCommand command = (CreateConnectionCommand)request.getStartCommand();
	 	
	 	command.setTarget(getHost().getModel());
		return command;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		 CreateConnectionCommand command = new CreateConnectionCommand();
		 
		 command.setConnection(request.getNewObject());
		 command.setSource(getHost().getModel());
		 
		 request.setStartCommand(command);
		return command;
	}
	



	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		// 拖动句柄重新连接的 source 端
		ReconnectConnectionCommand command = new ReconnectConnectionCommand();
		command.setConnectionModel(request.getConnectionEditPart().getModel());
		command.setNewSource(getHost().getModel());
		return command;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		// 拖动句柄重新连接的 Target 端
		ReconnectConnectionCommand command = new ReconnectConnectionCommand();
		command.setConnectionModel(request.getConnectionEditPart().getModel());
		command.setNewTarget(getHost().getModel());
		return command;
	}

}
