import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class PauseScreen implements Animation {
    /**
     * Put a pause screen.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
