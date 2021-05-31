# Monopoly
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