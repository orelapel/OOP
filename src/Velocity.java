/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-03-23 */


public class Velocity {
    private double dx;
    private double dy;
    // constructor
      /**
       * Create a Velocity.
       * <p>
       * @param dx - the change of the x value.
       * @param dy - the change of the y value.*/
       public Velocity(double dx, double dy) {
           this.dx = dx;
           this.dy = dy;
       }
       /**
         * Create a Velocity.
         * <p>
         * @param angle - the angel of the ball.
         * @param speed - the speed of the ball.
         * @return the appropriate velocity*/
       public static Velocity fromAngleAndSpeed(double angle, double speed) {
              double dx = Math.sin(Math.toRadians(angle)) * speed;
              double dy = Math.cos(-1 * Math.toRadians(angle)) * speed;
              return new Velocity(dx, dy);
       }
       /**
        * Find the dx of the ball.
        * <p>
        * @return the dx.*/
       public double getDx() {
           return this.dx;
       }
       /**
        * Find the dy of the ball.
        * <p>
        * @return the dy.*/
       public double getDy() {
           return this.dy;
       }
       // Take a point with position (x,y) and return a new point
       // with position (x+dx, y+dy)
       /**
        * Find the dy of the ball.
        * <p>
        * @param p - the point the ball need to be.
        * @return the point with the dx and dy.*/
        public Point applyToPoint(Point p) {
           double x = p.getX();
           double y = p.getY();
           return new Point(x + this.dx, y + this.dy);
       }
}