import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class WideEasy implements LevelInformation {
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
        for (int i = 0; i < 5; i++) {
            this.velList.add(Velocity.fromAngleAndSpeed(180 + (18 * i), 5));
            this.velList.add(Velocity.fromAngleAndSpeed(180 - (18 * i), 5));
        }
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
        return 600;
    }

    /**
     * Get the level name.
     * <p>
     * @return the paddle's width.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Get the level's background.
     * <p>
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new SecondBackground();
    }

    /**
     * Get the blocks.
     * <p>
     * @return a list of blocks.
     */
    @Override
    public List<Block> blocks() {
        this.blockList = new ArrayList<Block>();
        for (int i = 0; i < 2; i++) {
            Block block1 = new Block(new Point(20 + (50 * i), 300), 50, 30);
            block1.setColor(java.awt.Color.RED);
            block1.setStroke(Color.BLACK);
            block1.setNumOfHits(1);
            this.blockList.add(block1);
            Block block2 = new Block(new Point(120 + (50 * i), 300), 50, 30);
            block2.setColor(java.awt.Color.ORANGE);
            block2.setStroke(Color.BLACK);
            block2.setNumOfHits(1);
            this.blockList.add(block2);
            Block block3 = new Block(new Point(220 + (50 * i), 300), 50, 30);
            block3.setColor(java.awt.Color.YELLOW);
            block3.setStroke(Color.BLACK);
            block3.setNumOfHits(1);
            this.blockList.add(block3);
            Block block5 = new Block(new Point(470 + (50 * i), 300), 50, 30);
            block5.setColor(java.awt.Color.BLUE);
            block5.setStroke(Color.BLACK);
            block5.setNumOfHits(1);
            this.blockList.add(block5);
            Block block6 = new Block(new Point(570 + (50 * i), 300), 50, 30);
            block6.setColor(java.awt.Color.PINK);
            block6.setStroke(Color.BLACK);
            block6.setNumOfHits(1);
            this.blockList.add(block6);
            Block block7 = new Block(new Point(670 + (50 * i), 300), 50, 30);
            block7.setColor(java.awt.Color.MAGENTA);
            block7.setStroke(Color.BLACK);
            block7.setNumOfHits(1);
            this.blockList.add(block7);
        }
        for (int i = 0; i < 3; i++) {
            Block block4 = new Block(new Point(320 + (50 * i), 300), 50, 30);
            block4.setColor(java.awt.Color.GREEN);
            block4.setStroke(Color.BLACK);
            block4.setNumOfHits(2);
            this.blockList.add(block4);
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
