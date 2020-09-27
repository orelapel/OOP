import java.awt.Color;

import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class SecondBackground implements Sprite {
    /**
     * Draw the background on the surface.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // The background color.
        d.setColor(Color.WHITE);
        d.fillRectangle(20, 40, 760, 600);

        // The sun rays.
        d.setColor(Color.YELLOW);
        for (int i = 20; i < 650; i += 5) {
            d.drawLine(150, 150, i, 300);
        }

        // The sun.
        d.fillCircle(150, 150, 70);
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 150, 50);
        d.setColor(Color.YELLOW);
        d.fillCircle(150, 150, 30);
    }

    /**
     * The time passed.
     */
    @Override
    public void timePassed() {

    }
}
