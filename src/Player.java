import java.util.Scanner;

public class Player {
    public static String[] PLAY_OPTIONS = {
            "1: Roll",
            "2: Buy Houses or Hotels",
            "3: Trade with another Player",
            "4: Unmortgage",
            "5: Quit Playing Game"
    };

    private String piece;
    private int money;
    private boolean myTurn = false;
    private Square square = null;

    public Player() {}

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
        this.square.setOwnedBy(this);
    }

    public void playTurn() {
        if (!this.isMyTurn()) return;
        System.out.println("Player " + piece + "'s turn");
        int option = promptOption();
        if (option == 1){
            roll();
        }
    }

    private int promptOption() {
        System.out.println("Your options are: ");
        for (String s: PLAY_OPTIONS){
            System.out.println(s);
        }
        boolean validInput = false;
        int optionNum = -1;
        while(!validInput) {
            System.out.println("Please enter a number between 1 and 5, inclusive");
            Scanner scanner = new Scanner(System.in);
            optionNum = scanner.nextInt();
            if (optionNum >= 1 && optionNum <= 5){
                validInput = true;
            }
            else{
                System.out.println("Invalid input");
            }
        }
        return optionNum;
    }

    public void roll () {
        int dice1 = diceValue();
        int dice2 = diceValue();
        int sum = dice1 + dice2;
        System.out.println("Rolling ... dice1: " + dice1 + " , dice2: " + dice2);
        Square currSquare = this.square;
        int currPosition = currSquare.getPosition();
        int newPosition = currPosition + sum;
        if (newPosition > 40) {
            newPosition -= 40;
        }
        currSquare.setOwnedBy(null);
        setSquare(Board.newBoard.getSquare(newPosition));
        System.out.println("Moving to Position: " + this.square.getPosition() + " Name: "  + this.square.getName());
    }

    public int diceValue () {
        return (int)((Math.random() * 6) + 1);
    }

}
