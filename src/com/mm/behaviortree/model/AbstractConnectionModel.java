package com.mm.behaviortree.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

public abstract class AbstractConnectionModel extends AbstractModel{

	private HelloModel source, target;	
	
	// bending 点位置
	private List bendpoints = new ArrayList();
	// 标识bending点位置改变的ID
	public static final String P_BEND_POINT = "_bend_point";
	
	
	//this connectionʹs root is connected to source 链接的头端添加到 source
	public void attachSource() {
		if(!source.getModelSourceConnections().contains(this)) {
			source.addSourceConnection(this);
		}
	}
	//this connectionʹs tip is connected to source 链接的尾端添加到 target
	public void attachTarget() {
		if(!target.getModelTargetConnections().contains(this)) {
			target.addTargetConnection(this);
		}
	}
	public void detachSource() {
		source.removeSourceConnection(this);
	}
	public void detachTarget() {
		target.removeTargetConnection(this);
	}
	public HelloModel getSource() {
		return source;
	}
	public void setSource(HelloModel source) {
		this.source = source;
	}
	public HelloModel getTarget() {
		return target;
	}
	public void setTarget(HelloModel target) {
		this.target = target;
	}
	
	// 添加控制点并通知Editpart
	public void addBendpoint(int index, Point point) {
		bendpoints.add(index, point);
		firePropertyChange(P_BEND_POINT, null, null);
	}
	public List getBendpoints() {
		return bendpoints;
	}
	// 删除控制点并通知Editpart
	public void removeBendpoint(int index) {
		bendpoints.remove(index);
		firePropertyChange(P_BEND_POINT, null, null);
	}
	// 控制点发生变化时并通知Editpart
	public void replaceBendpoint(int index, Point point) {
		bendpoints.set(index, point);
		firePropertyChange(P_BEND_POINT, null, null);
	}
}
