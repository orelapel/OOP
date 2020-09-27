/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21
* @param <T> - the type of the menu.
* */

public interface Menu<T> extends Animation {
    /**
     * Add a new selection to the menu.
     * <p>
     * @param key - the key.
     * @param message - the message, what the key will do.
     * @param returnVal - the value we will return if the user pressed the key
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * The status we are after the choise of the user.
     * <p>
     * @return the status.
     */
    T getStatus();
    /**
     * Add a sub-menu.
     * <p>
     * @param key - the key we press and get to the sub-menu.
     * @param message - the message about the sub menu.
     * @param subMenu - the sub-menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     * Reset the menu.
     */
    void reset();
}
