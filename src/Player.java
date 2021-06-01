import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player class
 */
public class Player implements Pieces{
    //different options the player can do in a turn
    public static String[] PLAY_OPTIONS = {
            "1: Roll",
            "2: Add Houses",
            "3: Trade with opponent",
            "4: Mortgage A Property",
            "5: Unmortgage",
            "6: Quit Playing Game"

    };


    private String name; //player name
    private double money; //amount of money player has
    private boolean myTurn = false; //true if it is the player's turn
    private SquareType squareType = null;
    private String owner;
    private ArrayList<SquareType> ownedProperties = new ArrayList<>(); //ArrayList of owned properties
    private boolean hasLostGame = false; //determines if player has lost the game
    private Player opponent; //player representing the opponent
    private Board board; //Game board
    private boolean inJail = false; //determines if the player is in jail

    //constructor
    public Player() {}

    //getters and setters
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

    public SquareType getSquareType() {
        return squareType;
    }

    public void setSquareType(SquareType squareType) {
        this.squareType = squareType;
        this.squareType.addPlayerOnSpace(this);
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


    /**
     * credits money to the player
     * @param m
     */
    private void credit(double m) {
        this.money += m;
    }

    /**
     * debits money from the player
     * @param m
     */
    private void debit(double m) {
        this.money -= m;
    }

    /**
     * determines if the player has sufficient funds
     * @param m
     * @return
     */
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


    /**
     * Play your turn
     */
    public void playTurn() {
        if (!this.isMyTurn()) return;
        System.out.println("Player " + name + "'s turn");
        int option = promptOption();
        if (option == 1){
            roll();
        }
        else if (option == 2) {
            addHouses();
        }
        else if (option == 3) {
            trade();
        }
        else if (option == 4) {
            mortgageProperty();
        }
        else if (option == 5) {
            unmortgageProperty();
        }
        else if (option == 6) {
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

    /**
     * Handle rolling of a dice
     */
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
        else {
            if (isDouble) {
                System.out.println("You rolled a double and earned a chance card!");
                chance();
            }
        }
        movePlayer(sum);
        System.out.println("Moving to Position: " + this.squareType.getPosition() + " Name: "  + this.squareType.getName());
        if (this.squareType.isProperty()) {
            buyOrRentProperty();
        } else if (this.squareType.isTax()) {
            payTax();
        } else if (this.squareType.isGoToJail()) {
            movePlayer(11, false);
            setInJail(true);
        } else if (this.squareType.isGo()) {
            //do nothing
        } else if (this.squareType.isUtilities()) {
            buyOrRentUtilities(sum);
        } else if (this.squareType.isChance()) {
            chance();
        } else if (this.squareType.isJail()) {
            //do nothing
        } else if (this.squareType.isFreeParking()) {
            //do nothing
        }
    }

    /**
     * Calculate the value of the player's properties
     * @return total property value
     */
    private int totalPropertyValue() {
        int count = 0;
        for (SquareType s : this.ownedProperties) {
            double value = 0;
            if (s.isProperty()) {
                value = ((Property)s).getPropertyValue();
            } else if (s.isUtilities()) {
                value = ((Utilities)s).getPropertyValue();
            }
            count += value;
        }
        return count;
    }

    /**
     * handleSell property to bank or opponent
     * @param sellToOpponent
     */
    private void handleSell(boolean sellToOpponent) {
        if (this.ownedProperties.size() == 0) {
            System.out.println(this.name + ", you don't have any properties to sell!");
            playTurn();
            return;
        }
        Scanner scanner = new Scanner (System.in);
        System.out.println(this.name + ", which property would you like to sell (-1 if don't want to sell)? Enter the index: ");
        displayProperties();
        int propertyIndex = scanner.nextInt();
        if (propertyIndex == -1) {
            System.out.println(this.name + " does not want to sell any properties!");
            playTurn();
            return;
        }

        int buyer = -1;
        if (sellToOpponent) {
            buyer = 1;
        } else {
            System.out.println("Would you like to sell the property to the bank or the opponent? (1: opponent; 2: bank)");
            buyer = scanner.nextInt();
        }

        SquareType property = this.ownedProperties.get(propertyIndex);
        double propertyValue = ((Property)property).getPropertyValue();

        boolean defaultBankAsBuyer = false;
        if (buyer == 1) {
            System.out.println(this.opponent.getName() + ", would you like to buy this property? (Y/n)");
            Scanner sc = new Scanner(System.in);
            String opponentResponse = sc.nextLine();
            if (opponentResponse.equalsIgnoreCase("Y")) {
                boolean hasFunds = this.opponent.hasFunds(propertyValue);
                if (!hasFunds) {
                    defaultBankAsBuyer = true;
                }
                else {
                    sellToOpponent( property, propertyValue,propertyIndex);
                }
            } else {
                System.out.println(this.name + ", you cannot sell the property to your opponent. Choosing bank as the default");
                defaultBankAsBuyer = true;
            }
        }
        if (buyer == 2 || defaultBankAsBuyer) {
            sellToBank(property, propertyValue, propertyIndex);
        }
    }
    /**
     * Sell property to bank
     * @param property
     * @param propertyValue
     * @param propertyIndex
     */
    private void sellToBank(SquareType property, double propertyValue, int propertyIndex) {
        if (property.isProperty() && ((Property)property).isMortgaged()) {
            credit(propertyValue - ((Property)property).getMortgageValue());
        } else {
            credit(propertyValue);
        }
        property.setAvailable(true);
        property.setOwnedBy(null);
        this.ownedProperties.remove(propertyIndex);
    }

    /**
     * sell property to opponent
     * @param property
     * @param propertyValue
     * @param propertyIndex
     */
    private void sellToOpponent( SquareType property, double propertyValue, int propertyIndex) {
        double mortgageValue = 0.0;
        if (property.isProperty()){
            propertyValue = ((Property) property).getPropertyValue();
            mortgageValue = ((Property) property).getMortgageValue();
        } else if (property.isUtilities()){
            propertyValue = ((Utilities) property).getPropertyValue();
        }
        this.opponent.buy(property, propertyValue);
        this.ownedProperties.remove(propertyIndex);
        if (property.isProperty() && ((Property)property).isMortgaged()) {
            credit(propertyValue - mortgageValue);
        } else {
            credit(propertyValue);
        }
    }

    /**
     * mortgage a property
     */
    private void mortgage() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Which property would you like to mortgage? Enter the index: ");
        displayProperties();
        int input = scanner.nextInt();
        SquareType squareType = this.ownedProperties.get(input);
        double mortgageValue = ((Property) squareType).getMortgageValue();
        ((Property)squareType).setMortgaged(true);
        this.credit(mortgageValue);
    }

    /**
     * add a house to a property
     */
    private void addHouses() {
        if (!ownsHouse()) {
            System.out.println("You do not have any properties!");
            playTurn();
        } else {
            displayHouses();
            System.out.println("Which property would you like to add a house to?");
            Scanner sc = new Scanner (System.in);
            int prop = sc.nextInt();
            House house = ((House)this.ownedProperties.get(prop));
            if (!ownsColorGroup(house)) {
                System.out.println("You do not own the color group, so you cannot add houses!");
                playTurn();
                return;
            }
            double housePrice = house.getHousePrice();
            boolean haveMoney = hasFunds(housePrice);
            if (!haveMoney) {
                System.out.println("You don't have enough balance to add a house");
                raiseFunds(housePrice);
            }
            if (haveMoney){
                this.debit(housePrice);
                house.addHouse();
                System.out.println(house.getName() + " now has " + house.getNumHouses() + " houses!");
                if (house.getNumHouses() >= 4) {
                    System.out.println("Upgrading to a hotel!!");
                    Hotel hotel = Hotel.upgradeHouseToHotel(house);
                    int position = house.getPosition();
                    getBoard().setSquare(hotel, position);
                    System.out.println("The current rent on the property is " + hotel.getRent());
                }
            } else {
                System.out.println("You don't have any balance to pay tax");
                playTurn();
            }
        }
    }

    /**
     * trade properties with the opponent
     */
    private void trade() {
        System.out.println(this.name + ", would you like to buy or sell properties? (1/2)");
        Scanner sc = new Scanner(System.in);
        int response = sc.nextInt();
        if (response == 1) this.opponent.handleSell(true);
        else this.handleSell(false);
    }

    /**
     * mortgage a property
     */
    private void mortgageProperty() {
        if (ownedProperties.size() <= 0) {
            System.out.println("You do not have any properties to mortgage!");
            playTurn();
        }
        else {
            displayProperties();
            System.out.println("Which property would you like to mortgage?");
            Scanner sc = new Scanner (System.in);
            int ownedPropertyNum = sc.nextInt();
            Property h = ((Property)this.ownedProperties.get(ownedPropertyNum));
            System.out.println(h.getName() + " will now be mortgaged. You are allowed to retain " +
                    "possession of the property, but no rent can be collected. To lift the mortgage, you must pay" +
                    " back the entirety of the mortgage value plus an additional 10%.");
            h.setMortgaged(true);
        }
    }

    /**
     * unmortgage a mortgaged property
     */
    private void unmortgageProperty() {
        int count = 0;
        ArrayList<SquareType> mortgagedProperties = new ArrayList<>();
        for (SquareType s: this.ownedProperties) {
            if (s.isProperty() && ((Property)s).isMortgaged()) {
                System.out.println(count + " - " + s.getName());
                mortgagedProperties.add(s);
                count++;
            }
        }
        if (mortgagedProperties.size() <= 0) {
            System.out.println("You do not have any properties to mortgage. Please choose another move.");
            playTurn();
            return;
        }
        Scanner sc = new Scanner (System.in);
        System.out.println("Above are your currently mortgaged properties. Which property would you like to unmortgage?");
        int response = sc.nextInt();
        Property property = (Property) mortgagedProperties.get(response);
        double payValue = property.getMortgageValue() * 1.1;
        if (this.hasFunds(payValue)) this.debit (payValue);
        else {
            System.out.println("Not enough funds!");
            playTurn();
        }
    }

    /**
     * prompt options for the user's turn
     * @return
     */
    private int promptOption() {
        System.out.println("Your options are: ");
        for (String s: PLAY_OPTIONS){
            System.out.println(s);
        }
        boolean validInput = false;
        int optionNum = -1;
        while(!validInput) {
            System.out.println("Please enter a number between 1 and 6, inclusive");
            Scanner scanner = new Scanner(System.in);
            optionNum = scanner.nextInt();
            if (optionNum >= 1 && optionNum <= 6){
                validInput = true;
            }
            else{
                System.out.println("Invalid input");
            }
        }
        return optionNum;
    }

    /**
     * handle chance cards
     */
    private void chance() {
        System.out.println("You have landed on a chance card. Please enter a random letter to draw a chance card");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int chanceNum = (int)(Math.random()*Chance.chanceCards.length);
        System.out.println("Your draw was: " + Chance.chanceCards[chanceNum]);
        if (chanceNum == 0) {
            this.credit(200);
        } else if (chanceNum == 1) {
            this.debit(200);
        } else if (chanceNum == 2) {
            movePlayer(11, false);
            setInJail(true);
        } else if (chanceNum == 3) {
            this.movePlayer(41, false);
        } else if (chanceNum == 4) {
            this.movePlayer(3);
        } else if (chanceNum == 5) {
            //do nothing
        } else {
            chance();
        }
    }

    /**
     * move player on the board, offset by pos
     * @param pos
     */
    private void movePlayer(int pos) {
        movePlayer(pos, true);
    }

    /**
     * move player on the board to a certain position
     * @param pos
     * @param isOffset
     */
    private void movePlayer(int pos, boolean isOffset) {
        int newPosition = -1;
        SquareType currSquareType = this.squareType;
        if (isOffset) {
            int currPosition = currSquareType.getPosition();
            newPosition = currPosition + pos;
        } else {
            newPosition = pos;
        }
        if (newPosition > 40) {
            newPosition -= 40;
            System.out.println("Congratulations!!!!!!!! You just passed go and were awarded $200!");
            this.credit(200);
        }
        currSquareType.removePlayerOnSpace(this);
        SquareType newSquareType = this.board.getSquare(newPosition);
        setSquareType(newSquareType);
    }

    /**
     * pay taxes
     */
    private void payTax() {
        Scanner scanner = new Scanner (System.in);
        System.out.println("You have landed on a tax squareType. You can either pay 10 % of your net worth or $200. " +
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

    /**
     * buy or rent a property when landing on it
     */
    private void buyOrRentProperty() {
        Property h = ((Property)this.squareType);
        if(h.isAvailable()) {
            System.out.println("You currently have $" + this.money);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to buy this property for $" + h.getPropertyValue() + " (Y/n)");
            String decisionToBuy = scanner.nextLine();
            if (decisionToBuy.equalsIgnoreCase("Y")) {
                buy(h, h.getPropertyValue());
            }
        } else if (!h.isAvailable() && !h.isMortgaged()){
            Player owner = h.getOwnedBy();
            System.out.println("You owe " + owner.getName() + " " + h.getRent());
            rent(h, h.getRent());
        }
    }

    /**
     * buy or rent a utility
     * @param diceVal
     */
    private void buyOrRentUtilities(int diceVal) {
        Utilities u = ((Utilities)this.squareType);
        if(u.isAvailable()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to buy this utility for $" + u.getPropertyValue() + " (Y/n)");
            String decisionToBuy = scanner.nextLine();
            if (decisionToBuy.equalsIgnoreCase("Y")) {
                buy(u, u.getPropertyValue());
            }
        } else if (!u.isAvailable()){
            rent( u, 4*diceVal);
        }
    }

    /**
     * pay rent to opponent
     * @param s
     * @param rent
     */
    private void rent(SquareType s, double rent) {
        Player owner = s.getOwnedBy();
        System.out.println("You owe " + owner.getName() + " " + rent);
        boolean haveMoney = hasFunds(rent);
        if (!haveMoney) {
            System.out.println("You don't have any balance to pay rent");
            raiseFunds(rent);
        }
        haveMoney = hasFunds(rent);
        if (haveMoney) {
            this.debit(rent);
            owner.credit(rent);
        } else {
            System.out.println("You don't have enough balance to pay the rent");
            this.setHasLostGame(true);
        }
    }

    private boolean ownsColorGroup(House house) {
        int position = house.getPosition();
        int[] colorRange = ColorGroup.getColorRange(house.getColorGroup());
        int startIndex = colorRange[0];
        int endIndex = colorRange[1];
        for (int i = startIndex; i <= endIndex; i++) {
            SquareType s = board.getSquare(i);
            if (!s.isProperty()) {
                continue;
            }
            if (s.getOwnedBy() == null || !(s.getOwnedBy().getName().equals(this.name))) {
                return false;
            }
        }
        return true;
    }

    /**
     * quit the game
     * @return
     */
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

    /**
     * buy a property
     * @param s
     * @param propertyValue
     */
    private void buy(SquareType s, double propertyValue) {
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

    /**
     * raise funds if user does not have enough money
     * @param amount
     */
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
                handleSell(true);
            }
        } else {
            this.hasLostGame = true;
        }
    }

    /**
     * display the current user's properties
     */
    private void displayProperties() {
        for (int i = 0; i < this.ownedProperties.size(); i++) {
            SquareType squareType = this.ownedProperties.get(i);
            if (squareType.isProperty()) {
                System.out.println(i + ". " + squareType.getName());
            }

        }
    }
    /**
     * display the current user's houses
     */
    private void displayHouses() {
        for (int i = 0; i < this.ownedProperties.size(); i++) {
            SquareType squareType = this.ownedProperties.get(i);
            if (squareType.isHouse()) {
                System.out.println(i + ". " + squareType.getName());
            }

        }
    }

    private boolean ownsHouse() {
        for (int i = 0; i < this.ownedProperties.size(); i++) {
            SquareType squareType = this.ownedProperties.get(i);
            if (squareType.isHouse()) {
                return true;
            }
        }
        return false;
    }

    /**
     * produce a random dice value
     * @return
     */
    private int diceValue () {
        return (int)((Math.random() * 6) + 1);
    }

}
