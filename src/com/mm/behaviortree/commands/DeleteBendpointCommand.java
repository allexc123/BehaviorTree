package com.mm.behaviortree.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.AbstractConnectionModel;

public class DeleteBendpointCommand extends Command {
	private AbstractConnectionModel conn;
    private Point  oldLocation;
    private int index;

    @Override
    public void execute()
    {
        oldLocation = (Point) conn.getBendpoints().get(index);
        conn.removeBendpoint(index);
    }

    public void setConnection(Object object)
    {
        conn = (AbstractConnectionModel) object;
    }

    public void setIndex(int i)
    {
        index = i;
    }

    @Override
    public void undo()
    {
        conn.addBendpoint(index, oldLocation);
    }
}

