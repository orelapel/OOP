/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    private Block deathRegion;

    /**
     * Constractor.
     * <p>
     * @param game - the game where we put the ball remover.
     * @param remainingBalls - counter to the remaining balls.
     * @param deathRegion - the block where the balls will remove if they touch it.
     */
    public BallRemover(GameLevel game, Counter remainingBalls, Block deathRegion) {
        this.game = game;
        this.remainingBalls = remainingBalls;
        this.deathRegion = deathRegion;
    }
    /**
     * Update the number of balls.
     * <p>
     * @param num - the number of balls we want to add to the remaining balls.
     */
    public void updateRemainingBalls(int num) {
        this.remainingBalls.increase(num);
    }

    /**
     * Get the number of the remaining balls.
     * <p>
     * @return the number of the remaining balls.
     */
    public int getRemain() {
        return this.remainingBalls.getValue();
    }

    /**
     * Balls that hit the deth region will remove from the game.
     * <p>
     * @param beingHit - the block that hitted.
     * @param hitter - the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit == this.deathRegion) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }
}