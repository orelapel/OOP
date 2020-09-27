import java.util.List;
import java.util.ArrayList;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class LevelGeneric implements LevelInformation {
    private String levelName;
    private List<Velocity> ballsVel = new ArrayList<Velocity>();
    private int padSpeed;
    private int padWidth;
    private Sprite background;
    private List<Block> blockList = new ArrayList<Block>();

    /**
     * Get the number of balls.
     * <p>
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.ballsVel.size();
    }

    /**
     * Add a new ball velocity.
     * @param vel - the new velocity.
     */
    public void addBallVelocities(Velocity vel) {
        this.ballsVel.add(vel);
    }
    /**
     * Initial the ball velocities.
     * <p>
     * @return a list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVel;
    }

    /**
     * Set the paddle's speed.
     * @param speed - the paddle's speed.
     */
    public void setPaddleSpeed(int speed) {
        this.padSpeed = speed;
    }
    /**
     * Get the paddle's speed.
     * <p>
     * @return the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return this.padSpeed;
    }
    /**
     * Set the paddle's width.
     * @param width - the paddle's width.
     */
    public void setPaddleWidth(int width) {
        this.padWidth = width;
    }
    /**
     * Get the paddle's width.
     * <p>
     * @return the paddle's width.
     */
    @Override
    public int paddleWidth() {
        return this.padWidth;
    }

    /**
     * Set the level name.
     * <p>
     * @param name - the level name.
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }
    /**
     * Get the level name.
     * <p>
     * @return the level name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Set the level's background.
     * @param bg - the level's background.
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }
    /**
     * Get the level's background.
     * <p>
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Add new block.
     * @param block - the new block.
     */
    public void addBlock(Block block) {
        this.blockList.add(block);
    }
    /**
     * Get the blocks.
     * <p>
     * @return a list of blocks.
     */
    @Override
    public List<Block> blocks() {
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
