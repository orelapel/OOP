import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter numOfLives;
    private ScoreIndicator scoreInd;
    private ScoreTrackingListener scoreTrLis;
    private LivesIndicator livesInd;
    private HighScoresTable highScore;

    /**
     * Constractors.
     * <p>
     * @param ar - the animation runner.
     * @param ks - the keyboard sensor.
     * @param gui - the GUI.
     * @param highScore - the high score.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable highScore) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.highScore = highScore;
    }

    /**
     * Run the levels from the list.
     * <p>
     * @param levels - list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // Set the scores
        this.setScoreTrackingListener(new Counter(0));
        this.setScoreIndicator(new Counter(this.scoreTrLis.getScore()));

        // Set the number of lives
        this.numOfLives = new Counter(7);
        this.setLivesIndicator(this.numOfLives);

        // Go over all the levels.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.gui, this.scoreInd,
                    this.numOfLives, this.scoreTrLis);

            level.initialize();
            level.run();

            // level has more blocks and player has more lives
            while (level.getBlockRemain() > 0 && level.getLives() > 0) {
                level.playOneTurn();
            }
        }
        // After the game finished, check if one of the conditions below is exist.
        // If it is, show the appropriate screen.

        // User lost.
        if (this.numOfLives.getValue() == 0) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new EndScreen(this.scoreTrLis.getScore(), false)));
            // User won.
        } else {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new EndScreen(this.scoreTrLis.getScore(), true)));
        }
        if (this.highScore.getRank(this.scoreTrLis.getScore()) <= this.highScore.size()) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "anonymous");
            this.highScore.add(new ScoreInfo(name, this.scoreTrLis.getScore()));
            File file = new File("highscores.txt");
            try {
                this.highScore.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(this.highScore)));
        this.gui.close();
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
     * Set the Score Tracking Listener.
     * <p>
     * @param count - the current number of scores.
     */
    public void setScoreTrackingListener(Counter count) {
        this.scoreTrLis = new ScoreTrackingListener(count);
    }

    /**
     * Set the Lives Indicator.
     * <p>
     * @param count - the current number of lives.
     */
    public void setLivesIndicator(Counter count) {
        this.livesInd = new LivesIndicator(count);
    }
}
