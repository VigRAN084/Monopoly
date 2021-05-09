public class Square {
    public static final String BLANK = "  ";
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
    private int mortgageValue;
    private int housePrice;
    private boolean isAvailable;
    private int squareNumber;
    private int landValue;

    private int houses;
    private int hotel;
    private int railroad;
    private Player ownedBy;
    private boolean mortgaged;

    public Square(){}

    public Square (String type, String name, int rent, int mortgageValue, int housePrice,
                   int landValue, boolean isAvailable, int squareNumber) {
        this.type = type;
        this.name = name;
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.housePrice = housePrice;
        this.landValue = landValue;
        this.isAvailable = isAvailable;
        this.squareNumber = squareNumber;
    }

    public static Square blankSquare() {
        Square s = new Square();
        s.name = BLANK;
        return s;
    }

    public String toString() {
        String s = this.name;
        if (!s.equals(BLANK)){
            if(this.squareNumber < 10){
                s = this.squareNumber + " ";
            } else {
                s = this.squareNumber + "";
            }
        }
        return s;
    }
}
