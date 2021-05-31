public class Hotel extends House {
    /**
     * Constructor for Hotel class, which extends from House
     * @param name
     * @param rent
     * @param mortgageValue
     * @param housePrice
     * @param propertyValue
     * @param isAvailable
     * @param position
     */
    public Hotel (String name, double rent, double mortgageValue, double housePrice, double propertyValue, boolean isAvailable, int position) {
        super(name, rent, mortgageValue, housePrice,propertyValue, isAvailable, position);
        setTileType(SquareType.TYPE_HOTEL);
        setRent(getRent()*1.5);
    }

    /**
     * Upgrades house to hotel; called when the number of houses is greater than or equal to 4
     * @param h
     * @return
     */
    public static Hotel upgradeHouseToHotel(House h) {
        Hotel hotel = new Hotel(h.getName(), h.getRent(), h.getMortgageValue(), h.getHousePrice(), h.getPropertyValue(),
                                h.isAvailable(), h.getPosition());
        return hotel;
    }
}
