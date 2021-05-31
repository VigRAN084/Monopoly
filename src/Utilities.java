public class Utilities extends SquareType{
    private double propertyValue; //property value of the utility

    /**
     * Constructor for the Utilities Class
     * @param utilityVal
     * @param name
     * @param position
     * @param available
     */
    public Utilities (double utilityVal, String name, int position, boolean available) {
        super(SquareType.TYPE_UTILITIES, name,  available, position);
        this.propertyValue = utilityVal;
    }
    //getters and setters
    public double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(double propertyValue) {
        this.propertyValue = propertyValue;
    }
}
