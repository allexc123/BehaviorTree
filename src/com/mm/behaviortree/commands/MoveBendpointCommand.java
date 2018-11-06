package com.mm.behaviortree.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.mm.behaviortree.model.AbstractConnectionModel;

public class MoveBendpointCommand extends Command {
	 private AbstractConnectionModel conn;
	    private Point newLocation,oldLocation;
	    private int index;

	    @Override
	    public void execute()
	    {
	        oldLocation = (Point) conn.getBendpoints().get(index);
	        conn.replaceBendpoint(index, newLocation);
	    }
	    
	    public void setConnection(Object object)
	    {
	        conn = (AbstractConnectionModel) object;
	    }
	    public void setIndex(int i)
	    {
	        index = i;
	    }
	    public void setNewLocation(Point loc)
	    {
	        newLocation = loc;
	    }
	    @Override
	    public void undo()
	    {
	        conn.replaceBendpoint(index, oldLocation);
	    }   
}

