package com.mm.behaviortree.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.AbstractConnectionModel;
import com.mm.behaviortree.model.ContentsModel;
import com.mm.behaviortree.model.HelloModel;

public class DeleteCommand extends Command {
	private ContentsModel contentsModel;
	private HelloModel helloModel;
	
	private List sourceConnections=new ArrayList(); 
	private List targetConnections=new ArrayList();

	@Override
	public void execute() {
		
		//‐‐‐‐‐‐‐‐‐ delete the connection ‐‐‐‐‐‐‐‐‐‐‐‐‐ //在删除一个模型对象前，搜索把这个模型对象作为起点和终点的所有连接 
		sourceConnections.addAll(helloModel.getModelSourceConnections()); 
		targetConnections.addAll(helloModel.getModelTargetConnections()); //删除该模型对象对应的 source
		for(int i=0;i<sourceConnections.size();i++){
			AbstractConnectionModel model=(AbstractConnectionModel)sourceConnections.get(i); model.detachSource();
			model.detachTarget();
		}
		//删除该模型对象对应的 target
		for(int i=0;i<targetConnections.size();i++){
			AbstractConnectionModel model=(AbstractConnectionModel)targetConnections.get(i); model.detachSource();
			model.detachTarget();
		}
		
		contentsModel.removeChild(helloModel);
	}

	public void setContentsModel(Object contentsModel) {
		this.contentsModel = (ContentsModel) contentsModel;
	}

	public void setHelloModel(Object helloModel) {
		this.helloModel = (HelloModel) helloModel;
	}

	@Override
	public void undo() {
		
		contentsModel.addChild(helloModel);
	}

	
	
}
