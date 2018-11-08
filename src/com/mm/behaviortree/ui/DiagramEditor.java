package com.mm.behaviortree.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.mm.behaviortree.model.ContentsModel;
import com.mm.behaviortree.model.HelloModel;
import com.mm.behaviortree.model.LineConnectionModel;
import com.mm.behaviortree.parts.PartFactory;

public class DiagramEditor extends GraphicalEditorWithPalette{
	
	GraphicalViewer viewer;
	
	public DiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	
	
	@Override
	protected void setInput(IEditorInput input) {
		// TODO Auto-generated method stub
		super.setInput(input);
		System.out.println(input.getName());
		IPathEditorInput i = (IPathEditorInput) input;
		
		System.out.println(i.getPath());
	}

	

	@Override
	protected void createGraphicalViewer(Composite parent) {
		RulerComposite rulerComposite = new RulerComposite(parent, SWT.NONE);
		super.createGraphicalViewer(rulerComposite);
		rulerComposite.setGraphicalViewer((ScrollingGraphicalViewer)getGraphicalViewer());
	}
	


	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		
		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, true);
		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, true);
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


	// 重载GraphicalEditorWithPalette
	@Override
	protected PaletteRoot getPaletteRoot() {
		// (1)首先要创建一个palette的route
		PaletteRoot root = new PaletteRoot();
		
		// (2)创建一个工具组用于放置常规Tools
		PaletteGroup toolGroup = new PaletteGroup("g工具");
		
		// (3)创建一个GEF提供的“selection”工具并将其放到toolGroup中
		ToolEntry tool = new SelectionToolEntry();
		toolGroup.add(tool);
		// 该(选择)工具是缺省被选择的工具
		root.setDefaultEntry(tool);
		
		// (4)创建一个GEF提供的“Marquee多选”工具并将其放到toolGroup中
		
		tool = new MarqueeToolEntry();
		toolGroup.add(tool);
		
		// (5)创建一个Drawer(抽屉)放置绘图工具，该抽屉名称为“画图”
		PaletteDrawer drawer = new PaletteDrawer("画图");
		// 指定”创建HelloModel模型”工具所对应的图标
		ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin("AAB", "icons/sample.png");
		
		// (6)创建”创建HelloModel模型”工具
		CreationToolEntry creationEntry = new CreationToolEntry("绘制HelloModel", "创建HelloModel模型", new SimpleFactory(HelloModel.class), descriptor, descriptor);
		// (7)将其加到前面创建的抽屉中
		drawer.add(creationEntry);
		
		//add the connection tool drawer
		PaletteDrawer connectionDrawer = new PaletteDrawer("链接");
		
		ImageDescriptor newConnectionDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin("AAC", "icons/sample.png");
		// 在这个抽屉里放入 New Connection 工具
		
		ConnectionCreationToolEntry connxCreationEntry = new ConnectionCreationToolEntry("简单链接", "创建简单的链接", new SimpleFactory(LineConnectionModel.class), newConnectionDescriptor, newConnectionDescriptor);
		
		connectionDrawer.add(connxCreationEntry);
		
		// (8)最后将创建的两组工具加到root上.
		root.add(toolGroup);
		root.add(drawer);
		root.add(connectionDrawer);
		
		return root;
	}
	
	private String getSelectPath(){
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = page.getSelection();
		IStructuredSelection sel = (IStructuredSelection)selection;
		Object element = sel.getFirstElement();
		IResource resources = null;
		if (element instanceof IResource) {
			resources = (IResource) element;
		}
		if (!(element instanceof IAdaptable)) {
			return null;
		}
		IAdaptable adaptable = (IAdaptable) element;
		Object adapter = adaptable.getAdapter(IResource.class);
		resources = (IResource) adapter;
		String path = resources.getLocationURI().getPath();
		if (null!=path) {
			if (path.startsWith("/") && path.substring(2, 3).equals(":")) {
				path = path.substring(1);
			}
		}
		return path;
	}

}
