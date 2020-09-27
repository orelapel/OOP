import java.util.Map;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constractors.
     * @param spacerWidths - Map of the spaces.
     * @param blockCreators - Map of the blocks.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }
    /**
     * Returns true if 's' is a valid space symbol.
     * @param s - the string we checking.
     * @return true/false.
     */
    public boolean isSpaceSymbol(String s) {
        for (String str : this.spacerWidths.keySet()) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns true if 's' is a valid block symbol.
     * @param s - the string we checking.
     * @return true/false.
     */
    public boolean isBlockSymbol(String s) {
        for (String str : this.blockCreators.keySet()) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the BlockCreator.
     * @param s - the key.
     * @return a BlockCreator.
     */
    public BlockCreator getBasicBlock(String s) {
        return this.blockCreators.get(s);
    }
    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s - the key.
     * @param xpos - the x location.
     * @param ypos - the y location.
     * @return a block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
         return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s - the key.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
