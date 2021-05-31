import java.util.ArrayList;

public abstract class SquareType {
    //different SquareTypes
    public static final String BLANK = "  ";
    public static String TYPE_JAIL = "Jail";
    public static String TYPE_GO = "Go";
    public static String TYPE_FREEPARKING = "FreeParking";
    public static String TYPE_GOTOJAIL = "GoToJail";
    public static String TYPE_CHANCE = "Chance";
    public static String TYPE_TAX = "Tax";
    public static String TYPE_UTILITIES = "Utilities";
    public static String TYPE_HOUSE = "House";
    public static String TYPE_RAILROAD = "Railroad";
    public static String TYPE_HOTEL = "Hotel";

    private String tileType;
    private String name;
    private boolean isAvailable;
    private int position;
    private Player ownedBy;
    private ArrayList<Player> playersOnSpace = new ArrayList<>();

    //getters and setters
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

    //constructors for SquareType class
    public SquareType(){}

    public SquareType(String tileType, String name, /*int rent, int mortgageValue, int housePrice,
                   int propertyValue,*/ boolean isAvailable, int position) {
        this.tileType = tileType;
        this.name = name;
        this.isAvailable = isAvailable;
        this.position = position;
    }

    /**
     * determines if the SquareType is a property
     * @return
     */
    public boolean isProperty() {
        return (this.getTileType().equals(SquareType.TYPE_HOUSE) || this.getTileType().equals(SquareType.TYPE_RAILROAD)
                || this.getTileType().equals(SquareType.TYPE_HOTEL));
    }

    /**
     * determines if the SquareType is a house
     * @return
     */
    public boolean isHouse() {
        return (this.getTileType().equals(SquareType.TYPE_HOUSE) || this.getTileType().equals(SquareType.TYPE_HOTEL));
    }

    /**
     * determines if the SquareType is a tax square
     * @return
     */
    public boolean isTax() {
        return (this.getTileType().equals(SquareType.TYPE_TAX));
    }
    /**
     * determines if the SquareType is a jail square
     * @return
     */
    public boolean isJail() {
        return (this.getTileType().equals(SquareType.TYPE_JAIL));
    }
    /**
     * determines if the SquareType is a chance square
     * @return
     */
    public boolean isChance() {
        return (this.getTileType().equals(SquareType.TYPE_CHANCE));
    }
    /**
     * determines if the SquareType is a free parking square
     * @return
     */
    public boolean isFreeParking() {
        return (this.getTileType().equals(SquareType.TYPE_FREEPARKING));
    }
    /**
     * determines if the SquareType is a Go square
     * @return
     */
    public boolean isGo() {
        return (this.getTileType().equals(SquareType.TYPE_GO));
    }
    /**
     * determines if the SquareType is a Go To Jail square
     * @return
     */
    public boolean isGoToJail() {
        return (this.getTileType().equals(SquareType.TYPE_GOTOJAIL));
    }
    /**
     * determines if the SquareType is a Utilities square
     * @return
     */
    public boolean isUtilities() {
        return (this.getTileType().equals(SquareType.TYPE_UTILITIES));
    }
    public String toString() {
        return this.position + ": " + this.name;
    }

    /**
     * adds player to space
     * @param p
     */
    public void addPlayerOnSpace(Player p){
        this.playersOnSpace.add(p);
    }

    /**
     * removes player from space
     * @param p
     */
    public void removePlayerOnSpace(Player p) {
        this.playersOnSpace.remove(p);
    }

    /**
     * prints a square
     * @return
     */
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
}
