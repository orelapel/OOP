import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> scoreList;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    /**
     * Constractors.
     * @param size - the size of the table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreList = new ArrayList<ScoreInfo>(this.size);
    }

    // Add a high-score.
    /**
     * Add new score to the table.
     * @param score - the new score.
     */
    public void add(ScoreInfo score) {
        // Add this new score anyway, if the list is not full
        boolean addScore = (this.scoreList.size() < this.size);

        // Remove the last score from full list if the new score is better
        if (this.scoreList.size() == this.size
                && score.getScore() > this.scoreList.get(this.size - 1).getScore()) {
            // Remove last score
            this.scoreList.remove(this.size - 1);

            // Better score, so add it to the list
            addScore = true;
        }

        // If the score must be added to the list, do it
        if (addScore) {
            // Add the new score to the list
            this.scoreList.add(score);

            // Sort the list in descending order
            Collections.sort(this.scoreList, new Comparator<ScoreInfo>() {
                /**
                 * compare between two scores.
                 * @return -1 - less than, 1 - greater than, 0 - equal.
                 */
                @Override
                public int compare(ScoreInfo lhs, ScoreInfo rhs) {
                    return lhs.getScore() > rhs.getScore() ? -1 : (lhs.getScore() < rhs.getScore()) ? 1 : 0;
                }
            });
        }
    }

    /**
     * Return table size.
     * @return the table size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores.
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreList;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    /**
     * Return the rank of the current score: where will it be on the list if added?
     * @param score - the score we checking.
     * @return the rank.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.scoreList.size(); i++) {
            if (score > this.scoreList.get(i).getScore()) {
                return i + 1;
            }
        }
        return this.scoreList.size() + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreList.removeAll(scoreList);
    }

    /**
     * Load table data from file.
     * @param filename - the name of the file we load from.
     * @throws IOException - if we cant get to the file.
     */
    public void load(File filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String text = null;

            while ((text = reader.readLine()) != null) {
                int index = text.indexOf(" ");
                String name = text.substring(0, index);
                int score = Integer.parseInt(text.substring(index + 1, text.length()));
                this.scoreList.add(new ScoreInfo(name, score));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fail in loading file " + e.getMessage());
        }
        reader.close();
    }

    /**
     * Save table data to the specified file.
     * @param filename - the file we want to save.
     * @throws IOException - if we cant get to the file.
     */
    public void save(File filename) throws IOException {
        FileWriter fWrite = new FileWriter(filename);
        for (ScoreInfo s : this.scoreList) {
            fWrite.write(s.getName() + " " + s.getScore());
            fWrite.write(System.getProperty("line.separator"));
        }
        fWrite.close();
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    /**
     * Read a table from file and return it.
     * @param filename - the file we want to load from.
     * @return the high score table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        try {
            table.load(filename);
        } catch (IOException e) {
            return null;
        }
        return table;
    }
}
