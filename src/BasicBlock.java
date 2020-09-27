import java.awt.Color;
import java.awt.Image;
import java.util.TreeMap;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class BasicBlock implements BlockCreator, Cloneable {
    private int height;
    private int width;
    private int hitPoints;
    private Color fillCol;
    private Image fillImg;
    private Color stroke;
    private TreeMap<Integer, Object> colImg = new TreeMap<Integer, Object>();

    /**
     * Set height.
     * @param h - the height.
     */
    public void setHeight(int h) {
        this.height = h;
    }
    /**
     * Set width.
     * @param w - the width.
     */
    public void setWeidth(int w) {
        this.width = w;
    }
    /**
     * Set hit points.
     * @param num - the number of hit points.
     */
    public void setHitPoints(int num) {
        this.hitPoints = num;
    }
    /**
     * Set fill.
     * @param col - the fill.
     */
    public void setFill(Color col) {
        this.fillCol = col;
    }
    /**
     * Set fill.
     * @param img - the fill.
     */
    public void setFill(Image img) {
        this.fillImg = img;
    }
    /**
     * Set stroke.
     * @param col - the stroke.
     */
    public void setStroke(Color col) {
        this.stroke = col;
    }
    /**
     * Get stroke.
     * @return the stroke.
     */
    public Color getStroke() {
        return this.stroke;
    }
    /**
     * Set the fillCol map.
     * @param index - the place in the map.
     */
    public void setMapImgCol(int index) {
        if (this.fillCol != null) {
            this.colImg.put(index, this.fillCol);
            this.fillCol = null;
        } else if (this.fillImg != null) {
            this.colImg.put(index, this.fillImg);
            this.fillImg = null;
        }
    }
    /**
     * Create new blocks.
     * @param xpos - the x location.
     * @param ypos - the y location.
     * @return the new block.
     */
    @Override
    public Block create(int xpos, int ypos) {
        Block block = new Block(new Point(xpos, ypos), this.width, this.height);
        block.setMapImgCol(this.colImg);
        int key = this.hitPoints;
        if (key == 0) {
            key = 1;
        }
        Object fill = this.colImg.get(key);
        if (fill.getClass().toString().equals("class java.awt.Color")) {
            block.setColor((Color) fill);
        } else if (fill.getClass().toString().equals("class java.awt.image.BufferedImage")) {
            block.setImage((Image) fill);
        }
        block.setStroke(this.stroke);
        block.setNumOfHits(this.hitPoints);
        return block;
    }

    /**
     * Copy the data to another BasicBlock.
     * @return copy of this BasicBlock.
     */
    public BasicBlock clone() {
        BasicBlock copy = null;
        try {
            copy = (BasicBlock) super.clone();
            copy.colImg = (TreeMap<Integer, Object>) this.colImg.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
