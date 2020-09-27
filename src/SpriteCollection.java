import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-05-10 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Create a sprite collection. */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * Add a sprite to the list.
     * <p>
     * @param s - a new sprite.*/
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Remove a sprite from the list.
     * <p>
     * @param s - a new sprite.*/
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Call timePassed() on all sprites.*/
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite sp : spritesCopy) {
            sp.timePassed();
        }
    }
    /**
     * Call drawOn(d) on all sprites.
     * <p>
     * @param d - the surface. */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sp : this.sprites) {
            sp.drawOn(d);
        }
    }
}