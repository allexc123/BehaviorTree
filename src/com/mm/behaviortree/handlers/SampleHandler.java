package com.mm.behaviortree.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.mm.behaviortree.module.ModuleDialog;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String path = null;
		String selectPath = getSelectPath();
		File file = new File(selectPath);
		path = file.getAbsolutePath();
		if(file.isFile()) {
			path = file.getParent();
		}
		System.out.println(path);
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		Shell shell = window.getShell();
		ModuleDialog dialog = new ModuleDialog(shell, path);
		if(dialog.open() !=  InputDialog.OK) {
			System.out.println("失败");
		}
		
		
//		MessageDialog.openInformation( 
//				window.getShell(),
//				"BehaviorTree",
//				"Hello, Eclipse world");
		return null;
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
