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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

/**
 This class is to create a dancingBug that can dance at every move
 */
public class DancingBug extends Bug
{
    private int steps;
	private int[] times;
    /**
     * Constructs a dancing bug that the times as the given array
     * @param danceTimes means the dance time array
     */
    public DancingBug(int[] danceTimes)
    {
        steps = 0;
		times = new int [danceTimes.length];
		System.arraycopy(danceTimes, 0, times, 0, danceTimes.length);
    }
	

    /**
     * dance the array times 
     */
    public void act()
    {
		if (steps == times.length) {
			steps = 0;
		}
		for (int i = 0; i < times[steps]; i++) {
			turn();
		}
        if (canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
        }
    }
}
