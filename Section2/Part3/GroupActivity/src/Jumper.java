import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
/**
* Jumper can move forward two cells in each move. 
* It jumps over rocks and flowers. It does not leave anything behind it after jump.
*/
public class Jumper extends Actor {
    /* Constructs a blue Jumper. */
    public Jumper() {
        setColor(Color.BLUE);
    }
    /* Constructs a Jumper of a given color */
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }
    /* Jump if it can jump, turn otherwise */
    public void act() {
        if (canJump()) {
            jump();
            setColor(Color.GREEN);
        } else {
            turn();
            setColor(Color.YELLOW);
        }
    }
    /* make each turn 90 degrees */
    public void turn() {
        setDirection(getDirection() + Location.WEST);
    }
    /*
    * Moves the Jumper forward two cells.
    * The two cells in front must be valid or the Jumper will remove itself from the grid.
    */
    public void jump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
        return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location second = next.getAdjacentLocation(getDirection());
        if (gr.isValid(second)) {
            moveTo(second);
        } else {
            removeSelfFromGrid();
        }
    }
    /**
    * Tests whether this Jumper can jump forward into a location two in front that is empty or contains a flower.
    * The next cell in front of jumper must be empty or contain a Rock or a Flower.
    * return true if this Jumper can move.
    */
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        if (!((neighbor == null) || (neighbor instanceof Flower)||
           (neighbor instanceof Rock))) {
            return false;
        }
        Location second = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(second)) {
            return false;
        }
        neighbor = gr.get(second);
        return (neighbor == null) || (neighbor instanceof Flower);
    }
}
