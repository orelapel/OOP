/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * <p>
     * @param hl - a hit listener we want to add.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - a hit listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}
