import java.util.ArrayList;

public abstract class SquareType {
    public static final String BLANK = "  ";
    public static String TYPE_PROPERTY = "Property";
    public static String TYPE_JAIL = "Jail";
    public static String TYPE_GO = "Go";
    public static String TYPE_FREEPARKING = "FreeParking";
    public static String TYPE_GOTOJAIL = "GoToJail";
    public static String TYPE_CardDraw = "CardDraw";
    public static String TYPE_TAX = "Tax";
    public static String TYPE_UTILITIES = "Utilities";


    private String tileType;
    private String name;
    private boolean isAvailable;
    private int position;
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

    public String getTileType() {
        return tileType;
    }

    public void setTileType(String tileType) {
        this.tileType = tileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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

    public SquareType(){}

    public SquareType(String tileType, String name, /*int rent, int mortgageValue, int housePrice,
                   int propertyValue,*/ boolean isAvailable, int position) {
        this.tileType = tileType;
        this.name = name;
        this.isAvailable = isAvailable;
        this.position = position;
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
