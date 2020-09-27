/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-04-04 */
public class CollisionInfo {
    private Point colP;
    private Collidable object;

      /**
       * Create a collision info.
       * <p>
       * @param colP - the point at which the collision occurs.
       * @param object - the collidable object involved in the collision.*/
      public CollisionInfo(Point colP, Collidable object) {
          this.colP = colP;
          this.object = object;
      }

      /**
       * Find the point at which the collision occurs.
       * <p>
       * @return the point at which the collision occurs.*/
       public Point collisionPoint() {
           return this.colP;
       }

       /**
        * Find the collidable object involved in the collision.
        * <p>
        * @return the collidable object involved in the collision.*/
       public Collidable collisionObject() {
           return this.object;
       }
}