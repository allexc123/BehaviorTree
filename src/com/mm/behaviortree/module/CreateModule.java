package com.mm.behaviortree.module;

import java.io.File;
import java.io.IOException;

/**
 * 创建模块
 * @author myb
 *
 */
public class CreateModule {
	private String path;
	private String name;
	

	public CreateModule(String path, String name) {
		super();
		this.path = path;
		this.name = name;
	}
	public boolean create() {
		if(createDir(name)) {
			this.path =  path+ "/"+ name;
		}
		createDir("cmd");
		createDir("template");
		createDir("entity");
		createFile(name + "Inventory");
		createFile(name + "Contexnt");
		return true;
	}
	private boolean createDir(String name) {
		String newPath = path+ "/"+ name;
		File dir = new File(newPath);
		if(dir.exists()) { //判断目录是否存在
			return false;
		}
		if(dir.mkdir()) {
			return true;
		}
		
		return false;
	}
	
	private boolean createFile(String name) {
		String newFile = path+ "/"+ name +".java";
		File file = new File(newFile);
		if(file.exists()) {
			return false;
		}
		try {
			if(file.createNewFile()) {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
