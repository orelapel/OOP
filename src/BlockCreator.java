/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public interface BlockCreator {
    // Create a block at the specified location.
    /**
     * Create new blocks.
     * @param xpos - the x location.
     * @param ypos - the y location.
     * @return the new block.
     */
    Block create(int xpos, int ypos);
}
