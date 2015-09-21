import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.text.StyleConstants.ColorConstants;

/**
 *
 *@author Lucas
 *
 */

public class RealCrabTest {

 /**
  *test if the flowers can be attracted by the RealCrab
  */
        @Test
        public void TestIfcanAttrackFlowers() {
        final ActorWorld world = new ActorWorld();
        
      	RealCrab jim = new RealCrab();
        world.add(new Location(4,3), jim);
       
	    Rock rock = new Rock();
        Flower flower1 = new Flower();
        Flower flower2 = new Flower();
        Flower flower3 = new Flower();
        world.add(new Location(2, 2), flower1);
        world.add(new Location(2, 3), flower2);
        world.add(new Location(2, 5), flower3);
        world.add(new Location(3, 4), rock);


        jim.act();
        
        int []expects1 = new int[] {3,2};
        int []expects2 = new int[] {3,3};
        int []expects3 = new int[] {2,5};
        int []actual1 = new int[] {flower1.getLocation().getRow(), flower1.getLocation().getCol()};
        int []actual2 = new int[] {flower2.getLocation().getRow(), flower2.getLocation().getCol()};
        int []actual3 = new int[] {flower3.getLocation().getRow(), flower3.getLocation().getCol()};


        assertArrayEquals(expects1, actual1);
        assertArrayEquals(expects2, actual2);
        assertArrayEquals(expects3, actual3);
        }
/*
 *
 * test if the RealCrab will die for no eating flowers within five steps
 */
        @Test
        public void TestIfwillDie() {
        final ActorWorld world = new ActorWorld();
        
      	RealCrab jim = new RealCrab();
        world.add(new Location(4, 3), jim);

        jim.act();
        jim.act();
        jim.act();
        jim.act();
        jim.act();

		assertNull(jim.getGrid());
        }
}
