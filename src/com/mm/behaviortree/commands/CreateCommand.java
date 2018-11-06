package com.mm.behaviortree.commands;

import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.ContentsModel;
import com.mm.behaviortree.model.HelloModel;

public class CreateCommand extends Command {
	private ContentsModel contentsModel;
	private HelloModel helloModel;

	@Override
	public void execute() {
		contentsModel.addChild(helloModel);
	}
	
	public void setContentsModel(Object contentsModel) {
		this.contentsModel = (ContentsModel) contentsModel;
	}

	public void setHelloModel(Object helloModel) {
		this.helloModel = (HelloModel) helloModel;
	}

	@Override
	public void undo() {
		
		contentsModel.removeChild(helloModel);
	}
	
}
