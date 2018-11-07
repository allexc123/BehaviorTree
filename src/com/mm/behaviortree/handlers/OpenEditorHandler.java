package com.mm.behaviortree.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.mm.behaviortree.ui.DiagramEditorInput;




public class OpenEditorHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		try {
			String path = getSelectPath();
			IEditorInput input = new DiagramEditorInput(new Path(path));
			
			((IWorkbenchWindow) Activator.getDefault()).getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input, "com.mm.behaviortree.ui.DiagramEditor");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
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
