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
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ChameleonCritter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    private static final double DARKENING_FACTOR = 0.10;
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
		if (n == 0) {
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

			setColor(new Color(red, green, blue));
			return;	
		}
       // int r = (int) (Math.random() * n);

     //   Actor other = actors.get(r);
    //    setColor(other.getColor());
		/*
		* Only chang the color the same as the front the behind actor
		*/
//		Location loc1 = getLocation().getAdjacentLocation(Location.AHEAD);
//		Location loc2 = getLocation().getAdjacentLocation(Location.AHEAD + 180);

		int[] dirs =
		{ Location.NORTH, Location.SOUTH };
		for (Location loc : getLocationsInDirections(dirs))
			if (getGrid().get(loc) != null) {
				setColor(getGrid().get(loc).getColor());
				return;
			}
/*
		for (Actor a : actors) {	
			if ( loc1.equals(a) || loc2.equals(a)) {
				setColor(a.getColor());
				return;
			}
		}
*/
		// no front or behind actor, so darn itself
		Color c = getColor();
		int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
		int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
		int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

		setColor(new Color(red, green, blue));

    }

	public ArrayList<Location> getLocationsInDirections(int[] directions)
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		Grid gr = getGrid();
		Location loc = getLocation();

		for (int d : directions)
		{
			Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
			if (gr.isValid(neighborLoc))
				locs.add(neighborLoc);
		}
		return locs;
	} 

}

