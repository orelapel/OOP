import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-04-04 */
public class Paddle implements Collidable, Sprite {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectPaddle;

    /**
     * Create a paddle.
     * <p>
     * @param keyboard - the key from the keyboard.
     * @param rectPaddle - the rectangle of the paddle.*/
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectPaddle) {
        this.keyboard = keyboard;
        this.rectPaddle = rectPaddle;
    }
    /**
     * Move the paddle a step to left.*/
    public void moveLeft() {
        if (this.rectPaddle.getUpperLeft().getX() > 20) {
            this.rectPaddle.setUpperPoint(this.rectPaddle.getUpperLeft().getX() - 10,
                                     this.rectPaddle.getUpperLeft().getY());
        }
    }
    /**
     * Move the paddle a step to right.*/
    public void moveRight() {
        if (this.rectPaddle.getUpperLeft().getX() + this.rectPaddle.getWidth() < 770) {
            this.rectPaddle.setUpperPoint(this.rectPaddle.getUpperLeft().getX() + 10,
                                     this.rectPaddle.getUpperLeft().getY());
        }
    }

    // Sprite
    @Override
    /**
     * Recognize where the user want to go.
     * <p>
     * If he pressed the LEFT_KEY the program will pass to the moveLeft method.
     * <p>
     * If he pressed the RIGHT_KEY the program will pass to the moveRight method. */
    public void timePassed() {
         if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
             this.moveLeft();
         }
         if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
             this.moveRight();
         }
    }
    @Override
    /**
     * Draw a paddle.
     * <p>
     * @param d - a surface.*/
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.ORANGE);
        d.fillRectangle((int) this.rectPaddle.getUpperLeft().getX(),
                (int) this.rectPaddle.getUpperLeft().getY(),
                (int) (this.rectPaddle.getWidth()),
                (int) (this.rectPaddle.getHeight()));
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.rectPaddle.getUpperLeft().getX(),
                (int) this.rectPaddle.getUpperLeft().getY(),
                (int) (this.rectPaddle.getWidth()),
                (int) (this.rectPaddle.getHeight()));
    }

    // Collidable
    @Override
    /**
     * Get the rectangle of the paddle.
     * <p>
     * @return the rectangle.*/
    public Rectangle getCollisionRectangle() {
        return this.rectPaddle;
    }
    @Override
    /**
     * Change the velocity of the object if there is a hit.
     * <p>
     * @return the rectangle.*/
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
         Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
         double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
         double w = this.rectPaddle.getWidth();

         // Find the place of the hit in the paddle and according to the place the ball will move
         // in the appropriate degree.
         if (collisionPoint.getX() >= this.rectPaddle.getUpperLeft().getX()
                 && collisionPoint.getX() < this.rectPaddle.getUpperLeft().getX() + (w / 5)) {
             v = Velocity.fromAngleAndSpeed(240, speed);
         } else if (collisionPoint.getX() >= this.rectPaddle.getUpperLeft().getX() + (w / 5)
                 && collisionPoint.getX() < this.rectPaddle.getUpperLeft().getX() + (w / 5) * 2) {
             v = Velocity.fromAngleAndSpeed(210, speed);
         } else if (collisionPoint.getX() >= this.rectPaddle.getUpperLeft().getX() + (w / 5) * 2
                 && collisionPoint.getX() < this.rectPaddle.getUpperLeft().getX() + (w / 5) * 3) {
             v = Velocity.fromAngleAndSpeed(180, speed);
         } else if (collisionPoint.getX() >= this.rectPaddle.getUpperLeft().getX() + (w / 5) * 3
                 && collisionPoint.getX() < this.rectPaddle.getUpperLeft().getX() + (w / 5) * 4) {
             v = Velocity.fromAngleAndSpeed(150, speed);
         } else if (collisionPoint.getX() >= this.rectPaddle.getUpperLeft().getX() + (w / 5) * 4
                 && collisionPoint.getX() < this.rectPaddle.getUpperLeft().getX() + w) {
             v = Velocity.fromAngleAndSpeed(120, speed);
         }
         return v;
    }

    /**
     * Add this paddle to the game.
     * <p>
     * @param g - the game. */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
