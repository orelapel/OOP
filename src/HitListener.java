/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * <p>
     * @param beingHit - the block that hitted.
     * @param hitter - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
