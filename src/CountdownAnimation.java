import java.awt.Color;

import biuoop.DrawSurface;

//The CountdownAnimation will display the given gameScreen,
//for numOfSeconds seconds, and on top of them it will show
//a countdown from countFrom back to 1, where each number will
//appear on the screen for (numOfSeconds / countFrom) secods, before
//it is replaced with the next one.
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Constractor.
     * <p>
     * @param numOfSeconds - the number of seconds.
     * @param countFrom - from what number to count.
     * @param gameScreen - all the sprites plays in the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }
    /**
     * Count down until 0.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.LIGHT_GRAY);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(countFrom), 32);
        sleeper.sleepFor((long) (this.numOfSeconds / this.countFrom));
        this.countFrom -= 1;
    }
    /**
     * Stop when we get to 0.
     * <p>
     * @return true / false.
     */
    @Override
    public boolean shouldStop() {
        return this.countFrom == 0;
    }
}
