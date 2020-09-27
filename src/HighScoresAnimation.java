import java.awt.Color;
import biuoop.DrawSurface;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Constractors.
     * @param scores - the score table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }
    /**
     * Show the score table.
     * @param d - the surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.MAGENTA);
        d.drawText(10, 50, "High Scores", 32);
        d.setColor(Color.GREEN);
        d.drawText(50, 100, "Player Name                                      Score", 30);
        d.drawText(50, 110, "______________________________________________", 30);
        d.setColor(Color.MAGENTA);
        for (int i = 0; i < this.scores.size(); i++) {
            if (i < this.scores.getHighScores().size()) {
                d.drawText(50, 140 + 25 * i, this.scores.getHighScores().get(i).getName(), 25);
                d.drawText(500, 140 + 25 * i,
                        Integer.toString(this.scores.getHighScores().get(i).getScore()), 25);
            }
        }
    }

    /**
     * When to stop.
     * @return true/false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

}
