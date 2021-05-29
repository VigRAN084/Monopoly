public class House extends Square {
    private int rent;
    private int mortgageValue;
    private int housePrice;
    private int propertyValue;
    public House (String type, String name, int r, int m, int h, int p, /*int rent, int mortgageValue, int housePrice,
                  int propertyValue,*/ boolean isAvailable, int position) {
        super(type, name, /*rent, mortgageValue, housePrice, propertyValue,*/ isAvailable, position);
        this.rent = r;
        this.mortgageValue = m;
        this.housePrice = h;
        this.propertyValue = p;
    }

}
