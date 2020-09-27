import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class LevelSpecificationReader {
    /**
     * Read the level specification from the file.
     * <p>
     * @param reader - the reader of the file.
     * @return list of levels.
     */
    public List<LevelInformation> fromReader(Reader reader) {
        BufferedReader buffReader = new BufferedReader(reader);
        List<LevelInformation> liList = new ArrayList<LevelInformation>();
        LevelGeneric levelInfo = new LevelGeneric();
        String line;
        InputStream is = null;
        int blockStartX = 0;
        int blockStartY = 0;
        int rowHeight = 0;
        int numBlocks = 0;
        try {
            while ((line = buffReader.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                    if (line.equals("") || line.contains("#")) {
                        continue;
                    }
                    levelInfo = new LevelGeneric();
                    line = buffReader.readLine();
                    while (!line.equals("END_LEVEL")) {
                        if (line.contains(":")) {
                            int index = line.indexOf(":");
                            String type = line.substring(0, index);
                            String val = line.substring(index + 1, line.length());
                            switch (type) {
                            case "level_name":
                                levelInfo.setLevelName(val);
                                line = buffReader.readLine();
                                break;
                            case "ball_velocities":
                                String[] velList = val.split(" ");
                                for (String v : velList) {
                                    String[] as = v.split(",");
                                    double a = Integer.parseInt(as[0]);
                                    double s = Integer.parseInt(as[1]);
                                    levelInfo.addBallVelocities(Velocity.fromAngleAndSpeed(a, s));
                                }
                                line = buffReader.readLine();
                                break;
                            case "background":
                                String[] bg = val.split("\\(");
                                if (bg[0].equals("image")) {
                                    Image img = null;
                                    try {
                                        String[] nameImg = bg[1].split("\\)");
                                        is = ClassLoader.getSystemClassLoader().
                                                getResourceAsStream(nameImg[0]);
                                        img = ImageIO.read(is);
                                        ImageSprite imgS = new ImageSprite(img);
                                        levelInfo.setBackground(imgS);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if (bg[0].equals("color")) {
                                    if (bg[1].contains("RGB")) {
                                        String[] xyz = bg[2].split(",");
                                        int x = Integer.parseInt(xyz[0]);
                                        int y = Integer.parseInt(xyz[1]);
                                        int z = Integer.parseInt(xyz[2].substring(0, xyz[2].
                                                length() - 2));
                                        Color col = new Color(x, y, z);
                                        ColorSprite colS = new ColorSprite(col);
                                        levelInfo.setBackground(colS);
                                    } else {
                                        ColorsParser cp = new ColorsParser();
                                        Color col = cp.colorFromString(bg[1]);
                                        ColorSprite colS = new ColorSprite(col);
                                        levelInfo.setBackground(colS);
                                    }
                                }
                                line = buffReader.readLine();
                                break; // complete
                            case "paddle_speed":
                                levelInfo.setPaddleSpeed(Integer.parseInt(val));
                                line = buffReader.readLine();
                                break;
                            case "paddle_width":
                                levelInfo.setPaddleWidth(Integer.parseInt(val));
                                line = buffReader.readLine();
                                break;
                            case "block_definitions":
                                is = ClassLoader.getSystemClassLoader().
                                        getResourceAsStream(val);
                                line = buffReader.readLine();
                                break;
                            case "blocks_start_x":
                                blockStartX = Integer.parseInt(val);
                                line = buffReader.readLine();
                                break;
                            case "blocks_start_y":
                                blockStartY = Integer.parseInt(val);
                                line = buffReader.readLine();
                                break;
                            case "row_height":
                                rowHeight = Integer.parseInt(val);
                                line = buffReader.readLine();
                                break;
                            case "num_blocks":
                                numBlocks = Integer.parseInt(val);
                                line = buffReader.readLine();
                                break;
                            case "START_BLOCKS":
                                Reader readerBlock = null;
                                BlocksDefinitionReader.fromReader(readerBlock);
                                line = buffReader.readLine();
                            default:
                                break;
                            }
                        } else if (line.equals("START_BLOCKS")) {
                            Reader readerBlock = null;
                            readerBlock = new InputStreamReader(is);
                            BlocksFromSymbolsFactory bfsf = BlocksDefinitionReader.fromReader(readerBlock);
                            line = buffReader.readLine();
                            while (!line.equals("END_BLOCKS")) {
                                String[] blockBuild = line.split("(?!^)");
                                int x = blockStartX;
                                for (String b : blockBuild) {
                                    if (bfsf.isBlockSymbol(b)) {
                                        Block newBlock = bfsf.getBlock(b, x, blockStartY);
                                        levelInfo.addBlock(newBlock);
                                        x += newBlock.getWidth();
                                    } else if (bfsf.isSpaceSymbol(b)) {
                                        x += bfsf.getSpaceWidth(b);
                                    }
                                }
                                blockStartY += rowHeight;
                                line = buffReader.readLine();
                            }
                            line = buffReader.readLine();
                        }
                    }
                }
                if (line.equals("") || line.contains("#")) {
                    continue;
                }
                liList.add(levelInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liList;
    }
}
