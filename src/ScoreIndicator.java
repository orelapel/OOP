
import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     * <p>
     * @param score - the current score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * Update the score by integer.
     * <p>
     * @param newScore - the score we want to update.
     */
    public void updateScore(int newScore) {
        this.score = new Counter(newScore);
    }
    /**
     * Draw the score indicator.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.white);
        d.fillRectangle(100, 0, 800, 20);
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle(100, 0, 800, 20);

        // Draw the scores on the block
        d.setColor(java.awt.Color.BLACK);
        d.drawText(350, 12, "Score: " + Integer.toString(this.score.getValue()), 15);
    }
    /**
     * timePassed.
     */
    @Override
    public void timePassed() {

    }
    /**
     * Add the score indicator to the game.
     * <p>
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
