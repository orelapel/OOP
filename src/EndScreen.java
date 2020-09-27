import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class EndScreen implements Animation {
    private boolean win;
    private int score;

    /**
     * Constractor.
     * <p>
     * @param score - the current score.
     * @param win - the boolean value telling if the user winned or lost.
     */
    public EndScreen(int score, boolean win) {
        this.score = score;
        this.win = win;
    }

    /**
     * Put the end screen.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.win) {
            d.drawText(10, d.getHeight() / 2,
                    "You Win! Your score is " + Integer.toString(this.score), 32);
        } else {
            d.drawText(10, d.getHeight() / 2,
                    "Game Over. Your score is " + Integer.toString(this.score), 32);
        }
    }

    /**
     * The PauseScreen should never stop.
     * <p>
     * @return false
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
