import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Pieces{
    public static String[] PLAY_OPTIONS = {
            "1: Roll",
            "2: Buy Houses or Hotels",
            "3: Trade with another Player",
            "4: Unmortgage",
            "5: Quit Playing Game"
    };

    private String name;
    private int money;
    private boolean myTurn = false;
    private Square square = null;
    private String owner;
    private ArrayList<Square> ownedProperties = new ArrayList<>();

    public Player() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
        this.square.addPlayerOnSpace(this);
    }

    public void credit(int money) {
        this.money += money;
    }

    public void debit(int money) {
        this.money -= money;
    }

    public void playTurn() {
        if (!this.isMyTurn()) return;
        System.out.println("Player " + name + "'s turn");
        int option = promptOption();
        if (option == 1){
            roll();
        }
        System.out.println("Player " + this.getName() + " summary:");
        System.out.println("\nMoney: $" + this.getMoney());
        System.out.println("Jail Cards Owned: ");
        System.out.println("Land Owned: " + this.ownedProperties);
        System.out.println();
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
        boolean isDouble = (dice1 == dice2);
        System.out.println("Rolling ... dice1: " + dice1 + " , dice2: " + dice2);
        movePlayer(sum);
        System.out.println("Moving to Position: " + this.square.getPosition() + " Name: "  + this.square.getName());
        if (this.square.getType().equals(Square.TYPE_PROPERTY)) {
            buyOrRentProperty();
        } else if (this.square.getType().equals(Square.TYPE_TAX)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_GOTOJAIL)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_JAIL)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_UTILITIES)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_NOTHING)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_CardDraw)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_FREEPARKING)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_GO)) {
            //@TODO
        }
    }

    private void movePlayer(int sum) {
        Board board = Board.newBoard;
        Square currSquare = this.square;
        int currPosition = currSquare.getPosition();
        int newPosition = currPosition + sum;
        if (newPosition > 40) {
            newPosition -= 40;
            this.credit(200);
        }
        currSquare.removePlayerOnSpace(this);
        Square newSquare = board.getSquare(newPosition);
        setSquare(newSquare);
    }

    private void buyOrRentProperty() {
        if(this.square.isAvailable()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to buy this property for $" + this.square.getPropertyValue() + " (Y/n)");
            String decisionToBuy = scanner.nextLine();
            if (decisionToBuy.equalsIgnoreCase("Y")) {
                buyProperty(this.square);
            }
        } else if (!this.square.isAvailable() && !this.square.isMortgaged()){
            Player owner = this.square.getOwnedBy();
            System.out.println("You owe " + owner.getName() + " " + this.square.getRent());
            rentProperty(this.square);
        }
    }

    private boolean rentProperty(Square s) {
        Player owner = this.square.getOwnedBy();
        int rent = this.square.getRent();
        System.out.println("You owe " + owner.getName() + " " + rent);
        int currentBalance = this.getMoney();
        if (currentBalance >= rent) {
            this.debit(rent);
            owner.credit(rent);
            return true;
        } else {
            System.out.println("You don't have enough balance to pay the rent");
            return false;
        }
    }

    private boolean buyProperty(Square s) {
        int currentBalance = this.getMoney();
        int propertyValue = s.getPropertyValue();
        if (propertyValue <= currentBalance){
            s.setAvailable(false);
            s.setOwnedBy(this);
            this.debit(propertyValue);
            ownedProperties.add(s);
            return true;
        }
        System.out.println("You don't have any balance to buy this property");
        return false;
    }

    public int diceValue () {
        return (int)((Math.random() * 6) + 1);
    }

}
