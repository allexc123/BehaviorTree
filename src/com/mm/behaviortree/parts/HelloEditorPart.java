package com.mm.behaviortree.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.mm.behaviortree.model.HelloModel;
import com.mm.behaviortree.policy.CustomComponentEditPolicy;
import com.mm.behaviortree.policy.CustomGraphicalNodeEditPolicy;

public class HelloEditorPart extends EditPartWithListener implements NodeEditPart{

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
		
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CustomComponentEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CustomGraphicalNodeEditPolicy());
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
		}else if(evt.getPropertyName().equals(HelloModel.P_TEXT)) {
			Label lable = (Label) getFigure();
			lable.setText((String)evt.getNewValue());
		}else if(evt.getPropertyName().equals(HelloModel.P_SOURCE_CONNECTION)) {
			refreshSourceConnections();
		}else if(evt.getPropertyName().equals(HelloModel.P_TARGET_CONNECTION)) {
			refreshTargetConnections();
		}
		
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart Connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart Connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	protected List getModelSourceConnections() {
		
		return ((HelloModel)getModel()).getModelSourceConnections();
	}

	@Override
	protected List getModelTargetConnections() {
		
		return ((HelloModel)getModel()).getModelTargetConnections();
	}

	
	
}
