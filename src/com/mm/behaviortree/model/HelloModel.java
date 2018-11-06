package com.mm.behaviortree.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Triangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class HelloModel extends AbstractModel{
	private String text = "Hello World";
	private Rectangle constaint; //约束

	public static final String P_CONSTRAINT = "_constraint";
	public static final String P_TEXT = "_text";
	
	public static final String P_SOURCE_CONNECTION="_source_connection"; 
	public static final String P_TARGET_CONNECTION="_target_connection";
	
	private List sourceConnection=new ArrayList();
	private List targetConnection=new ArrayList();
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		// 图形文本改变时通知其 EditPart
		firePropertyChange(P_TEXT, null, text);
	}

	public Rectangle getConstaint() {
		return constaint;
	}

	public void setConstaint(Rectangle rect) {
		this.constaint = rect;
		
		firePropertyChange(P_CONSTRAINT, null, constaint);
	}

	// 下面时重载 IPropertySource 接口的方法
	// 其实 Property View 中用 TableView 来显示属性。第一列是属性名称，第 2 列是属性值。
	// IPropertyDescriptor[]数组顾名思义就是用来设置属性名称的。这里我们只提供了一个属性， 
	// 并命名为 Greeting
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] descriptors= new IPropertyDescriptor[] {new TextPropertyDescriptor(P_TEXT, "Greeting")};
		
		return descriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(id.equals(P_TEXT)) {
			// 这里获得 Property view 中文本属性的值
			return text;
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		if(id.equals(P_TEXT)) {
			return true;
		}
		return false;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if(id.equals(P_TEXT)) {
			setText((String)value);
		}
	}
	
	//connection
	
	public void addSourceConnection(Object connx) {
		sourceConnection.add(connx);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}
	
	public void addTargetConnection(Object connx) {
		targetConnection.add(connx);
		firePropertyChange(P_TARGET_CONNECTION, null, null);
	}
	
	public List getModelSourceConnections() {
		return sourceConnection;
	}
	
	public List getModelTargetConnections() {
		return targetConnection;
	}
	
	public void removeSourceConnection(Object connx) {
		sourceConnection.remove(connx);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}
	
	public void removeTargetConnection(Object connx) {
		targetConnection.remove(connx);
		firePropertyChange(P_TARGET_CONNECTION, null, null);
	}
	
}
