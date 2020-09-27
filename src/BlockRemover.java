/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constractor.
     * <p>
     * @param game - the game.
     * @param remainingBlocks - the number of the remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * Update the remaining blocks.
     * Add the given number to the current number of the remaining blocks.
     * <p>
     * @param num - the number we want to add.
     */
    public void updateRemainingBlocks(int num) {
        this.remainingBlocks.increase(num);
    }
    /**
     * Get the number of the remaining blocks.
     * <p>
     * @return number of the remaining blocks.
     */
    public int getRemain() {
        return this.remainingBlocks.getValue();
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * <p>
     * @param beingHit - the block that hitted.
     * @param hitter - the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}
