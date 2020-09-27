import java.awt.Color;
import java.util.ArrayList;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21
* @param <T> - the type of the menu.
* */
public class MenuAnimation<T> implements Menu<T> {
    private ArrayList<String> keysList;
    private ArrayList<String> messagesList;
    private ArrayList<T> returnValue;
    private T status;
    private String title;
    private KeyboardSensor keyboardSensor;
    private ArrayList<Boolean> isSubMenu;
    private ArrayList<Menu<T>> subMenus;
    private AnimationRunner animationRunner;
    private boolean stop;

    /**
     * Constractors.
     * @param title - the title of the menu.
     * @param keyboardSensor - the keyboardSensor.
     * @param ar - the animationRunner.
     */
    public MenuAnimation(String title, KeyboardSensor keyboardSensor, AnimationRunner ar) {
        this.title = title;
        this.keysList = new ArrayList<String>();
        this.messagesList = new ArrayList<String>();
        this.returnValue = new ArrayList<T>();
        this.keyboardSensor = keyboardSensor;
        this.subMenus = new ArrayList<>();
        this.isSubMenu = new ArrayList<>();
        this.animationRunner = ar;
        this.stop = false;
        this.reset();
    }

    /**
     * Show the menu.
     * @param d - the surfacre.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.drawText(10, 50, this.title, 32);
        d.setColor(Color.MAGENTA);
        for (int i = 0; i < this.keysList.size(); i++) {
            d.drawText(50, 140 + 25 * i, "(" + this.keysList.get(i) + ")"
                    + " " + this.messagesList.get(i), 25);
        }
    }

    /**
     * When to stop.
     * @return true/ false.
     */
    @Override
    public boolean shouldStop() {
        for (int i = 0; i < this.keysList.size(); i++) {
            if (this.keyboardSensor.isPressed(this.keysList.get(i))) {
                if (!this.isSubMenu.get(i)) {
                    this.status = this.returnValue.get(i);
                    this.stop = true;
                } else {
                    Menu<T> subMenu = this.subMenus.get(i);
                    this.animationRunner.run(subMenu);
                    Task<T> task = (Task<T>) subMenu.getStatus();
                    if (task != null) {
                        task.run();
                    }
                    subMenu.reset();
                    this.stop = false;
                    this.reset();
                }
            }
        }
        return this.stop;
    }

    /**
     * Reset the menu.
     */
    @Override
    public void reset() {
        this.status = null;
        this.stop = false;
    }

    /**
     * Add a new selection to the menu.
     * <p>
     * @param key - the key.
     * @param message - the message, what the key will do.
     * @param returnVal - the value we will return if the user pressed the key
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keysList.add(key);
        this.messagesList.add(message);
        this.returnValue.add(returnVal);
        this.isSubMenu.add(false);
    }

    /**
     * The status we are after the choise of the user.
     * <p>
     * @return the status.
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * Add a sub-menu.
     * <p>
     * @param key - the key we press and get to the sub-menu.
     * @param message - the message about the sub menu.
     * @param subMenu - the sub-menu.
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.keysList.add(key);
        this.messagesList.add(message);
        this.returnValue.add(null);
        this.isSubMenu.add(true);
        this.subMenus.add(subMenu);
    }
}
