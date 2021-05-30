public class BlankSquare extends SquareType {
    public BlankSquare () {
        super();
        setName(BLANK);
    }
    public static BlankSquare blankSquare() {
        return new BlankSquare();
    }
}
