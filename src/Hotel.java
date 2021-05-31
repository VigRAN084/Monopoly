public class Hotel extends House {

    public Hotel (String name, double rent, double mortgageValue, double housePrice, double propertyValue, boolean isAvailable, int position) {
        super(name, rent, mortgageValue, housePrice,propertyValue, isAvailable, position);
        setTileType(SquareType.TYPE_HOTEL);
        setRent(getRent()*1.5);
    }
    public static Hotel upgradeHouseToHotel(House h) {
        Hotel hotel = new Hotel(h.getName(), h.getRent(), h.getMortgageValue(), h.getHousePrice(), h.getPropertyValue(),
                                h.isAvailable(), h.getPosition());
        return hotel;
    }
}
