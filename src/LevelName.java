import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class LevelName implements Sprite {
    private String name;

    /**
     * Constructor.
     * <p>
     * @param name - the name of the level.
     */
    public LevelName(String name) {
        this.name = name;
    }

    /**
     * Draw the level's name on the given surface.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText(550, 15, "Level Name: " + this.name, 15);
    }
    /**
     * The time passed.
     */
    @Override
    public void timePassed() {

    }
}
