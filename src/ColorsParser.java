import java.awt.Color;

/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21 */
public class ColorsParser {
    // parse color definition and return the specified color.
    /**
     * Create a color from string.
     * @param s - the string.
     * @return color.
     */
    public java.awt.Color colorFromString(String s) {
        java.lang.reflect.Field f = null;
        try {
            f = Class.forName("java.awt.Color").
                    getField(s.substring(0, s.length() - 1));
            try {
                Color col = (Color) f.get(null);
                return col;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
