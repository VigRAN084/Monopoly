public class Chance extends SquareType {
    //static variable containing the different chance cards to be used
    public static String[] chanceCards = {"Get $200", "Pay $200", "Go To Jail", "Go To 'Go'",
            "Move 3 spaces forward", "Do Nothing", "Draw Another Chance Card"};
    //constructor
    public Chance (int position) {
        super(SquareType.TYPE_CHANCE, "Chance",  false, position);
    }
}
