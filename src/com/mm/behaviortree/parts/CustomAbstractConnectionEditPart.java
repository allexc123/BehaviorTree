package com.mm.behaviortree.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import com.mm.behaviortree.model.AbstractConnectionModel;
import com.mm.behaviortree.policy.CustomBendpointEditPolicy;
import com.mm.behaviortree.policy.CustomConnectionEditPolicy;
import com.mm.behaviortree.policy.CustomConnectionEndpointEditPolicy;

public class CustomAbstractConnectionEditPart extends AbstractConnectionEditPart implements PropertyChangeListener{
	
	

	@Override
	protected IFigure createFigure() {
		PolylineConnection connection = new PolylineConnection();
		connection.setConnectionRouter(new BendpointConnectionRouter());
		return connection;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new CustomConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new CustomConnectionEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new CustomBendpointEditPolicy());

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(AbstractConnectionModel.P_BEND_POINT)) {
			// 刷新控制点
			refreshBendpoints();
			
		}
		
	}
	protected void refreshBendpoints() {
		// 首先获得bending点的位置
		List bendpoints = ((AbstractConnectionModel) getModel()).getBendpoints();
		// 控制点的列表
		List constraint = new ArrayList();
		for (int i = 0; i < bendpoints.size(); i++) {
			// 根本连接模型的数据创建一个控制点
			constraint.add(new AbsoluteBendpoint((Point) bendpoints.get(i)));
		}
		// 创建一个连接，把刚才生成的控制点作为约束 
		getConnectionFigure().setRoutingConstraint(constraint);
	}
	
	// 重载 activate，注册 PropertyChange
	@Override
	public void activate() {
		
		super.activate();
		((AbstractConnectionModel)getModel()).addPropertyChangeListener(this);
	}
	// 重载 deactivate，删除 PropertyChange
	@Override
	public void deactivate() {
		((AbstractConnectionModel)getModel()).removePropertyChangeListenner(this);
		super.deactivate();
	}

	@Override
	protected void refreshVisuals() {
		refreshBendpoints();
	}

	

}
