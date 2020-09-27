import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class Green3 implements LevelInformation {
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
        this.velList.add(Velocity.fromAngleAndSpeed(150, 5));
        this.velList.add(Velocity.fromAngleAndSpeed(-150, 5));
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
        return "Green 3";
    }

    /**
     * Get the level's background.
     * <p>
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new ThirdBackground();
    }

    /**
     * Get the blocks.
     * <p>
     * @return a list of blocks.
     */
    @Override
    public List<Block> blocks() {
        this.blockList = new ArrayList<Block>();
        for (int i = 0; i < 10; i++) {
            Block block = new Block(new Point(720 - (50 * i), 100), 50, 30);
            block.setColor(java.awt.Color.GRAY);
            block.setStroke(Color.BLACK);
            block.setNumOfHits(2);
            this.blockList.add(block);
        }
        for (int i = 0; i < 9; i++) {
            Block block = new Block(new Point(720 - (50 * i), 130), 50, 30);
            block.setColor(java.awt.Color.RED);
            block.setStroke(Color.BLACK);
            block.setNumOfHits(1);
            this.blockList.add(block);
        }
        for (int i = 0; i < 8; i++) {
            Block block = new Block(new Point(720 - (50 * i), 160), 50, 30);
            block.setColor(java.awt.Color.YELLOW);
            block.setStroke(Color.BLACK);
            block.setNumOfHits(1);
            this.blockList.add(block);
        }
        for (int i = 0; i < 7; i++) {
            Block block = new Block(new Point(720 - (50 * i), 190), 50, 30);
            block.setColor(java.awt.Color.BLUE);
            block.setStroke(Color.BLACK);
            block.setNumOfHits(1);
            this.blockList.add(block);
        }
        for (int i = 0; i < 6; i++) {
            Block block = new Block(new Point(720 - (50 * i), 220), 50, 30);
            block.setColor(java.awt.Color.WHITE);
            block.setStroke(Color.BLACK);
            block.setNumOfHits(1);
            this.blockList.add(block);
        }
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
