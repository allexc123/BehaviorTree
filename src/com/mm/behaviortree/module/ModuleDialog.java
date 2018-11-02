package com.mm.behaviortree.module;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ModuleDialog extends Dialog{
	
	private String path;
	private Text namePatternField;
	
	public ModuleDialog(Shell parentShell, String path) {
		super(parentShell);
		this.path = path;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		container.setLayout(gridLayout);
		
		final Label filterLabel = new Label(container, SWT.NONE);
		filterLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER,false, false, 2, 1));
		filterLabel.setText("xxxxxxxxxxxxxxx");
		
		Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setLayoutData(new GridData(GridData.END, GridData.CENTER, false, false));
		nameLabel.setText("Name:");
		
		this.namePatternField = new Text(container, SWT.BORDER);
		namePatternField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		Composite checkBoxComposite = new Composite(container, SWT.NONE);
		GridData checkBoxGridData = new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1);
		checkBoxGridData.horizontalIndent = 20;
		checkBoxComposite.setLayoutData(checkBoxGridData);
		GridLayout checkBoxGridLayout = new GridLayout();
		checkBoxGridLayout.numColumns = 1;
		checkBoxComposite.setLayout(checkBoxGridLayout);
		
		createCheckBoxs(checkBoxComposite);
		
		return container;
	}
	
	private void createCheckBoxs(Composite parent) {
		
		final Button button = new Button(parent, SWT.CHECK);
		button.setText("create Cmd");
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button.getSelection()) {
					System.out.println("选择");
				}else {
					System.out.println("放弃");
				}
			}
			
		});
	}

	@Override
	protected void okPressed() {
		
		System.out.println(" 开始创建---------");
		String name = namePatternField.getText();
		CreateModule createModule = new CreateModule(path, name);
		createModule.create();
		
		super.okPressed();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("new Module");
	}
	
	
}
