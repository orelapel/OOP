/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-03 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * Constractor.
     * <p>
     * @param name - the name of the player.
     * @param score - his score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * Get the name of the player.
     * <p>
     * @return the name of the player.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Get the score of the player.
     * <p>
     * @return the score of the player.
     */
    public int getScore() {
        return this.score;
    }
}
