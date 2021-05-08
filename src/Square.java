public class Square {
    public static String TYPE_PROPERTY = "Property";
    public static String TYPE_JAIL = "Jail";
    public static String TYPE_GO = "Go";
    public static String TYPE_FREEPARKING = "FreeParking";
    public static String TYPE_GOTOJAIL = "GoToJail";
    public static String TYPE_CardDraw = "CardDraw";
    public static String TYPE_TAX = "Tax";
    public static String TYPE_UTILITIES = "Utilities";
    public static String TYPE_NOTHING = "Nothing";


    private String type;
    private String name;
    private int rent;
    private int morgageValue;
    private int housePrice;
    private boolean isAvailable;
    private int boardSpaceNumber;
    private int houses;
    private int hotel;
    private int railroad;
    private Player ownedBy;
    private boolean mortgaged;
    private int landValue;
}
