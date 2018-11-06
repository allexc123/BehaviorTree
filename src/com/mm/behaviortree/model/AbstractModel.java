package com.mm.behaviortree.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class AbstractModel implements IPropertySource{
	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	public void firePropertyChange(String propName, Object oldValue, Object newValue) {
		listeners.firePropertyChange(propName, oldValue, newValue);
	}
	public void removePropertyChangeListenner(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	@Override
	public Object getEditableValue() {
		return this;// // 返回模型自身作为可编辑的属性值
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// 如果在抽象模型中返回null会出现异常，因此这里返回一个长度为0的数组
		// 后面将介绍IPropertyDescriptor数组
		return new IPropertyDescriptor[0];
	}

	@Override
	public Object getPropertyValue(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPropertySet(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetPropertyValue(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPropertyValue(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
