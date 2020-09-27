import java.awt.Color;

import biuoop.DrawSurface;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-28 */
public class ColorSprite implements Sprite {
    private Color color;

    /**
     * Constractors.
     * @param col - the color.
     */
    public ColorSprite(Color col) {
        this.color = col;
    }

    /**
     * Draw the color.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(20, 40, 760, 600);
    }

    /**
     * timePassed.
     */
    @Override
    public void timePassed() {
        // TODO Auto-generated method stub

    }

}
