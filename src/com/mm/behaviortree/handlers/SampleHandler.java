package com.mm.behaviortree.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mm.behaviortree.module.ModuleDialog;
import com.mm.behaviortree.ui.DiagramEditorInput;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class SampleHandler extends AbstractHandler {
	IWorkbenchWindow window;
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		String path = null;
//		String selectPath = getSelectPath();
//		File file = new File(selectPath);
//		path = file.getAbsolutePath();
//		if(file.isFile()) {
//			path = file.getParent();
//		}
//		System.out.println(path);
//		
//		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		Shell shell = window.getShell();
//		ModuleDialog dialog = new ModuleDialog(shell, path);
//		if(dialog.open() !=  InputDialog.OK) {
//			System.out.println("失败");
//		}
//		
		
//		MessageDialog.openInformation( 
//				window.getShell(),
//				"BehaviorTree",
//				"Hello, Eclipse world");
		
		String path = optenFileDialog();
		if(path != null) {
			IEditorInput input = new DiagramEditorInput(new Path(path));
            IWorkbenchPage page = window.getActivePage();
            try{
                page.openEditor(input, "com.mm.behaviortree.ui.DiagramEditor",true);
            }catch(Exception e){
                System.err.println(e);
            }
		}
		
		return null;
	}
	
	
	private String optenFileDialog() {
		 FileDialog dialog = new FileDialog(window.getShell(), SWT.OPEN);
		 dialog.setText("AAADDD");
		 dialog.setFilterExtensions(new String[] {".diagram"});
		 return dialog.open();
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
