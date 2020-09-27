import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockRec;
    private Color color;
    private Point upperLeft;
    private double width;
    private double height;
    private int numOfHits;
    private List<HitListener> hitListeners;
    private Color stroke;
    private Image image;
    private Map<Integer, Object> colImg;

    /**
     * Create a block.
     * <p>
     * @param upperLeft - the point of the upper left side of the block.
     * @param width - the width of the block.
     * @param height - the height of the block.*/
    public Block(Point upperLeft, double width, double height) {
        this.blockRec = new Rectangle(upperLeft, width, height);
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Create a block.
     * <p>
     * @param rectangle - the rectangle the block is samilliar to.*/
    public Block(Rectangle rectangle) {
        // TODO Auto-generated constructor stub
        this.blockRec = rectangle;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Set a color to the block.
     * <p>
     * @param col - the color we want to the block.*/
    public void setColor(java.awt.Color col) {
        this.color = col;
    }

    /**
     * Set a stroke to the block.
     * <p>
     * @param col - the color we want to the stroke of the block.*/
    public void setStroke(java.awt.Color col) {
        this.stroke = col;
    }

    /**
     * Set a image to the block.
     * <p>
     * @param img - the image we want to the block.*/
    public void setImage(Image img) {
        this.image = img;
    }

    /**
     * Set the colImg map.
     * @param map - the colImg map.
     */
    public void setMapImgCol(Map<Integer, Object> map) {
        this.colImg = map;
    }
    /**
     * Set the number times when a ball hit the block.
     * <p>
     * @param num - the number times when a ball hit the block.*/
    public void setNumOfHits(int num) {
        this.numOfHits = num;
    }

    /**
     * Get the rectangle.
     * <p>
     * @return the rectangle.*/
    public Rectangle getCollisionRectangle() {
        return this.blockRec;
    }

    /**
     * Get the upper left point.
     * <p>
     * @return the upper left point.*/
    public Point getUpperLeftPoint() {
        return this.upperLeft;
    }

    /**
     * Get the width of the block.
     * <p>
     * @return the width of the block.*/
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the block.
     * <p>
     * @return the height of the block.*/
    public double getHeight() {
        return this.height;
    }

    /**
     * When there is a hit go over all the hit listeners and
     * tell them to do what they need when there is a hit event.
     * <p>
     * @param hitter - the ball that hit the object.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }
     }

    /**
     * Change the velocity of the object if there is a hit.
     * <p>
     * @param hitter - the ball that hit the object.
     * @param collisionPoint - the point where happen the collision.
     * @param currentVelocity - the velocity the object has now.
     * @return the velocity after the hit.*/
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.numOfHits != 0) {
            this.setNumOfHits(this.numOfHits - 1);
        }
        if (this.numOfHits >= 1) {
            Object fill = this.colImg.get(this.numOfHits);
            if (fill.getClass().toString().equals("class java.awt.Color")) {
                this.setColor((Color) fill);
            } else if (fill.getClass().toString().equals("class java.awt.image.BufferedImage")) {
                this.setImage((Image) fill);
            }
        }

        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        //The block's points
        double xRect = this.getUpperLeftPoint().getX();
        double yRect = this.getUpperLeftPoint().getY();
        double w = this.getWidth();
        double h = this.getHeight();

        // The block's sides
        Line leftSide = new Line(xRect, yRect, xRect, yRect + h);
        Line upSide = new Line(xRect, yRect, xRect + w, yRect);
        Line downSide = new Line(xRect, yRect + h, xRect + w, yRect + h);
        Line rightSide = new Line(xRect + w, yRect, xRect + w, yRect + h);

        // Check what side the object collide to
        if ((leftSide.checkPointInLine(collisionPoint) && (currentVelocity.getDx() > 0))
                || (rightSide.checkPointInLine(collisionPoint) && (currentVelocity.getDx() < 0))) {
            v = new Velocity(-v.getDx(), v.getDy());
        } else if ((upSide.checkPointInLine(collisionPoint) && (currentVelocity.getDy() >= 0))
                || downSide.checkPointInLine(collisionPoint) && (currentVelocity.getDy() <= 0)) {
            v = new Velocity(v.getDx(), -1 * v.getDy());
        }
        this.notifyHit(hitter);
        // Return the new velocity
        return v;
    }

    /**
     * Get the color of the block.
     * <p>
     * @return the color of the block.*/
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw a block.
     * <p>
     * @param d - a surface.*/
    public void drawOn(DrawSurface d) {
        // Draw a block
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle((int) this.blockRec.getUpperLeft().getX(),
                    (int) this.blockRec.getUpperLeft().getY(),
                    (int) (this.blockRec.getWidth()), (int) (this.blockRec.getHeight()));
        } else if (this.image != null) {
            d.drawImage((int) this.blockRec.getUpperLeft().getX(),
                    (int) this.blockRec.getUpperLeft().getY(), this.image);
        }
        if (this.stroke != null) {
            d.setColor(this.stroke);
            d.drawRectangle((int) this.blockRec.getUpperLeft().getX(),
                    (int) this.blockRec.getUpperLeft().getY(),
                    (int) (this.blockRec.getWidth()), (int) (this.blockRec.getHeight()));
        }
    }

    /**
     * Add the block to the game.
     * <p>
     * @param g - a game.*/
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    /**
     * the time passed.*/
    public void timePassed() {
    }
    /**
     * Remove the block from the game.
     * <p>
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Add to the block a hit listener.
     * <p>
     * @param hl - a hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove from the block a hit listener.
     * <p>
     * @param hl - a hit listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Get the number of the hit points.
     * <p>
     * @return the number of the hit points.
     */
    public int getHitPoints() {
        return this.numOfHits;
    }
}