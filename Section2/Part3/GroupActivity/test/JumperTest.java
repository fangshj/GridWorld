import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import org.junit.After;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
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

public class JumperTest {

    
        @Test
        public void TestJumpOverRock() {
        final ActorWorld world = new ActorWorld();
        
      	Jumper jim = new Jumper();
        world.add(new Location(0,9), jim);
        
        Rock rock1 = new Rock();
        Rock rock2 = new Rock();
        Rock rock3 = new Rock();
        world.add(new Location(0,8),rock1);
        world.add(new Location(0,6),rock2);
        world.add(new Location(0,3),rock3);

        jim.act();
        jim.act();
        jim.act();
        jim.act();
        jim.act();
        
        int []expects = new int[] {2,5};
        int []actual = new int[] {jim.getLocation().getRow(), jim.getLocation().getCol()};
        assertArrayEquals(expects, actual);
        }
        
        @Test
        public void TestOutOfGrid() {
        final ActorWorld world = new ActorWorld();
        
        Jumper jim = new Jumper();
        world.add(new Location(3,3), jim);

        jim.act();
        jim.act();
        jim.act();
        jim.act();
        jim.act();
        jim.act();

        int []expects = new int[] {5,1};
        int []actual = new int[] {jim.getLocation().getRow(), jim.getLocation().getCol()};
        assertArrayEquals(expects, actual);
        }
        
        @Test
        public void TestJumperColorWhenJump() {
        final ActorWorld world = new ActorWorld();
        
        Jumper jim = new Jumper();
        world.add(new Location(3,3), jim);
        jim.act();
        if(Color.GREEN == jim.getColor())
            assertEquals(true,true);
        else
                assertEquals(true, false);
        }
        
        @Test
        public void TestJumperColorWhenTurn() {
        final ActorWorld world = new ActorWorld();
        
        Jumper jim = new Jumper();
        world.add(new Location(1,3), jim);
        jim.act();
        if(Color.YELLOW == jim.getColor())
            assertEquals(true,true);
        else
                assertEquals(true, false);
        }
}


