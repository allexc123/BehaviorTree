package com.mm.behaviortree.policy;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

import com.mm.behaviortree.commands.CreateBendpointCommand;
import com.mm.behaviortree.commands.DeleteBendpointCommand;
import com.mm.behaviortree.commands.MoveBendpointCommand;

public class CustomBendpointEditPolicy extends BendpointEditPolicy {

	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		
		// 获得增加 bend 点的位置
		Point point = request.getLocation();
		getConnection().translateToRelative(point);
		
		CreateBendpointCommand command = new CreateBendpointCommand();
		command.setLocation(point);
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		DeleteBendpointCommand command=new DeleteBendpointCommand();
        command.setConnection(getHost().getModel());
        command.setIndex(request.getIndex());
        return command;
	}

	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		 Point point = request.getLocation();
	        getConnection().translateToRelative(point);
	        
	        MoveBendpointCommand command = new MoveBendpointCommand();
	        command.setNewLocation(point);
	        command.setConnection(getHost().getModel());
	        command.setIndex(request.getIndex());
	        return command;
	}

}
