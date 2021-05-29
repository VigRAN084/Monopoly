public class House extends Square {
    private double rent;
    private double mortgageValue;
    private double housePrice;
    private double propertyValue;
    public House (String type, String name, double r, double m, double h, double p, /*int rent, int mortgageValue, int housePrice,
                  int propertyValue,*/ boolean isAvailable, int position) {
        super(type, name, /*rent, mortgageValue, housePrice, propertyValue,*/ isAvailable, position);
        this.rent = r;
        this.mortgageValue = m;
        this.housePrice = h;
        this.propertyValue = p;
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

}
