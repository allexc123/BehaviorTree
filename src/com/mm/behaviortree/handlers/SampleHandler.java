package com.mm.behaviortree.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		getSelectPath();
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"BehaviorTree",
				"Hello, Eclipse world");
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
		System.out.println(path);
		return path;
	}
}
