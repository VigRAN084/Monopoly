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
    private int position;
    private int landValue;
    private Player ownedBy;
    private Player playerOnSpace;

    private int houses;
    private int hotel;
    private int railroad;
    private boolean mortgaged;

    public Player getPlayerOnSpace() {
        return playerOnSpace;
    }

    public void setPlayerOnSpace(Player playerOnSpace) {
        this.playerOnSpace = playerOnSpace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public void setMortgageValue(int mortgageValue) {
        this.mortgageValue = mortgageValue;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getLandValue() {
        return landValue;
    }

    public void setLandValue(int landValue) {
        this.landValue = landValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Player getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Player ownedBy) {
        this.ownedBy = ownedBy;
    }

    public Square(){}

    public Square (String type, String name, int rent, int mortgageValue, int housePrice,
                   int landValue, boolean isAvailable, int position) {
        this.type = type;
        this.name = name;
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.housePrice = housePrice;
        this.landValue = landValue;
        this.isAvailable = isAvailable;
        this.position = position;
    }

    public static Square blankSquare() {
        Square s = new Square();
        s.name = BLANK;
        return s;
    }

    public String toString() {
        return this.position + ": " + this.name;
    }

    public String printSquare() {
        String s = this.name;
        if (!s.equals(BLANK)){
            if (this.playerOnSpace != null) {
                s = this.playerOnSpace.getName() + " ";
            } else {
                if(this.position < 10){
                    s = this.position + " ";
                } else {
                    s = this.position + "";
                }
            }
        }
        return s;
    }
}
