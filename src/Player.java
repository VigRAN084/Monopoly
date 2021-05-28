import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math.*;
public class Player implements Pieces{
    public static String[] PLAY_OPTIONS = {
            "1: Roll",
            "2: Buy Houses or Hotels",
            "3: Trade with another Player",
            "4: Unmortgage",
            "5: Quit Playing Game"
    };
    public static String[] chanceCards = {"Get $200", "Pay $200", "Go To Jail", "Go To 'Go'",
                                            "Move 3 spaces forward", "Do Nothing", "Draw Another Chance Card"};

    private String name;
    private double money;
    private boolean myTurn = false;
    private Square square = null;
    private String owner;
    private ArrayList<Square> ownedProperties = new ArrayList<>();
    private boolean hasLostGame = false;
    private Player opponent;
    private Board board;
    private boolean inJail = false;

    public Player() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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

    public boolean hasLostGame() {
        return hasLostGame;
    }

    public void setHasLostGame(boolean hasLostGame) {
        this.hasLostGame = hasLostGame;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    private void credit(double m) {
        this.money += m;
    }

    private void debit(double m) {
        this.money -= m;
    }

    private boolean hasFunds(double m) {
        return this.money > m;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    private void raiseFunds(double amount) {
        double totalFunds = totalPropertyValue() + this.money;
        if (totalFunds < amount) {
            System.out.println("Unable to raise funds since your total property worth + current funds " +
                    "is less than the amount needed");
            this.hasLostGame = true;
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to mortgage or sell any one of your properties? (Y/n): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("Y")){
            System.out.println("Would you like to mortgage or sell? Select '1' for mortgage " +
                    "and '2' for sell");
            int userResponse = scanner.nextInt();
            if (userResponse == 1) {
                mortgage();
            } else {
                sell();
            }
        } else {
            this.hasLostGame = true;
        }
    }

    public int totalPropertyValue() {
        int count = 0;
        for (Square s : this.ownedProperties) {
            count += s.getPropertyValue();
        }
        return count;
    }

    private void sell() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Which property would you like to sell? Enter the index: ");
        for (int i = 0; i < this.ownedProperties.size(); i++) {
            System.out.println(i + ". " + this.ownedProperties.get(i).getName());
        }
        int input = scanner.nextInt();
        System.out.println("Would you like to sell the property to the bank or the opponent? (1: opponent; 2: bank");
        int buyer = scanner.nextInt();
        Square property = this.ownedProperties.get(input);
        int propertyValue = property.getPropertyValue();

        boolean defaultingToBank = false;
        if (buyer == 1) {
            System.out.println(this.opponent.getName() + ", would you like to buy this property? (Y/n)");
            Scanner sc = new Scanner(System.in);
            String opponentResponse = sc.nextLine();
            if (opponentResponse.equalsIgnoreCase("Y")) {
                boolean hasFunds = this.opponent.hasFunds(propertyValue);
                if (!hasFunds) {
                    defaultingToBank = true;
                }
                else {
                    this.opponent.buy(property);
                    this.ownedProperties.remove(input);
                    if (property.isMortgaged()) {
                        credit(propertyValue - property.getMortgageValue());
                    } else {
                        credit(propertyValue);
                    }
                }
            } else {
                System.out.println(this.name + ", you cannot sell the property to your opponent. Choosing bank as the default");
                defaultingToBank = true;
            }
        }
        if (buyer == 2 || defaultingToBank) {
            if (property.isMortgaged()) {
                credit(propertyValue - property.getMortgageValue());
            } else {
                credit(propertyValue);
            }
            property.setAvailable(true);
            property.setOwnedBy(null);
            this.ownedProperties.remove(input);
        }

    }

    private void mortgage() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Which property would you like to mortgage? Enter the index: ");
        for (int i = 0; i < this.ownedProperties.size(); i++) {
            System.out.println(i + ". " + this.ownedProperties.get(i).getName());
        }
        int input = scanner.nextInt();
        Square square = this.ownedProperties.get(input);
        int mortgageValue = square.getMortgageValue();
        square.setMortgaged(true);
        this.credit(mortgageValue);
    }

    public void playTurn() {
        if (!this.isMyTurn()) return;
        System.out.println("Player " + name + "'s turn");
        int option = promptOption();
        if (option == 1){
            roll();
        }
        else if (option == 5) {
            if (quitGame()) {
                return;
            } else {
                playTurn();
            }
        }
        System.out.println("Player " + this.getName() + " summary:");
        System.out.println("\nMoney: $" + this.getMoney());
        System.out.println("Jail Cards Owned: ");
        System.out.println("Land Owned: " + this.ownedProperties);
        System.out.println();
    }

    private boolean quitGame() {
        Scanner sc = new Scanner (System.in);
        System.out.println("Are you sure you would like to quit the game? (Y/n)");
        String response = sc.nextLine();
        if (response.equalsIgnoreCase("Y")) {
            this.setHasLostGame(true);
            return true;
        } else {
            System.out.println("The game will resume");
            return false;
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

    private void roll () {
        int dice1 = diceValue();
        int dice2 = diceValue();
        int sum = dice1 + dice2;
        boolean isDouble = (dice1 == dice2);
        System.out.println("Rolling ... dice1: " + dice1 + " , dice2: " + dice2);
        if (this.inJail) {
            if (!isDouble) {
                System.out.println("You are currently in jail. You need to roll " +
                        "a double to exit.");
                return;
            } else {
                System.out.println("Congratulations! You just rolled a double and exited jail.");
                this.setInJail(false);
            }
        }
        movePlayer(sum);
        System.out.println("Moving to Position: " + this.square.getPosition() + " Name: "  + this.square.getName());
        if (this.square.getType().equals(Square.TYPE_PROPERTY)) {
            buyOrRentProperty();
        } else if (this.square.getType().equals(Square.TYPE_TAX)) {
            payTax();
        } else if (this.square.getType().equals(Square.TYPE_GOTOJAIL)) {
            movePlayer(11, false);
            setInJail(true);
        } else if (this.square.getType().equals(Square.TYPE_JAIL)) {
            //@TODO
        } else if (this.square.getType().equals(Square.TYPE_UTILITIES)) {
            buyOrRentUtilities(sum);
        }  else if (this.square.getType().equals(Square.TYPE_CardDraw)) {
            chance();
        } else if (this.square.getType().equals(Square.TYPE_FREEPARKING)) {
            //do nothing
        } else if (this.square.getType().equals(Square.TYPE_GO)) {
            //do nothing - crediting player $200 is already done in movePlayer method
        }
    }

    //public static String[] chanceCards = {"Get $200", "Pay $200", "Go To Jail", "Go To 'Go'",
    //                                            "Move 3 spaces forward", "Do Nothing", "Draw Another Chance Card"};
    //-*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*)(*
    public void chance() {
        System.out.println("You have landed on a chance card. Please enter a random letter to draw a chance card");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int chanceNum = (int)(Math.random()*chanceCards.length);
        System.out.println("Your draw was: " + chanceCards[chanceNum]);
        if (chanceNum == 0) {
            this.credit(200);
        } else if (chanceNum == 1) {
            this.debit(200);
        } else if (chanceNum == 2) {
            movePlayer(11, false);
            setInJail(true);
        } else if (chanceNum == 3) {
            this.movePlayer(41);
        } else if (chanceNum == 4) {
            this.movePlayer(3, true);
        } else if (chanceNum == 5) {
            //do nothing
        } else {
            chance();
        }
    }

    private void movePlayer(int pos) {
        movePlayer(pos, true);
    }

    private void movePlayer(int pos, boolean isOffset) {
        int newPosition = -1;
        Square currSquare = this.square;
        if (isOffset) {
            int currPosition = currSquare.getPosition();
            newPosition = currPosition + pos;
        } else {
            newPosition = pos;
        }
        if (newPosition > 40) {
            newPosition -= 40;
            System.out.println("Congratulations!!!!!!!! You just passed go and were awarded $200!");
            this.credit(200);
        }
        currSquare.removePlayerOnSpace(this);
        Square newSquare = this.board.getSquare(newPosition);
        setSquare(newSquare);
    }



    public void payTax() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("You have landed on a tax square. You can either pay 10 % of your net worth or $200. " +
                "Enter 'Y' for paying 10% and 'n' for $200. If you don't have enough for the 200 ");
        String inputForTax = scanner.nextLine();
        double tax = 0;
        if (inputForTax.equalsIgnoreCase("Y")) {
            double currBalance = this.getMoney();
            tax = 0.1 * currBalance;
        }
        else {
            tax = 200;
        }
        boolean haveMoney = hasFunds(tax);
        if (!haveMoney) {
            System.out.println("You don't have any balance to pay tax");
            raiseFunds(tax);
        }
        haveMoney = hasFunds(tax);
        if (haveMoney)
            this.debit(tax);
        else {
            System.out.println("You don't have any balance to pay tax");
            this.setHasLostGame(true);
        }
    }

    public void buyOrRentProperty() {
        if(this.square.isAvailable()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to buy this property for $" + this.square.getPropertyValue() + " (Y/n)");
            String decisionToBuy = scanner.nextLine();
            if (decisionToBuy.equalsIgnoreCase("Y")) {
                buy(this.square);
            }
        } else if (!this.square.isAvailable() && !this.square.isMortgaged()){
            Player owner = this.square.getOwnedBy();
            System.out.println("You owe " + owner.getName() + " " + this.square.getRent());
            rent(this.square, -1);
        }
    }

    public void buyOrRentUtilities(int diceVal) {
        if(this.square.isAvailable()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to buy this utility for $" + this.square.getPropertyValue() + " (Y/n)");
            String decisionToBuy = scanner.nextLine();
            if (decisionToBuy.equalsIgnoreCase("Y")) {
                buy(this.square);
            }
        } else if (!this.square.isAvailable() && !this.square.isMortgaged()){
            Player owner = this.square.getOwnedBy();
            rent( this.square, diceVal);
        }
    }

    public void rent(Square s,int diceVal) {
        Player owner = s.getOwnedBy();
        double rent = 0;
        if (this.square.getType().equals(Square.TYPE_UTILITIES)) {
            rent = 4*diceVal;
        } else {
            rent = s.getRent();
        }
        System.out.println("You owe " + owner.getName() + " " + rent);
        boolean haveMoney = hasFunds(rent);
        if (!haveMoney) {
            System.out.println("You don't have any balance to pay rent");
            raiseFunds(rent);
        }
        haveMoney = hasFunds(rent);
        if (hasFunds(rent)) {
            this.debit(rent);
            owner.credit(rent);
        } else {
            System.out.println("You don't have enough balance to pay the rent");
            this.setHasLostGame(true);
        }
    }

    public void buy(Square s) {
        double propertyValue = s.getPropertyValue();
        boolean haveMoney = hasFunds(propertyValue);
        if (!haveMoney){
            System.out.println("You don't have any balance to buy this property");
            raiseFunds(propertyValue);
        }
        haveMoney = hasFunds(propertyValue);
        if(haveMoney) {
            s.setAvailable(false);
            s.setOwnedBy(this);
            this.debit(propertyValue);
            ownedProperties.add(s);
        } else {
            System.out.println("You don't have any balance to buy this property");
            this.setHasLostGame(true);
        }
    }

    private int diceValue () {
        return (int)((Math.random() * 6) + 1);
    }

    private boolean ownsProperties() {
        return this.ownedProperties.size() > 0;
    }

}
