package com.mm.behaviortree.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.mm.behaviortree.commands.ChangeConstranintCommand;

public class CustomXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command createAddCommand(ChangeBoundsRequest request, EditPart child, Object constraint) {
		// TODO Auto-generated method stub
		return super.createAddCommand(request, child, constraint);
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		
		 ChangeConstranintCommand command = new ChangeConstranintCommand();
		 command.setModel(child.getModel());
		 command.setConstranint((Rectangle)constraint);
		return command;
	}

	@Override
	protected Command getDeleteDependantCommand(Request request) {
		// TODO Auto-generated method stub
		return super.getDeleteDependantCommand(request);
	}
	

}
