import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-04-04 */
public interface Sprite {
       /**
        * draw the sprite to the screen.
        * <p>
        * @param d - the surface. */
       void drawOn(DrawSurface d);
       /**
         * notify the sprite that time has passed. */
       void timePassed();
}