/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
	public void makeMove(Location loc)
	{
		if (loc.equals(getLocation()))
		{
			double r = Math.random();
			int angle;
			if (r < 0.5)
				angle = Location.LEFT;
			else
				angle = Location.RIGHT;
			setDirection(getDirection() + angle);
		}
		else {
			int ang = getLocation().getDirectionToward(loc);
			Location loc2 = loc.getAdjacentLocation(ang);
			Grid<Actor> gr = getGrid();
			if (gr.isValid(loc2) && gr.get(loc2) == null) {
				super.makeMove(loc2);
			} else {
				super.makeMove(loc);
            }
		}
	}
}
