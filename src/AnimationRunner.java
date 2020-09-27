import biuoop.DrawSurface;
import biuoop.GUI;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * Constractor.
     * <p>
     * @param gui - the GUI.
     * @param framesPerSecond - the number of the frames per second.
     * @param sleeper - the sleeper.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, biuoop.Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * Run the animation.
     * <p>
     * @param animation - the animation we want to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);

            // Time to see the current score after the level should stop
            if (animation.shouldStop()) {
                this.sleeper.sleepFor(500);
            }

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
