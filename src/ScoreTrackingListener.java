/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constractor.
     * <p>
     * @param scoreCounter - the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Get the score.
     * <p>
     * @return the score.
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
    /**
     * Update the score.
     * <p>
     * @param num - the number we want to add to the current score.
     */
    public void updateScore(int num) {
        this.currentScore.increase(num);
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * <p>
     * @param beingHit - the block that hitted.
     * @param hitter - the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(15);
        } else {
            this.currentScore.increase(5);
        }
    }

}
