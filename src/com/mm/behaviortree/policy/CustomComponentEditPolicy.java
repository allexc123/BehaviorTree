package com.mm.behaviortree.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.mm.behaviortree.commands.DeleteCommand;

public class CustomComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		DeleteCommand deleteCommand = new DeleteCommand();
		deleteCommand.setContentsModel(getHost().getParent().getModel());
		deleteCommand.setHelloModel(getHost().getModel());
		return deleteCommand;
	}

	
}
