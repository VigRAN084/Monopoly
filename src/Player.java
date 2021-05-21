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
    private double money;
    private boolean myTurn = false;
    private Square square = null;
    private String owner;
    private ArrayList<Square> ownedProperties = new ArrayList<>();
    private boolean hasLostGame = false;
    private Player opponent;

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
                    this.opponent.buyProperty(property);
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

    private void roll () {
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
            payTax();
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
                buyProperty(this.square);
            }
        } else if (!this.square.isAvailable() && !this.square.isMortgaged()){
            Player owner = this.square.getOwnedBy();
            System.out.println("You owe " + owner.getName() + " " + this.square.getRent());
            rentProperty(this.square);
        }
    }

    public void rentProperty(Square s) {
        Player owner = this.square.getOwnedBy();
        double rent = this.square.getRent();
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

    public void buyProperty(Square s) {
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
