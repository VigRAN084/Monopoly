public class BlankSquare extends SquareType {
    public BlankSquare () {
        super();
        setName(BLANK);
    }
    //generates blank square (used for populating the board)
    public static BlankSquare blankSquare() {
        return new BlankSquare();
    }
}
