public class Property extends SquareType {

    private double rent;
    private double mortgageValue;
    private double propertyValue;
    private boolean mortgaged;

    /**
     * Constructor for the Property class
     * @param name
     * @param type
     * @param rent
     * @param mortgageValue
     * @param propertyValue
     * @param isAvailable
     * @param position
     */
    public Property(String name, String type, double rent, double mortgageValue, double propertyValue, boolean isAvailable, int position) {
        super(type, name,  isAvailable, position);
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.propertyValue = propertyValue;
    }
    //getters and setters
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



}
