import java.util.ArrayList;

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


    private String type;
    private String name;
    private double rent;
    private int mortgageValue;
    private int housePrice;
    private boolean isAvailable;
    private int position;
    private int propertyValue;
    private Player ownedBy;
    private ArrayList<Player> playersOnSpace = new ArrayList<>();
    private boolean mortgaged;

    private int houses = 0;
    private int hotel = 0;
    private int railroad = 0;

    public boolean isMortgaged() {
        return mortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }

    public ArrayList<Player> getPlayersOnSpace() {
        return playersOnSpace;
    }

    public void setPlayersOnSpace(ArrayList<Player> playersOnSpace) {
        this.playersOnSpace = playersOnSpace;
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

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
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

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
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

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public int getRailroad() {
        return railroad;
    }

    public void setRailroad(int railroad) {
        this.railroad = railroad;
    }

    public Square(){}

    public Square (String type, String name, /*int rent, int mortgageValue, int housePrice,
                   int propertyValue,*/ boolean isAvailable, int position) {
        this.type = type;
        this.name = name;
        /*this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.housePrice = housePrice;
        this.propertyValue = propertyValue;*/
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
            if (this.playersOnSpace.size()>0) {
                s = "";
                for(Player p: playersOnSpace ){
                    s += p.getName() + "";
                }
                if (s.length() < 2){
                    s += " ";
                }
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
    public void addPlayerOnSpace(Player p){
        this.playersOnSpace.add(p);
    }
    public void removePlayerOnSpace(Player p) {
        this.playersOnSpace.remove(p);
    }
}
