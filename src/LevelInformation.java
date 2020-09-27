import java.util.List;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public interface LevelInformation {
    /**
     * Get the number of balls.
     * <p>
     * @return the number of balls.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * Initial the ball velocities.
     * <p>
     * @return a list of velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * Get the paddle's speed.
     * <p>
     * @return the paddle's speed.
     */
    int paddleSpeed();
    /**
     * Get the paddle's width.
     * <p>
     * @return the paddle's width.
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.
    /**
     * Get the level name.
     * <p>
     * @return the paddle's width.
     */
    String levelName();
    // Returns a sprite with the background of the level
    /**
     * Get the level's background.
     * <p>
     * @return the level's background.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    /**
     * Get the blocks.
     * <p>
     * @return a list of blocks.
     */
    List<Block> blocks();
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    /**
     * Get the number of the blocks the user need to remove.
     * <p>
     * @return the number of the blocks the user need to remove.
     */
    int numberOfBlocksToRemove();
}