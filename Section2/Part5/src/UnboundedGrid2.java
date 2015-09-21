/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */


import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList; 
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * An <code>UnboundedGrid22</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
	private Object[][] occupantArray; // the array storing the grid elements
	private int rate = 16;
	/**
	 * Constructs an empty unbounded grid.
	 */
	public UnboundedGrid2()
	{
		occupantArray = new Object[16][16];
	}
	public void growGrid(int x) {
		
		if (rate < x) {
			int temp = rate;
			while (rate < x) {
				rate *= 2;	
			}
			Object[][] tempArray = new Object[temp][temp];
			for (int i = 0; i < temp; i++) {
				for (int j= 0; j < temp; j++) {
					tempArray[i][j] = occupantArray[i][j];	
				}	
			}
			occupantArray = new Object[rate][rate];
			for (int i = 0; i < temp; i++) {
				for (int j= 0; j < temp; j++) {
					occupantArray[i][j] = tempArray[i][j];
				}
			}
		}
	}

	public int getNumRows()
	{
		return -1;
	}

	public int getNumCols()
	{
		return -1;
	}

	public boolean isValid(Location loc)
	{
		if(loc.getRow() >= rate || loc.getCol() >= rate) {
			int x = loc.getRow() > loc.getCol() ? loc.getRow() : loc.getCol();
			growGrid(x);	
		}
		return loc.getRow() >= 0 && loc.getCol() >= 0;
	}

	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> theLocations = new ArrayList<Location>();

		// Look at all grid locations.
		for (int r = 0; r < rate; r++)
		{
			for (int c = 0; c < rate; c++)
			{
				// If there's an object at this location, put it in the array.
				Location loc = new Location(r, c);
				if (get(loc) != null) {
					theLocations.add(loc);
				}
			}
		}

		return theLocations;
	}

	public E get(Location loc)
	{
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
	}

	public E put(Location loc, E obj)
	{
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		if (obj == null) {
			throw new NullPointerException("obj == null");
		}

		int x = loc.getRow() > loc.getCol() ? loc.getRow() : loc.getCol();
		growGrid(x);
		// Add the object to the grid.
		E oldOccupant = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = obj;
		return oldOccupant;
	}

	public E remove(Location loc)
	{
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}

		E r = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = null;
		return r;
	}
}
