public class Chance extends SquareType {
    public static String[] chanceCards = {"Get $200", "Pay $200", "Go To Jail", "Go To 'Go'",
            "Move 3 spaces forward", "Do Nothing", "Draw Another Chance Card"};
    public Chance (int position) {
        super(SquareType.TYPE_CHANCE, "Chance",  false, position);
    }
}
