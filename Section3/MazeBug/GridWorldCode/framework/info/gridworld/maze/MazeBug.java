package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next = null;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;  //count the steps in all
	boolean hasShown = false;  //final message has been shown

	// my own variables.
	public ArrayList<Location> temp = new ArrayList<Location>();
	public int count = 0;
	public boolean flagNoMove = false;
	
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (stepCount == 0) {
			temp.add(getLocation());
		//	crossLocation.push(temp);
		}
		
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else {
			goBack();
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		Location locCurrent = getLocation();
		int[] dires = {Location.NORTH, Location.WEST, Location.SOUTH, Location.EAST};
				
		for (int i = 0; i < 4; i++) {
			Location neibor = locCurrent.getAdjacentLocation(dires[i]);

			// test for if it is destination
			if (gr.isValid(neibor) == true) {
				Actor a = gr.get(neibor);
				if (a instanceof Rock && a.getColor().equals(Color.red)) {
					isEnd = true;
					valid.add(neibor);
					return valid;
				}
			}

			// add neibor location to list that can go 
			if ( gr.isValid(neibor) == true  &&
				 gr.get(neibor) == null) {
				 	valid.add(neibor);
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Location loc = getLocation();
		ArrayList<Location> arr = new ArrayList<Location>();
		arr = getValid(loc);
		if (arr.size() == 0) {
			if (flagNoMove == false) {
				count = temp.size();
				count--;
				flagNoMove = true;
				//Grid<Actor> gr = getGrid();
				//Flower flower = new Flower(getColor());
				//flower.putSelfInGrid(gr, loc);
			}
			return false;
		}
		if (arr.size() == 1) {
			next = arr.get(0);
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		// keep the current location to put flower in final
		Location loc = getLocation();
		
		// when there is a cross road, deal with stack
		if (next == null) {
			crossLocation.push(temp);
			//  temp.clear();
			temp = new ArrayList<Location>();
			temp.add(loc);
			goStack();
		} else {
			temp.add(next);
		}				
		
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			last = loc;
			
			moveTo(next);
			next = null;
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	// deal with the tree node cross thing
	public void goStack() {
		// dealing with only one way can go
		Location loc = getLocation();
		ArrayList<Location> arr = new ArrayList<Location>();
		arr = getValid(loc);

		Random num = new Random();  //  get random number from 0 to the number of road that can walk.
		next = arr.get(num.nextInt(arr.size()));
		temp.add(next);
		
	}
	

	// go back to the father tree node
	public void goBack() {
		count--;
		Location loc = getLocation();
		
		moveTo(temp.get(count));
		Grid<Actor> gr = getGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
		
		
		//this.moveTo(temp.get(0));
		if (count == 0) {
			temp.clear();
			temp = crossLocation.pop();
			flagNoMove = false;
		}
		stepCount++;
	}
}