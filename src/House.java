public class House extends SquareType {
    private double rent;
    private double mortgageValue;
    private double housePrice;
    private double propertyValue;
    public House (String type, String name, double r, double m, double h, double p, boolean isAvailable, int position) {
        super(type, name,  isAvailable, position);
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

    public static SquareType blankSquare() {
        SquareType s = new House();
        s.setName(BLANK);
        return s;
    }
}
