/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-03-23 */

public class Point {
    private double x;
    private double y;
   // constructor
   /**
    * Create a point.
    * <p>
    * @param x - the value x of the point.
    * @param y - the value y of the point.*/
   public Point(double x, double y) {
       this.x = x;
       this.y = y;
   }

   @Override
   public String toString() {
       return "(" + x + ", " + y + ")";
   }
   // distance -- return the distance of this point to the other point
   /**
    * Find the distance between two points.
    * <p>
    * @param other - the second point.
    * @return the distance of this point to the other point.*/
   public double distance(Point other) {
       double dx = this.x - other.x;
       double dy = this.y - other.y;
       return Math.sqrt(dx * dx + dy * dy);
   }

   // equals -- return true is the points are equal, false otherwise
   /**
    * Check if two different points are equal.
    * <p>
    * @param other - the second point.
    * @return true/false.*/
   public boolean equals(Point other) {
       if (other == null) {
           return false;
       }
       return (this.x == other.x && this.y == other.y);
   }

   // Return the x and y values of this point
   /**
    * Find the x value of the point.
    * <p>
    * @return the x value.*/
   public double getX() {
       return this.x;
   }
   /**
    * Find the y value of the point.
    * <p>
    * @return the y value.*/
   public double getY() {
       return this.y;
   }
}