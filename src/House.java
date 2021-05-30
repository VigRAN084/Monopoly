public class House extends SquareType {
    private double rent;
    private double mortgageValue;
    private double housePrice;
    private double propertyValue;

    private boolean mortgaged;

    private int houses = 0;
    private int hotel = 0;
    private int railroad = 0;

    public House (String name, double rent, double mortgageValue, double housePrice, double propertyValue, boolean isAvailable, int position) {
        super(SquareType.TYPE_PROPERTY, name,  isAvailable, position);
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.housePrice = housePrice;
        this.propertyValue = propertyValue;
    }

    public House() {
        this.rent = 0;
        this.mortgageValue = 0;
        this.housePrice = 0;
        this.propertyValue = 0;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getMortgageValue() {
        return mortgageValue;
    }

    public void setMortgageValue(double mortgageValue) {
        this.mortgageValue = mortgageValue;
    }

    public double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(double housePrice) {
        this.housePrice = housePrice;
    }


    public double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(double propertyValue) {
        this.propertyValue = propertyValue;
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
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
}
