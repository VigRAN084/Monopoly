public class House extends Property {
    private double housePrice;
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
}
