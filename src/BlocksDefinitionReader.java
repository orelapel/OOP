import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class BlocksDefinitionReader {
    private static BasicBlock block = new BasicBlock();

    /**
     * Make a BlocksFromSymbolsFactory from the reader.
     * @param reader - reader to the file.
     * @return BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader buffReader = new BufferedReader(reader);
        Map<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();
        Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
        String line;
        try {
            while ((line = buffReader.readLine()) != null) {
                if (line.equals("") || line.contains("#")) {
                    continue;
                }
                String[] str = line.split(" ");
                String type = str[0];
                if (type.equals("default")) {
                    for (String s : str) {
                        String[] trait = s.split(":");
                        switch (trait[0]) {
                        case "height":
                            block.setHeight(Integer.parseInt(trait[1]));
                            break;
                        case "width":
                            block.setWeidth(Integer.parseInt(trait[1]));
                            break;
                        case "hit_points":
                            block.setHitPoints(Integer.parseInt(trait[1]));
                            break;
                        case "fill":
                            checkingFill(str[1], trait[1]);
                            break;
                        case "stroke":
                            String[] stroke = trait[1].split("\\(");
                            if (str[1].contains("RGB")) {
                                String[] xyz = stroke[2].split(",");
                                int x = Integer.parseInt(xyz[0]);
                                int y = Integer.parseInt(xyz[1]);
                                int z = Integer.parseInt(xyz[2].substring(0, xyz[2].
                                        length() - 2));
                                block.setStroke(new Color(x, y, z));
                            } else {
                                ColorsParser cp = new ColorsParser();
                                block.setStroke(cp.colorFromString(stroke[1]));
                            }
                            break;
                        default:
                            break;
                        }
                    }
                } else if (type.contains("bdef")) {
                    BasicBlock newBlock = new BasicBlock();
                    String symbol = null;
                    newBlock = block.clone();
                    for (String s : str) {
                        String[] trait = s.split(":");
                        switch (trait[0]) {
                        case "symbol":
                            symbol = trait[1];
                            break;
                        case "height":
                            newBlock.setHeight(Integer.parseInt(trait[1]));
                            break;
                        case "width":
                            newBlock.setWeidth(Integer.parseInt(trait[1]));
                            break;
                        case "hit_points":
                            newBlock.setHitPoints(Integer.parseInt(trait[1]));
                            break;
                        case "stroke":
                            String[] stroke = str[1].split("\\(");
                            if (str[1].contains("RGB")) {
                                String[] xyz = stroke[2].split(",");
                                int x = Integer.parseInt(xyz[0]);
                                int y = Integer.parseInt(xyz[1]);
                                int z = Integer.parseInt(xyz[2].substring(0, xyz[2].
                                        length() - 2));
                                newBlock.setFill(new Color(x, y, z));
                            } else {
                                String[] stroke2 = stroke[1].split("\\)");
                                ColorsParser cp = new ColorsParser();
                                newBlock.setFill(cp.colorFromString(stroke2[0]));
                            }
                            break;
                        default:
                            break;
                        }
                        if (trait[0].contains("fill")) {
                            int place = 0;
                            if (trait[0].contains("-")) {
                                String[] fillNum = trait[0].split("-");
                                place = Integer.parseInt(fillNum[1]);
                            } else {
                                place = 1;
                            }
                            String[] fill = trait[1].split("\\(");
                            if (trait[1].contains("color")) {
                                if (trait[1].contains("RGB")) {
                                    String[] xyz = fill[2].split(",");
                                    int x = Integer.parseInt(xyz[0]);
                                    int y = Integer.parseInt(xyz[1]);
                                    int z = Integer.parseInt(xyz[2].substring(0, xyz[2].
                                            length() - 2));
                                    newBlock.setFill(new Color(x, y, z));
                                } else {
                                    ColorsParser cp = new ColorsParser();
                                    newBlock.setFill(cp.colorFromString(fill[1]));
                                }
                            } else if (trait[1].contains("image")) {
                                Image img = null;
                                try {
                                    String[] nameImg = fill[1].split("\\)");
                                    InputStream is = ClassLoader.getSystemClassLoader().
                                            getResourceAsStream(nameImg[0]);
                                    img = ImageIO.read(is);
                                    newBlock.setFill(img);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            newBlock.setMapImgCol(place);
                        }
                    }
                    blockCreators.put(symbol, newBlock);
                } else if (type.contains("sdef")) {
                    String symbol = null;
                    int width = 0;
                    for (String s : str) {
                        String[] trait = s.split(":");
                        if (trait[0].equals("symbol")) {
                            symbol = trait[1];
                        } else if (trait[0].equals("width")) {
                            width = Integer.parseInt(trait[1]);
                        }
                    }
                    spacerWidths.put(symbol, width);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BlocksFromSymbolsFactory bfsf = new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
        return bfsf;
    }
    /**
     * Checking what type of fill is it.
     * @param str - the fill in string.
     * @param trait - the trait of the fill.
     */
    private static void checkingFill(String str, String trait) {
        String[] fill = str.split("\\(");
        if (trait.contains("color")) {
            if (trait.contains("RGB")) {
                String[] xyz = fill[2].split(",");
                int x = Integer.parseInt(xyz[0]);
                int y = Integer.parseInt(xyz[1]);
                int z = Integer.parseInt(xyz[2].substring(0, xyz[2].
                        length() - 2));
                block.setFill(new Color(x, y, z));
            } else {
                ColorsParser cp = new ColorsParser();
                block.setFill(cp.colorFromString(fill[1].split("\\)")[0]));
            }
        } else if (trait.contains("image")) {
            Image img = null;
            try {
                String[] nameImg = fill[1].split("\\)");
                InputStream is = ClassLoader.getSystemClassLoader().
                        getResourceAsStream(nameImg[0]);
                img = ImageIO.read(is);
                block.setFill(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}