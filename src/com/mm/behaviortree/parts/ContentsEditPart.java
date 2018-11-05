package com.mm.behaviortree.parts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.mm.behaviortree.model.ContentsModel;
import com.mm.behaviortree.policy.CustomXYLayoutEditPolicy;


public class ContentsEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		
		Layer figure = new Layer();
		figure.setLayoutManager(new XYLayout());
		return figure;

	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new CustomXYLayoutEditPolicy());
	}

	@Override
	protected List getModelChildren() {
		
		return ((ContentsModel)getModel()).getChildren();
	}

	

}
