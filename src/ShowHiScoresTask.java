import biuoop.KeyboardSensor;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyboardSensor;

    /**
     * Constractors.
     * @param runner - the AnimationRunner.
     * @param highScoresAnimation - the highScores animation.
     * @param keyboardSensor - the KeyboardSensor.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor keyboardSensor) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * Run the task.
     * @return playing the task.
     */
    @Override
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}
