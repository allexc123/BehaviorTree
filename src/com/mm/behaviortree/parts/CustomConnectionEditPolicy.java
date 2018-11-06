package com.mm.behaviortree.parts;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.mm.behaviortree.commands.DeleteConnectionCommand;

public class CustomConnectionEditPolicy extends ConnectionEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest arg0) {
		DeleteConnectionCommand command = new DeleteConnectionCommand();
		command.setConnectionModel(getHost().getModel());
		
		return command;
	}

}
