public class House extends Property {
    private double housePrice;
    private int numHouses = 0;

    /**
     * Constructor for the House class
     * @param name
     * @param rent
     * @param mortgageValue
     * @param housePrice
     * @param propertyValue
     * @param isAvailable
     * @param position
     */
    public House (String name, double rent, double mortgageValue, double housePrice, double propertyValue, boolean isAvailable, int position) {
        super(name, SquareType.TYPE_HOUSE, rent, mortgageValue, propertyValue, isAvailable, position);
        this.housePrice = housePrice;
    }

    /**
     * getters and setters
     * @return
     */
    public double getHousePrice() {
        return housePrice;
    }
    public void setHousePrice(double housePrice) {
        this.housePrice = housePrice;
    }
    public int getNumHouses() {
        return numHouses;
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }

    /**
     * Add a house to the number of houses on a property and multiply the rent by 1.25 for each successive house added
     */
    public void addHouse() {
        this.numHouses++;
        this.setRent(this.getRent()*1.25);
    }
}
