import java.awt.Color;

import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class ForthBackground implements Sprite {
    /**
     * Draw the background on the surface.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // The background color.
        d.setColor(Color.BLUE);
        d.fillRectangle(20, 40, 760, 600);

        // The rain.
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(100 + (10 * i), 380, 60 + (10 * i), 600);
            d.drawLine(590 + (10 * i), 500, 550 + (10 * i), 600);
        }

        // The clouds.
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(100, 370, 22);
        d.fillCircle(120, 390, 25);

        d.fillCircle(590, 490, 22);
        d.fillCircle(610, 510, 25);
        d.setColor(Color.GRAY);
        d.fillCircle(140, 360, 28);

        d.fillCircle(630, 480, 28);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(160, 390, 18);
        d.fillCircle(180, 380, 30);

        d.fillCircle(650, 510, 18);
        d.fillCircle(670, 500, 30);
    }
    /**
     * The time passed.
     */
    @Override
    public void timePassed() {

    }
}
