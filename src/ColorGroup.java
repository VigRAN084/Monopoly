import java.util.Hashtable;

public class ColorGroup {
    public static String COLOR_TEEL = "Teel";
    public static String COLOR_TAN = "Tan";
    public static String COLOR_ORANGE = "Orange";
    public static String COLOR_BLACK = "Black";
    public static String COLOR_PURPLE = "Purple";
    public static String COLOR_RED = "Red";
    public static String COLOR_BLUE = "Blue";
    public static String COLOR_GREEN = "Green";


    public static String getColorGroup(int position) {
        String colorGroup = "";
        if (position >= 1 && position <= 5) {
            colorGroup = ColorGroup.COLOR_TEEL;
        }
        else if (position >= 6 && position <= 10) {
            colorGroup = ColorGroup.COLOR_TAN;
        }
        else if (position >= 11 && position <= 15) {
            colorGroup = ColorGroup.COLOR_ORANGE;
        }
        else if (position >= 16 && position <= 20) {
            colorGroup = ColorGroup.COLOR_BLACK;
        }
        else if (position >= 21 && position <= 25) {
            colorGroup = ColorGroup.COLOR_PURPLE;
        }
        else if (position >= 26 && position <= 30) {
            colorGroup = ColorGroup.COLOR_RED;
        }
        else if (position >= 31 && position <= 35) {
            colorGroup = ColorGroup.COLOR_BLUE;
        }
        else if (position >= 36 && position <= 40) {
            colorGroup = ColorGroup.COLOR_GREEN;
        }
        return colorGroup;
    }

    private static Hashtable<String,int[]> getColorGroupIndexRange() {
        Hashtable<String,int[]> indexRng = new Hashtable<>();
        indexRng.put(COLOR_TEEL, new int[]{1,5});
        indexRng.put(COLOR_TAN, new int []{6,10});
        indexRng.put(COLOR_ORANGE, new int []{11,15});
        indexRng.put(COLOR_BLACK, new int []{16,20});
        indexRng.put(COLOR_PURPLE, new int []{21,25});
        indexRng.put(COLOR_RED, new int []{26,30});
        indexRng.put(COLOR_BLUE, new int []{31,35});
        indexRng.put(COLOR_GREEN, new int []{36,40});
        return indexRng;
    }

    public static int[] getColorRange(String color) {
        return getColorGroupIndexRange().get(color);
    }
}
