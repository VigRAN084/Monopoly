public class House extends Property {
    private double housePrice;
    private int numHouses = 0;
    private int hotel = 0;
    public House (String name, double rent, double mortgageValue, double housePrice, double propertyValue, boolean isAvailable, int position) {
        super(name, SquareType.TYPE_HOUSE, rent, mortgageValue, propertyValue, isAvailable, position);
        this.housePrice = housePrice;
    }

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

    public int getHotel() {
        return hotel;
    }

    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    public void addHouse() {
        this.numHouses++;
        this.setRent(this.getRent()*1.25);
    }
}
