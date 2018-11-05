package com.mm.behaviortree.parts;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


public class ContentsEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		Layer figure = new Layer();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}


}
