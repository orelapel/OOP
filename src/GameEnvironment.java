import java.util.ArrayList;
import java.util.List;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
public class GameEnvironment {
    private List<Collidable> collidables;

       /**
        * Create a new collidables list. */
       public GameEnvironment() {
           this.collidables = new ArrayList<Collidable>();
       }

       /**
        * Add the given collidable to the environment.
        * <p>
        * @param c - a collidable. */
       public void addCollidable(Collidable c) {
           this.collidables.add(c);
       }

       /**
        * Remove the given collidable from the environment.
        * <p>
        * @param c - a collidable. */
       public void removeCollidable(Collidable c) {
           this.collidables.remove(c);
       }

       // Assume an object moving from line.start() to line.end().
       // If this object will not collide with any of the collidables
       // in this collection, return null. Else, return the information
       // about the closest collision that is going to occur.
       /**
        * Find the collision if there is, and return the information about the closest collision that is going to occur.
        * <p>
        * @param trajectory - the line we check.
        * @return null - if there is'nt a collision / information about the collision - if there is. */
       public CollisionInfo getClosestCollision(Line trajectory) {
           Point closestMeeting = null;
           Point meet;
           Collidable col = null;
           double dist = Double.MAX_VALUE;
           int i = 0;

           // Check about each collidable if there is a collision with the trajectory
           for (Collidable c : collidables) {
               if (c.getCollisionRectangle().intersectionPoints(trajectory) != null) {
                   meet = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                   if ((meet != null) && (meet.distance(trajectory.start()) < dist)) {
                       closestMeeting = meet;
                       col = this.collidables.get(i);
                       dist = meet.distance(trajectory.start());
                   }
               }
               i++;
           }

           // If there is'nt a collision
           if (closestMeeting == null) {
               return null;
           }

           return new CollisionInfo(closestMeeting, col);
       }
}