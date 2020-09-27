/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-03-23 */

public class Line {
    private Point startP;
    private Point endP;
   // constructors
   /**
     * Create a line.
     * <p>
     * @param start - a start point.
     * @param end - an end point. */
   public Line(Point start, Point end) {
       this.startP = start;
       this.endP = end;
   }
   /**
     * Find the start and end points.
     * <p>
     * @param x1 - the first x value.
     * @param y1 - the first y value.
     * @param x2 - the second x value.
     * @param y2 - the second y value.*/
   public Line(double x1, double y1, double x2, double y2) {
       startP = new Point(x1, y1);
       endP = new Point(x2, y2);
   }
   // Return the length of the line
   /**
     * Find the length of the line.
     * <p>
     * @return the length.*/
   public double length() {
      return this.startP.distance(endP);
   }

   // Returns the middle point of the line
   /**
     * Find the middle point of the line.
     * <p>
     * @return the middle point.*/
   public Point middle() {
       double midX = (endP.getX() + startP.getX()) / 2;
       double midY = (endP.getY() + startP.getY()) / 2;
       Point mid = new Point(midX, midY);
       return mid;
   }
   // Returns the start point of the line
   /**
     * Find the start point of the line.
     * <p>
     * @return the start point.*/
   public Point start() {
       return this.startP;
   }
   // Returns the end point of the line
   /**
     * Find the end point of the line.
     * <p>
     * @return the end point.*/
   public Point end() {
       return this.endP;
   }

   // Returns true if the lines intersect, false otherwise
   /**
    * Check if the lines are intersect.
    * <p>
    * @param other - line we want to check.
    * @return true/false.*/
   public boolean isIntersecting(Line other) {
       return (this.isIntersectingInPoint(other) != null);
   }

   /**
     * Find the point where the Intersection happen.
     * <p>
     * @param other - line we want to check.
     * @return a point.*/
   public Point isIntersectingInPoint(Line other) {
       double m = findIncline(this);
       double n = findIncline(other);
       if (m == -1) {
           double xMeet = this.startP.getX();
           double yMeet = (findIncline(other) * xMeet) + findB(other);
           Point meetP = new Point(xMeet, yMeet);
           if (Math.min(other.startP.getX(), other.endP.getX()) <= xMeet
                   && Math.max(other.startP.getX(), other.endP.getX()) >= xMeet
                   && Math.min(this.startP.getY(), this.endP.getY()) <= yMeet
                   && Math.max(this.startP.getY(), this.endP.getY()) >= yMeet) {
               return meetP;
           }
       } else if (n == -1) {
           double xMeet = other.startP.getX();
           double yMeet = (findIncline(this) * xMeet) + findB(this);
           Point meetP = new Point(xMeet, yMeet);
           if (Math.min(this.startP.getX(), this.endP.getX()) <= xMeet
                   && Math.max(this.startP.getX(), this.endP.getX()) >= xMeet
                   && Math.min(other.startP.getY(), other.endP.getY()) <= yMeet
                   && Math.max(other.startP.getY(), other.endP.getY()) >= yMeet) {
               return meetP;
           }
       } else if (m != n) {
           double meet = this.findXMeeting(other);
           if ((meet >= this.start().getX() && meet <= this.end().getX())
                   && (meet >= other.start().getX() && meet <= other.end().getX())) {
               return this.intersectionWith(other);
           } else if ((meet <= this.start().getX() && meet >= this.end().getX())
                   && (meet <= other.start().getX() && meet >= other.end().getX())) {
               return this.intersectionWith(other);
           } else if ((meet <= this.start().getX() && meet >= this.end().getX())
                   && (meet >= other.start().getX() && meet <= other.end().getX())) {
               return this.intersectionWith(other);
           } else if ((meet >= this.start().getX() && meet <= this.end().getX())
                   && (meet <= other.start().getX() && meet >= other.end().getX())) {
               return this.intersectionWith(other);
           }
       }
       return null;
   }
   /**
     * Find the line's incline.
     * <p>
     * @param ln - line we want to check.
     * @return the incline of the line.*/
   public double findIncline(Line ln) {
       double dx = ln.start().getX() - ln.end().getX();
       if (dx == 0) {
           return -1;
       }
       double dy = ln.start().getY() - ln.end().getY();
       return dy / dx;
   }
   /**
     * Find the line's 'b' (meeting with the Y axis).
     * <p>
     * @param ln - line we want to check.
     * @return the meeting with the Y axis.*/
   public double findB(Line ln) {
       double y = ln.start().getY();
       double m = findIncline(ln);
       double x = ln.start().getX();
       return y - (m * x);
   }
   /**
     * Find the X of the meeting point.
     * <p>
     * @param other - line we want to check.
     * @return the X of the meeting point.*/
   public double findXMeeting(Line other) {
       double m = findIncline(this);
       double n = findIncline(other);
       double b1 = findB(this);
       double b2 = findB(other);
       return (b2 - b1) / (m - n);
   }

   // Returns the intersection point if the lines intersect,
   // and null otherwise.
   /**
     * Find the meeting point.
     * <p>
     * @param other - line we want to check.
     * @return the meeting point or null if there isn't.*/
   public Point intersectionWith(Line other) {
       double x, y;
       if (findIncline(other) == -1) {
           double xMeet1 = other.startP.getX();
           double yMeet1 = (findIncline(this) * xMeet1) + findB(this);
           return new Point(xMeet1, yMeet1);
       } else if (findIncline(this) == -1) {
           double xMeet1 = this.startP.getX();
           double yMeet1 = (findIncline(other) * xMeet1) + findB(other);
           return new Point(xMeet1, yMeet1);
       }
       x = this.findXMeeting(other);
       y = (findIncline(this) * x) + findB(this);
       return new Point(x , y);
   }

   /**
    * Check if the point is in the line.
    * <p>
    * @param checkP - the point we checking.
    * @return true/false.*/
   public boolean checkPointInLine(Point checkP) {
        return (this.startP.distance(checkP) + this.endP.distance(checkP)
        == this.startP.distance(this.endP));
   }

   // equals -- return true is the lines are equal, false otherwise
   /**
     * Check if the lines are equal.
     * <p>
     * @param other - line we want to check.
     * @return true/false.*/
   public boolean equals(Line other) {
       if (this.start() == other.start() && this.end() == other.end()) {
           return true;
       }
       if (this.start() == other.end() && this.end() == other.start()) {
           return true;
       }
       return false;
   }

   // If this line does not intersect with the rectangle, return null.
   // Otherwise, return the closest intersection point to the
   // start of the line.
   /**
    * Find the closest intersection point with the rectangle sides to the start of the line.
    * <p>
    * @param rect - the rectangle we want to check.
    * @return the closest point.*/
   public Point closestIntersectionToStartOfLine(Rectangle rect) {
       if ((rect.intersectionPoints(this)).size() == 1) {
           return rect.intersectionPoints(this).get(0);
       }
       if ((rect.intersectionPoints(this)).size() == 2) {
           if (rect.intersectionPoints(this).get(0).distance(startP)
                   < rect.intersectionPoints(this).get(1).distance(startP)) {
               return rect.intersectionPoints(this).get(0);
           }
           return rect.intersectionPoints(this).get(1);
       }
       return null;
   }
}