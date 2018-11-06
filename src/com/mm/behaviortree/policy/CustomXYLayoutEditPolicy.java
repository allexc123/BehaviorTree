package com.mm.behaviortree.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import com.mm.behaviortree.commands.ChangeConstranintCommand;
import com.mm.behaviortree.commands.CreateCommand;
import com.mm.behaviortree.model.HelloModel;

public class CustomXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		CreateCommand command = new CreateCommand();
		// 产生创建图形的尺寸和位置
		Rectangle constraint = (Rectangle) getConstraintFor(request);
		
		// 获得新创建的图形
		HelloModel model = (HelloModel) request.getNewObject();
		// 为该图形设置前面获得的位置和尺寸
		model.setConstaint(constraint);
		// 将新创建的图形添加到模型中，
		// 因为我们在第 2 页的(2)中已经把模型更改和它们的 Editpart 联系起来， 
		// 因此，Graphical Editor 中的图形也会发生变化
		command.setContentsModel(getHost().getModel());
		command.setHelloModel(model);
		return command;
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
