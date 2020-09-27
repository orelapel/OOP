/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class LevelSet {
    private String key;
    private String path;
    private String desc;
    /**
     * Constructor.
     * @param description the description line.
     * @param path the path line.
     */
    public LevelSet(String description, String path) {
        String[] keyDesk = description.split(":");
        this.key = keyDesk[0];
        this.path = path;
        this.desc = keyDesk[1];
    }
    /**
     * Returns the key that matches the level.
     * @return a string of the key.
     */
    public String getKey() {
        return this.key;
    }
    /**
     * Returns the path.
     * @return a string of the path of the level.
     */
    public String getPath() {
        return this.path;
    }
    /**
     * Returns the title of the level.
     * @return a string of the level name.
     */
    public String getDescription() {
        return this.desc;
    }
}
