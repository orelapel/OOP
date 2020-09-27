import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class FinalFour implements LevelInformation {
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
        this.velList.add(Velocity.fromAngleAndSpeed(180, 5));
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
        return "Final Four";
    }

    /**
     * Get the level's background.
     * <p>
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new ForthBackground();
    }

    /**
     * Get the blocks.
     * <p>
     * @return a list of blocks.
     */
    @Override
    public List<Block> blocks() {
        this.blockList = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            Block block1 = new Block(new Point(20 + (50 * i), 100), 50, 30);
            block1.setColor(java.awt.Color.RED);
            block1.setStroke(Color.BLACK);
            block1.setNumOfHits(1);
            this.blockList.add(block1);
            Block block2 = new Block(new Point(20 + (50 * i), 130), 50, 30);
            block2.setColor(java.awt.Color.ORANGE);
            block2.setStroke(Color.BLACK);
            block2.setNumOfHits(1);
            this.blockList.add(block2);
            Block block3 = new Block(new Point(20 + (50 * i), 160), 50, 30);
            block3.setColor(java.awt.Color.YELLOW);
            block3.setStroke(Color.BLACK);
            block3.setNumOfHits(1);
            this.blockList.add(block3);
            Block block4 = new Block(new Point(20 + (50 * i), 190), 50, 30);
            block4.setColor(java.awt.Color.GREEN);
            block4.setStroke(Color.BLACK);
            block4.setNumOfHits(1);
            this.blockList.add(block4);
            Block block5 = new Block(new Point(20 + (50 * i), 220), 50, 30);
            block5.setColor(java.awt.Color.BLUE);
            block5.setStroke(Color.BLACK);
            block5.setNumOfHits(1);
            this.blockList.add(block5);
            Block block6 = new Block(new Point(20 + (50 * i), 250), 50, 30);
            block6.setColor(java.awt.Color.PINK);
            block6.setStroke(Color.BLACK);
            block6.setNumOfHits(1);
            this.blockList.add(block6);
            Block block7 = new Block(new Point(20 + (50 * i), 280), 50, 30);
            block7.setColor(java.awt.Color.MAGENTA);
            block7.setStroke(Color.BLACK);
            block7.setNumOfHits(1);
            this.blockList.add(block7);
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
