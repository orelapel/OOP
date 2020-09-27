import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constractor.
     * <p>
     * @param sensor - the keyboard sensor.
     * @param key - the pressed key.
     * @param animation - the animation using this class.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor,
            String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    /**
     * Put the animation's screen.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);

        // If the key is not pressed, set isAlreadyPressed=false
        if (!this.ks.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }

        // If the key was pressed before the animation started, we ignore the key press.
        if (!this.isAlreadyPressed && this.ks.isPressed(this.key)) {
            this.stop = true;
        }
    }

    /**
     * The animtion will stop if the user will press the key we axpect for.
     * <p>
     * @return true / false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
