import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class DirectHit implements LevelInformation {
    private List<Velocity> velList;
    private List<Block> blockList;

    /**
     * Get the number of balls.
     * <p>
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.velList.size();
    }

    /**
     * Initial the ball velocities.
     * <p>
     * @return a list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        this.velList = new ArrayList<Velocity>();
        this.velList.add(Velocity.fromAngleAndSpeed(180, 8));
        return this.velList;
    }

    /**
     * Get the paddle's speed.
     * <p>
     * @return the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * Get the paddle's width.
     * <p>
     * @return the paddle's width.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * Get the level name.
     * <p>
     * @return the paddle's width.
     */
    @Override
    public String levelName() {
        return "DirectHit";
    }

    /**
     * Get the level's background.
     * <p>
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new FirstBackground();
    }

    /**
     * Get the blocks.
     * <p>
     * @return a list of blocks.
     */
    @Override
    public List<Block> blocks() {
        this.blockList = new ArrayList<Block>();
        this.blockList.add(new Block(new Point(385, 122), 30, 30));
        this.blockList.get(0).setColor(Color.red);
        this.blockList.get(0).setStroke(Color.BLACK);
        this.blockList.get(0).setNumOfHits(1);
        return this.blockList;
    }

    /**
     * Get the number of the blocks the user need to remove.
     * <p>
     * @return the number of the blocks the user need to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blockList.size();
    }
}
