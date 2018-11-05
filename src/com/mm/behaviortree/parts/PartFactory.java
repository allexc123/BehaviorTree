package com.mm.behaviortree.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.mm.behaviortree.model.ContentsModel;
import com.mm.behaviortree.model.HelloModel;

public class PartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = getPartForElement(model);
		part.setModel(model);
		return part;
	}
	
	private EditPart getPartForElement(Object modelElement) {
		if(modelElement instanceof HelloModel) {
			return new HelloEditorPart();
		}else if(modelElement instanceof ContentsModel) {
			return new ContentsEditPart();
		}
		
		throw new RuntimeException("Can't create part  from model element");
	}
	
}
