# Monopoly
==================================

Important Classes:
Monopoly.java - This is the main class and it is responsible for drawing a new board and initializing Player 1 and Player 2.

Board.java - Board.java represents the physical monopoly board, which has 40 squares.

SquareType.java (abstract) - Represents every single square on the monopoly.
Contains the information about each square on the board, such as position, name, owner, color group, etc.
Implements the following subclasses:
1. Chance.java
2. FreeParking.java
3. Go.java
4. GoToJail.java
5. House.java --- This is in turn inherited by Hotel.java
6. Jail.java
7. Property.java
8. Railroad.java
9. Tax.java
10. Utilities.java.

Pieces.java
Interface which has the following methods: getName() and setOwner()

Player.java:
Implements Pieces.java and contains all the functionalities of the game. Some of the key methods include:
1. roll() - Roll the dice
2. playTurn() - Allows user to perform other actions, such as trading houses, mortgaging properties, etc.
3. handleSell() - Facilitates monetary transactions between players when selling properties to opponent or bank
4. mortgage() - Mortgages user's properties
5. addHouses() - Adds houses at user's request + verifies user owns color group + automatically adds hotel if 4 or more houses
6. trade() - Allows users to buy and sell to each other.
7. unmortgageProperty() - Unmortgages a mortgaged property.
8. chance() - Randomly selects a chance card and performs the given action.
9. movePlayer() - (Overloaded method) and moves the user's position on the physical Monopoly Board
10. payTax() - Facilitates tax payments for the user.
11. buyOrRentProperty()/buyOrRentUtilities() - Allows users to buy or rent a property/utility.
12. rent() - Withdraws money from opponent if landed on an owned property.
13. ownsColorGroup() - Verifies if user owns a certain color group for adding hotels/houses.
14. buy() - Enables users to buy a certain property.
15. raiseFunds() - Helps a user raise funds if they need to pay tax, rent, or want to buy a property but cannot afford.
16. displayHouses()/displayProperties() - Prints out the user's list of properties/houses
17. ownsHouse() - Checks that user owns the house
18. diceValue() - Generates a random dice value from 1 to 6, inclusive.
19. quit() - Quits the game




==================================

Game Rules
Welcome to Monopoly! The game involves two players competing to make the other player bankrupt first; one is player A
and the other is player B. Player A goes first. Each player starts with $1200. Each player is given 6 options in their turn:
1. Roll - rolls a dice allowing the player to move forward; player allowed to buy a property if they land on one or performs
another command based on the square (i.e. draws chance card, goes to jail, pays tax, etc.).

2. Add Houses - enables users to add Houses to any of their properties

3. Trade With Opponent: Allows users to buy or sell properties to their opponent.

4. Mortgage A Property: Allows a user to mortgage a property to the bank. No rent can be collected on a mortgaged property.

5. Unmortgage A Property: Allows a user to unmortgage a mortgaged property. The mortgage price must be paid plus 10%.

6. Quit the game: User quits the game and loses.

After each turn, a summary of the amount of money a player has and the land they own is printed as well. The player is also
moved to their new position.

For chance cards, there are 7 possibilities:
1. Get $200
2. Pay $200
3. Go To Jail
4. Go To 'Go'
5. Move 3 spaces forward
6. Do Nothing
7. Draw Another Chance Card

Jail: If a player goes to jail, either by drawing a chance card to go to jail, or by landing on the square go to jail,
they remain there until they roll a double.

Go: Once a player passes Go, they earn $200.

Buying Properties: If a player would like to buy a property from their opponent, both sides must agree to the transaction
for a certain property. The program will check if the buyer has the necessary funds to purchase the property. All owned
properties, regardless of type, can be sold.

Selling Properties: If a player would like to sell a property, they are given the option of either selling it directly
to the bank or selling it to their opponent. If they would like to sell to their opponent, both sides must agree to the
transaction for a certain property. The program checks if the buyer has the necessary funds to purchase the property. All owned
properties, regardless of type, can be sold.

Raising Funds: In the case that the user is unable to buy a property, pay rent, pay taxes, etc., they are given the option
to raise funds to try and complete the purchase. Funds can be raised through mortgaging a property or selling it to the bank
or the opponent. If the player will still not be able to purchase the property, the following takes place:

1) If the player was trying to buy a property but could not afford it, nothing happens and the turn is given to the
opponent.
2) If the player lands on a tax, or has to pay rent to the opponent and cannot afford it, then the game is lost.

Houses/Hotels: House and hotels can be added to owned properties, but not railroads or utilities, to increase the
rent earned from a given property. Each house cause the rent to increase by 25% and a hotel causes the rent to
increase by 50%. In order to create a house, the house price of the property must be paid. Once 4 houses are made on
a certain property, it automatically transforms into a hotel, increasing the rent by 50%. If a property with a house/
hotel is sold, then the opponent does not get to keep the houses/hotel and simply pays the original property value
for the given property.

Properties are grouped together, with properties with higher positions tending to have greater property values and rents.
Good luck!