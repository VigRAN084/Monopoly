public class Utilities extends SquareType{
    private double propertyValue;
    public Utilities (double utilityVal, String name, int position, boolean available) {
        super(SquareType.TYPE_UTILITIES, name,  available, position);
        this.propertyValue = utilityVal;
    }

    public double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(double propertyValue) {
        this.propertyValue = propertyValue;
    }
}
