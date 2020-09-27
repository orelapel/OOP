
import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Constractor.
     * <p>
     * @param lives - the number of lives.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }
    /**
     * Update the number of lives by integer.
     * <p>
     * @param numOflives - the number of lives we want to update.
     */
    public void updateLives(int numOflives) {
        this.lives = new Counter(numOflives);
    }
    /**
     * Draw the lives indicator.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.RED);
        d.fillRectangle(0, 0, 100, 20);
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle(0, 0, 100, 20);

        d.setColor(java.awt.Color.BLACK);
        d.drawText(20, 15, "lives: " + Integer.toString(this.lives.getValue()), 15);
    }

    /**
     * timePassed.
     */
    @Override
    public void timePassed() {

    }
    /**
     * Add the lives indicator to the game.
     * <p>
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
