package com.mm.behaviortree.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.mm.behaviortree.model.HelloModel;

public class HelloEditorPart extends EditPartWithListener {

	@Override
	protected IFigure createFigure() {
		HelloModel model = (HelloModel) getModel();
		 
		Label label = new Label();
		label.setText(model.getText());
		
		label.setBorder(new CompoundBorder(new LineBorder(), new MarginBorder(3)));
		
		label.setBackgroundColor(ColorConstants.orange);
		
		label.setOpaque(true);

		return label;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void refreshVisuals() {
		Rectangle constaint = ((HelloModel)getModel()).getConstaint();
		
		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), constaint);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getPropertyName().equals(HelloModel.P_CONSTRAINT)) {
			refreshVisuals();
		}
		
	}

	
}
