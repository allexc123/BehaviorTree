package com.mm.behaviortree.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditor;

import com.mm.behaviortree.model.HelloModel;
import com.mm.behaviortree.parts.PartFactory;

public class DiagramEditor extends GraphicalEditor{
	
	GraphicalViewer viewer;
	
	public DiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		 viewer  = getGraphicalViewer();
		 viewer.setEditPartFactory(new PartFactory());
	}



	@Override
	protected void initializeGraphicalViewer() {
		
		viewer.setContents(new HelloModel());
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub
		
	}

}
