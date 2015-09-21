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
import java.awt.Color;
/**
 * A <code>BlusterCritterCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    private static final double DARKENING_FACTOR = 0.10;
	private int courage;
	private int count;

	public BlusterCritter() {
		courage = 2;	
		count = 0;
		setColor(Color.GRAY);
	} 
    public void processActors(ArrayList<Actor> actors)
    {
		
		int r = getLocation().getRow();
		int c = getLocation().getCol();
		Grid<Actor> gr = getGrid();
		for (int i = r - 2; i <= r + 2; i++) {
			for(int j = c - 2; j <= c + 2; j++) {
				if (gr.isValid(new Location(i, j))  && ( gr.get(new Location(i, j)) instanceof Critter) )	
					count++;
			}	
		}
		if (count - 1 < courage) {
			setColor(getColor().brighter());
			
		} else if (count - 1 >= courage) {
			setColor(getColor().darker());
			
		}
		count = 0;
    }
}
