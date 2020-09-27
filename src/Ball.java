
import java.awt.Color;

import biuoop.DrawSurface;

/**
 * @author Orel Lichtenstein
 * @version 1.8
 * @since 2019-03-23 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point leftSide;
    private Point rightSide;
    private GameEnvironment gameEnv;

    // constructor
    /**
     * Create a ball.
     * <p>
     * @param x - the value x of the center.
     * @param y - the value y of the center.
     * @param r - the radios of the ball.
     * @param color - the color of the ball.*/
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameEnv = new GameEnvironment();
    }
    // accessors
    /**
     * Find the x value of the center.
     * <p>
     * @return the value x of the center.*/
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * Find the y value of the center.
     * <p>
     * @return the value y of the center.*/
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * Find the radios of the ball.
     * <p>
     * @return the radios of the ball.*/
    public int getSize() {
        return this.r;
    }
    /**
     * Find the color of the ball.
     * <p>
     * @return the color of the ball.*/
    public java.awt.Color getColor() {
        return this.color;
    }
    // draw the ball on the given DrawSurface
    /**
     * Draw a ball.
     * <p>
     * @param surface - the surface.*/
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }
    /**
     * Set the velocity.
     * <p>
     * @param vel - the velocity of the ball.*/
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }
    /**
     * Set the velocity.
     * <p>
     * @param dx - the change of the x value.
     * @param dy - the change of the y value.*/
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * Get the velocity of the ball.
     * <p>
     * @return v - the velocity of the ball.*/
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * Set the point of the board from the left side.
     * <p>
     * @param x - the x value of this point.
     * @param y - the y value of this point.*/
    public void setBoardLeft(int x, int y) {
        Point left = new Point(x, y);
        this.leftSide = left;
    }
    /**
     * Set the point of the board from the right side.
     * <p>
     * @param x - the x value of this point.
     * @param y - the y value of this point.*/
    public void setBoardRight(int x, int y) {
        Point right = new Point(x, y);
        this.rightSide = right;
    }
    /**
     * Set the game environment of the ball.
     * <p>
     * @param gameEnvi - the game environment.*/
    public void setGameEnvironment(GameEnvironment gameEnvi) {
        this.gameEnv = gameEnvi;
    }

    /**
     * Set the game environment of the ball with a new block.
     * <p>
     * @param block - a new block to the game environment.*/
    public void setGameEnvironment(Collidable block) {
        this.gameEnv.addCollidable(block);
    }
    /**
     * Get the point of the left side.
     * <p>
     * @return the point of the left side.*/
    public Point getLeft() {
        return this.leftSide;
    }
    /**
     * Get the point of the right side.
     * <p>
     * @return the point of the right side.*/
    public Point getRight() {
        return this.rightSide;
    }
    /**
     * Move the ball a step. */
    public void moveOneStep() {
        // Create a line which is the ball's route;
        Point startP = this.center;
        Point endP = new Point(this.center.getX() + this.v.getDx(), this.center.getY() + this.v.getDy());
        Line trajectory = new Line(startP, endP);
        CollisionInfo colInf = this.gameEnv.getClosestCollision(trajectory);

        // Check the limits of the board
        if ((this.center.getX() + this.r >= this.getRight().getX()
                && this.v.getDx() > 0)
                || (this.center.getX() - this.r <= this.getLeft().getX()
                && this.v.getDx() < 0)) {
            this.setVelocity(-this.v.getDx(), this.v.getDy());
        }
        if ((this.center.getY() - this.r <= this.getLeft().getY()
                && this.v.getDy() < 0)) {
            this.setVelocity(this.v.getDx(), -this.v.getDy());
        }

        // Take care of the case when the ball collide an object which is collidable
        if (colInf != null) {
            this.setVelocity(colInf.collisionObject().hit(this, colInf.collisionPoint(), this.v));
        }

        this.center = this.getVelocity().applyToPoint(center);
    }
    /**
     * Add the ball to the game.
     * <p>
     * @param g - a game.*/
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove th ball from the game.
     * <p>
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
    @Override
    /**
     * Move a step.*/
    public void timePassed() {
        // TODO Auto-generated method stub
        moveOneStep();
    }
}