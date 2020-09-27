import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-03-23 */
public interface Collidable {
       /**
        * Get "collision shape" of the object.
        * <p>
        * @return the "collision shape" of the object. */
       Rectangle getCollisionRectangle();

       /**
        * Notify the object that we collided with it at collisionPoint with a given velocity.
        * <p>
        * The return is the new velocity expected after the hit
        * <p>
        * (based on the force the object inflicted on us).
        * <p>
        * @param hitter - the ball that hit the object.
        * @param collisionPoint - the point where the collision occur.
        * @param currentVelocity - the current velocity.
        * @return the new velocity expected after the hit. */
       Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

       /**
        * Draw a collidable.
        * <p>
        * @param d - the surface. */
       void drawOn(DrawSurface d);
}