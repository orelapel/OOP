import java.awt.Color;

import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class FirstBackground implements Sprite {
    /**
     * Draw the background on the surface.
     * <p>
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // The background color.
        d.setColor(Color.BLACK);
        d.fillRectangle(20, 40, 760, 600);

        // The circles one into another.
        d.setColor(Color.BLUE);
        d.drawCircle(400, 140, 50);
        d.drawCircle(400, 140, 75);
        d.drawCircle(400, 140, 100);

        // The lines in every quater of the circles.
        d.drawLine(430, 140, 520, 140);
        d.drawLine(280, 140, 370, 140);
        d.drawLine(400, 40, 400, 118);
        d.drawLine(400, 160, 400, 240);
    }

    /**
     * The time passed.
     */
    @Override
    public void timePassed() {

    }

}
