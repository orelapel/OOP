import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public interface Animation {
    /**
     * Do one frame on the given surface.
     * <p>
     * @param d - the surface.
     */
    void doOneFrame(DrawSurface d);
    /**
     * Return if to stop the animation or continue.
     * <p>
     * @return true / false.
     */
    boolean shouldStop();
}
