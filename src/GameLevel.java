import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class GameLevel implements Animation {
   private SpriteCollection sprites;
   private GameEnvironment environment;
   private GUI gui;
   private BlockRemover blockRem;
   private BallRemover ballRem;
   private ScoreTrackingListener scoreTrLis;
   private ScoreIndicator scoreInd;
   private Counter numOfLives;
   private LivesIndicator livesInd;
   private AnimationRunner runner;
   private boolean running;
   private biuoop.KeyboardSensor keyboard;
   private LevelInformation  levelInfo;
   private List<Ball> ballList;

   /**
    * Create a game.
    * <p>
    * @param sprites - the sprites collection.
    * @param environment - the game environment.
    * @param levelInfo - the level information.*/
   public GameLevel(SpriteCollection sprites, GameEnvironment environment, LevelInformation  levelInfo) {
       this.sprites = sprites;
       this.environment = environment;
       this.levelInfo = levelInfo;
   }

   /**
    * Create a game without getting "sprites", "environment" as parameters.
    * <p>
    * @param levelInfo - the level information.
    * @param keyboard - the keyboard sensor.
    * @param runner - the animation runner.
    * @param gui - the GUI.
    * @param scoreInd - the score indicator.
    * @param numOfLives - counter to the lives.
    * @param scoreTrLis - the score tracking listener*/
   public GameLevel(LevelInformation  levelInfo, biuoop.KeyboardSensor keyboard,
           AnimationRunner runner, GUI gui,  ScoreIndicator scoreInd,
           Counter numOfLives, ScoreTrackingListener scoreTrLis) {
       this.sprites = new SpriteCollection();
       this.environment = new GameEnvironment();
       this.levelInfo = levelInfo;
       this.keyboard = keyboard;
       this.runner = runner;
       this.gui = gui;
       this.scoreInd = scoreInd;
       this.numOfLives = numOfLives;
       this.setLivesIndicator(this.numOfLives);
       this.scoreTrLis = scoreTrLis;
   }

   /**
    * Set the block remover.
    * <p>
    * @param count - the current number of the blocks.
    */
   public void setBlockRem(Counter count) {
       this.blockRem = new BlockRemover(this, count);
   }

   /**
    * Set the ball remover.
    * <p>
    * @param count - the current number of the balls.
    * @param deathRegion - the block which is the death region.
    */
   public void setBallRem(Counter count, Block deathRegion) {
       this.ballRem = new BallRemover(this, count, deathRegion);
   }

   /**
    * Set the Score Tracking Listener.
    * <p>
    * @param count - the current number of scores.
    */
   public void setScoreTrackingListener(Counter count) {
       this.scoreTrLis = new ScoreTrackingListener(count);
   }

   /**
    * Set the Score Indicator.
    * <p>
    * @param count - the current number of scores.
    */
   public void setScoreIndicator(Counter count) {
       this.scoreInd = new ScoreIndicator(count);
   }

   /**
    * Set the Lives Indicator.
    * <p>
    * @param count - the current number of lives.
    */
   public void setLivesIndicator(Counter count) {
       this.livesInd = new LivesIndicator(count);
   }

   /**
    * Get the number of lives.
    * <p>
    * @return the number of lives.
    */
   public int getLives() {
       return this.numOfLives.getValue();
   }

   /**
    * Get the number of the remaining blocks.
    * <p>
    * @return the number of the remaining blocks.
    */
   public int getBlockRemain() {
       return this.blockRem.getRemain();
   }

   /**
    * Add a collidabale.
    * <p>
    * @param c - a new collidable.*/
   public void addCollidable(Collidable c) {
       this.environment.addCollidable(c);
   }

   /**
    * Add a sprite.
    * <p>
    * @param s - a new sprite.*/
   public void addSprite(Sprite s) {
       this.sprites.addSprite(s);
   }

   /**
    * Remove a collidabale.
    * <p>
    * @param c - a collidable.*/
   void removeCollidable(Collidable c) {
       this.environment.removeCollidable(c);
   }

   /**
    * Remove a sprite.
    * <p>
    * @param s - a sprite.*/
   void removeSprite(Sprite s) {
       this.sprites.removeSprite(s);
   }

   /**
    * Initialize a new game.
    * <p>.
    * Create the Blocks and Ball (and Paddle) and add them to the game.*/
   public void initialize() {
       // Add the level's background to the sprite list.
       this.sprites.addSprite(this.levelInfo.getBackground());

       // Add to game the score's and the live's indicator.
       this.scoreInd.addToGame(this);
       this.livesInd.addToGame(this);

       // Add the level's name to the sprite list.
       this.sprites.addSprite(new LevelName(this.levelInfo.levelName()));

       // Create the frame blocks and add them to the game
       Block upBlock = new Block(new Point(0, 20), 800, 20);
       upBlock.setColor(java.awt.Color.GRAY);
       upBlock.setStroke(Color.GRAY);
       upBlock.setNumOfHits(0);
       upBlock.addToGame(this);
       Block leftBlock = new Block(new Point(0, 20), 20, 580);
       leftBlock.setColor(java.awt.Color.GRAY);
       leftBlock.setStroke(Color.GRAY);
       leftBlock.setNumOfHits(0);
       leftBlock.addToGame(this);
       Block rightBlock = new Block(new Point(770, 20), 30, 580);
       rightBlock.setColor(java.awt.Color.GRAY);
       rightBlock.setStroke(Color.GRAY);
       rightBlock.setNumOfHits(0);
       rightBlock.addToGame(this);
       Block deathRegion = new Block(new Point(20, 600), 780, 20);
       deathRegion.setColor(java.awt.Color.GRAY);
       deathRegion.setStroke(Color.GRAY);
       deathRegion.setNumOfHits(0);
       deathRegion.addToGame(this);

       // Initilize the velocity list and set the ball remover
       this.levelInfo.initialBallVelocities();
       this.setBallRem(new Counter(this.levelInfo.numberOfBalls()), deathRegion);
       deathRegion.addHitListener(this.ballRem);

       // Initilize the block list and set the block remover
       this.levelInfo.blocks();
       this.setBlockRem(new Counter(this.levelInfo.numberOfBlocksToRemove()));

       // Create the blocks and add them to the game
       for (Block block : this.levelInfo.blocks()) {
           block.addHitListener(this.blockRem);
           block.addHitListener(this.scoreTrLis);
           block.addToGame(this);
       }
   }

   /**
    * Play a turn.
    * <p>
    * Start the animation loop.*/
   public void playOneTurn() {
       biuoop.Sleeper sleeper = new biuoop.Sleeper();
       this.runner = new AnimationRunner(this.gui, 60, sleeper);

       this.createBallsOnTopOfPaddle();

       this.runner.run(new CountdownAnimation(500, 3, this.sprites));
       this.running = true;

       // use our runner to run the current animation -- which is one turn of
       // the game.
       sleeper.sleepFor(300);
       this.runner.run(this);
   }

   /**
    * Create balls on top of the paddle.
    */
   private void createBallsOnTopOfPaddle() {
       List<Velocity> list = this.levelInfo.initialBallVelocities();
       this.ballList = new ArrayList<Ball>();

       // Create the balls, set their settings, and add them to the game
       for (Velocity vel : list) {
           Ball ball = new Ball(400, 575, 5, java.awt.Color.WHITE);
           ball.setVelocity(vel);
           ball.setBoardLeft(0, 0);
           ball.setBoardRight(800, 600);
           ball.addToGame(this);
           ball.setGameEnvironment(this.environment);
           this.ballList.add(ball);
       }
   }

   /**
    * Run the game.
    */
   public void run() {
       this.keyboard = gui.getKeyboardSensor();
       while (this.numOfLives.getValue() > 0 && this.blockRem.getRemain() > 0) {
           // Create a paddle and add it to the game
           int w = this.levelInfo.paddleWidth();
           Paddle paddle = new Paddle(this.keyboard,
                   new Rectangle(new Point(400 - (w / 2), 580), w, 15));
           paddle.addToGame(this);

           // Run the game
           playOneTurn();

           // Remove the objects from the previous turn
           this.removeSprite(paddle);
           this.removeCollidable(paddle);
           for (Ball ball : this.ballList) {
               ball.removeFromGame(this);
           }
           this.ballRem.updateRemainingBalls(this.levelInfo.numberOfBalls());
       }
       return;
   }

   // the logic from the previous playOneTurn method goes here.
   // the `return` or `break` statements should be replaced with
   // this.running = false;
   /**
    * Do the appropriate frame.
    * <p>
    * @param d - the surface.
    */
   @Override
   public void doOneFrame(DrawSurface d) {
       this.sprites.drawAllOn(d);
       this.scoreInd.updateScore(this.scoreTrLis.getScore());
       this.sprites.notifyAllTimePassed();

       if (this.blockRem.getRemain() == 0 || this.ballRem.getRemain() == 0) {
           // If the player removed all the blocks from the level
           if (this.blockRem.getRemain() == 0) {
               this.scoreTrLis.updateScore(100);
               this.scoreInd.updateScore(this.scoreTrLis.getScore());
               this.sprites.drawAllOn(d);
           } else {
               this.numOfLives.decrease(1);
           }
           // End the frame
           this.running = false;
       }

       // Check if the user pressed "p" for pause.
       if (this.keyboard.isPressed("p")) {
           this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
       }
   }

   /**
    * The turn will stop when running == false.
    * <p>
    * @return true / false.
    */
   @Override
   public boolean shouldStop() {
       return !this.running;
   }
}