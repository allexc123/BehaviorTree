package com.mm.behaviortree.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditor;

import com.mm.behaviortree.model.ContentsModel;
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
		
		//viewer.setContents(new HelloModel());
		//设置最上层模型
		ContentsModel parent = new ContentsModel();
		//创建一个子模型
		HelloModel child1 = new HelloModel();
		child1.setConstaint(new Rectangle(0, 0, -1, -1));
		parent.addChild(child1);
		
		HelloModel child2 = new HelloModel();
		child2.setConstaint(new Rectangle(30, 30, -1, -1));
		parent.addChild(child2);
		
		HelloModel child3 = new HelloModel();
		child3.setConstaint(new Rectangle(10, 80, 80, 50));
		parent.addChild(child3);
		
		viewer.setContents(parent);
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub
		
	}

}
