import java.util.ArrayList;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-04-04 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

       // Create a new rectangle with location and width/height.
       /**
        * Create a rectangle.
        * <p>
        * @param upperLeft - the point of the upper left side of the rectangle.
        * @param width - the width of the rectangle.
        * @param height - the height of the rectangle.*/
       public Rectangle(Point upperLeft, double width, double height) {
           this.upperLeft = upperLeft;
           this.width = width;
           this.height = height;
       }

       // Return a (possibly empty) List of intersection points
       // with the specified line.
       /**
        * Create a list of all the intersection points between this rectangle and a line.
        * <p>
        * @param line - the line we want to check.
        * @return the list of the points.*/
       public java.util.List<Point> intersectionPoints(Line line) {
            // Create an empty list
            ArrayList<Point> newList = new ArrayList<Point>();

            //The rectangle's points
            double xRect = this.getUpperLeft().getX();
            double yRect = this.getUpperLeft().getY();
            double w = this.getWidth();
            double h = this.getHeight();

            //The rectangle's sides
            Line leftSide = new Line(xRect, yRect + h, xRect, yRect);
            Line upSide = new Line(xRect, yRect, xRect + w, yRect);
            Line downSide = new Line(xRect, yRect + h, xRect + w, yRect + h);
            Line rightSide = new Line(xRect + w, yRect + h, xRect + w, yRect);

            // Add the appropriate points to the list
            if (leftSide.isIntersectingInPoint(line) != null) {
                newList.add(leftSide.intersectionWith(line));
            }
            if (upSide.isIntersectingInPoint(line) != null) {
                newList.add(upSide.intersectionWith(line));
            }
            if (downSide.isIntersectingInPoint(line) != null) {
                newList.add(downSide.intersectionWith(line));
            }
            if (rightSide.isIntersectingInPoint(line) != null) {
                newList.add(rightSide.intersectionWith(line));
            }

            return newList;
       }

       // Return the width and height of the rectangle
       /**
        * Get the width of the rectangle.
        * <p>
        * @return the width of the rectangle.*/
       public double getWidth() {
           return this.width;
       }
       /**
        * Get the height of the rectangle.
        * <p>
        * @return the height of the rectangle.*/
       public double getHeight() {
           return this.height;
       }

       /**
        * Get the upper-left point of the rectangle.
        * <p>
        * @return the upper-left point of the rectangle.*/
       public Point getUpperLeft() {
           return this.upperLeft;
       }
       /**
        * Set the upper-left point of the rectangle.
        * <p>
        * @param x - the x value of the chosen point.
        * @param y - the y value of the chosen point. */
       public void setUpperPoint(double x, double y) {
           this.upperLeft = new Point(x, y);
       }
}