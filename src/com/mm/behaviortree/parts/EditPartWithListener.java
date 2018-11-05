package com.mm.behaviortree.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.mm.behaviortree.model.AbstractModel;

public abstract class EditPartWithListener extends AbstractGraphicalEditPart implements PropertyChangeListener{


	@Override
	public void activate() {
		
		super.activate();
		((AbstractModel)getModel()).addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		
		super.deactivate();
		((AbstractModel)getModel()).removePropertyChangeListenner(this);
	}
	
	

}
