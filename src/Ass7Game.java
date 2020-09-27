import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Orel Lichtenstein
 * @version 1.8
 * @since 2019-05-28 */
public class Ass7Game {
    /**
     * Initialize and run the game.
     * <p>
     * @param args - parameters from the user.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, 600);
        Sleeper sleeper = new biuoop.Sleeper();

        File highscores = new File("highscores.txt");
        try {
            highscores.createNewFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        HighScoresTable sTable = HighScoresTable.loadFromFile(highscores);

        // Create an animation runner and a game flow and run the game.
        AnimationRunner ar = new AnimationRunner(gui, 60, sleeper);
        HighScoresAnimation sAni = new HighScoresAnimation(sTable);

        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Level Sets", gui.getKeyboardSensor(), ar);
        InputStream is = null;
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        if (args.length == 0) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        } else {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        }
        Reader reader = new InputStreamReader(is);
        BufferedReader buffReader = new BufferedReader(reader);
        CreateLevelFromFile setLevel = new CreateLevelFromFile(buffReader);
        Map<String, List<LevelInformation>> levelsMap =
                new TreeMap<String, List<LevelInformation>>();
        levelsMap = setLevel.buildLevelsFromFile();
        Map<String, String> nameFromKey = setLevel.getNameFromKey();
        for (Map.Entry<String, List<LevelInformation>> entry : levelsMap.entrySet()) {
            List<LevelInformation> levelsListFile = entry.getValue();
            Task<Void> levelsListTask = new Task<Void>() {
                private GameFlow gf = new GameFlow(ar, gui.getKeyboardSensor(), gui, sTable);
                public Void run() {
                    this.gf.runLevels(levelsListFile);
                    return null;
                }
            };
            String key = entry.getKey();
            String message = nameFromKey.get(key);
            subMenu.addSelection(key, message, levelsListTask);
        }

        is = ClassLoader.getSystemClassLoader().getResourceAsStream("definitions/level_definitions.txt");
        reader = new InputStreamReader(is);

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", gui.getKeyboardSensor(), ar);

        Task<Void> endTask = new Task<Void>() {
            public Void run() {
                int n = 0;
                Runtime.getRuntime().exit(n);
                return null;
            }
        };

        // the parameters to addSelection are:
        // key to wait for, line to print, what to return
        menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("h", "High Score", new ShowHiScoresTask(ar, sAni, gui.getKeyboardSensor()));
        menu.addSelection("q", "Quit", endTask);

        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.reset();
        }
    }
}