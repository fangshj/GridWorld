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
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a large number of
 * rows and columns and is bounded. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
	private List<LinkedList<OccupantInCol>> occupantArray; // the array storing the grid linkedList
	private int colAll = 0;
	private int rowAll = 0;

	/**
	 * Constructs an empty bounded grid with the given dimensions.
	 * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
	 * @param rows number of rows in SparseBoundedGrid
	 * @param cols number of columns in SparseBoundedGrid
	 */
	public SparseBoundedGrid(int rows, int cols)
	{
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		occupantArray = new ArrayList<LinkedList<OccupantInCol>> ();
		colAll = cols;
		rowAll = rows;
		for (int i = 0; i < rows; i++) {
			occupantArray.add(new LinkedList<OccupantInCol>());	
		}
	}

	public int getNumRows()
	{
		return rowAll;
	}

	public int getNumCols()
	{
		// Note: according to the constructor precondition, numRows() > 0, so
		// theGrid[0] is non-null.
		return colAll;
	}

	public boolean isValid(Location loc)
	{
		return 0 <= loc.getRow() && loc.getRow() < getNumRows()
			&& 0 <= loc.getCol() && loc.getCol() < getNumCols();
	}

	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> theLocations = new ArrayList<Location>();

		for (int i = 0; i < rowAll; i++) {
			for (OccupantInCol node : occupantArray.get(i)) {
				theLocations.add(new Location(i, node.getCol()));	
			}	
		}
		return theLocations;
	}

	public E get(Location loc)
	{
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc
					+ " is not valid");
		}

// return the object in list
		for (OccupantInCol node : occupantArray.get(loc.getRow())) {
			if (node.getCol() == loc.getCol()) {
				return (E) node.getObject();	
			}	
		}
		return null; 
	}

	public E put(Location loc, E obj)
	{
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc
					+ " is not valid");
		}
		if (obj == null) {
			throw new NullPointerException("obj == null");
		}

		// Add the object to the grid.
		E oldOccupant = get(loc);
		OccupantInCol newObj = new OccupantInCol(obj, loc.getCol());
		occupantArray.get(loc.getRow()).add(newObj);
		return oldOccupant;
	}

	public E remove(Location loc)
	{
		if (!isValid(loc)) {
			throw new IllegalArgumentException("Location " + loc
					+ " is not valid");
		}

		// Remove the object from the grid.
		E r = get(loc);
		LinkedList<OccupantInCol> one = occupantArray.get(loc.getRow());
		for (OccupantInCol theNode : one) {
			if (theNode.getCol() == loc.getCol()) {
				one.remove(theNode);	
			}	
		}
		return r;
	}
}
