import java.awt.Color;

import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class ThirdBackground implements Sprite {
    /**
     * Draw the background on the surface.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // The background color.
        d.setColor(Color.GREEN);
        d.fillRectangle(20, 40, 760, 600);

        // The building.
        d.setColor(Color.BLACK);
        d.fillRectangle(60, 400, 100, 200);

        // The windows.
        d.setColor(Color.WHITE);
        for (int i = 70; i < 150; i += 17) {
            d.fillRectangle(i, 410, 10, 30);
            d.fillRectangle(i, 448, 10, 30);
            d.fillRectangle(i, 486, 10, 30);
            d.fillRectangle(i, 524, 10, 30);
            d.fillRectangle(i, 562, 10, 30);
        }

        // The continue of the building.
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(85, 330, 40, 70);
        d.setColor(Color.GRAY);
        d.fillRectangle(100, 170, 10, 160);
        d.setColor(Color.ORANGE);
        d.fillCircle(105, 165, 15);
        d.setColor(Color.RED);
        d.fillCircle(105, 165, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(105, 165, 3);
    }
    /**
     * The time passed.
     */
    @Override
    public void timePassed() {

    }
}
