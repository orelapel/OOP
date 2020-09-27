import java.awt.Image;

import biuoop.DrawSurface;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class ImageSprite implements Sprite {
    private Image image;

    /**
     * Constractors.
     * @param img - the image.
     */
    public ImageSprite(Image img) {
        this.image = img;
    }

    /**
     * Draw the image on the surface.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    /**
     * timePassed.
     */
    @Override
    public void timePassed() {

    }

}
