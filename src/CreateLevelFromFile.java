import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class CreateLevelFromFile {
    private List<LevelSet> setLevels;
    private Map<String, String> nameFromKey;

    /**
     * Constructor.
     * @param reader the file we read from.
     */
    public CreateLevelFromFile(java.io.Reader reader) {
        int i = 0;
        BufferedReader newReader = (BufferedReader) reader;
        this.setLevels = new ArrayList<LevelSet>();
        this.nameFromKey = new TreeMap<String, String>();
        String oddLine = null;
        String evenLine = null;
        try {
            while ((oddLine = newReader.readLine()) != null) {
                evenLine = newReader.readLine();
                this.setLevels.add(new LevelSet(oddLine, evenLine));
                this.nameFromKey.put(this.setLevels.get(i).getKey(),
                        this.setLevels.get(i).getDescription());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the name level which matches the key.
     * @param key our key.
     * @return the level name.
     */
    public String getLevelName(String key) {
        return this.nameFromKey.get(key);
    }
    /**
     * Returns a map which contains the a list of level information.
     * @return map.
     */
    public  Map<String, List<LevelInformation>> buildLevelsFromFile() {
        Map<String, List<LevelInformation>> map = new
                TreeMap<String, List<LevelInformation>>();
        List<LevelInformation> l;
        for (int i = 0; i < this.setLevels.size(); i++) {
            l = new ArrayList<LevelInformation>();
            String text = this.setLevels.get(i).getPath();
            InputStream is = ClassLoader.getSystemClassLoader().
                    getResourceAsStream(text);
            InputStreamReader reader = new InputStreamReader(is);
            LevelSpecificationReader ls = new LevelSpecificationReader();
            List<LevelInformation> li = ls.fromReader(reader);
            l.addAll(li);
            map.put(this.setLevels.get(i).getKey(), l);
        }
        return map;
    }
    /**
     * Get the nameFromKey map.
     * @return the nameFromKey map.
     */
    public Map<String, String> getNameFromKey() {
        return this.nameFromKey;
    }
}
