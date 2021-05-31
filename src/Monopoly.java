//Vignesh Rangarajan
//Period 1
//5/30/2021
//Monopoly
public class Monopoly {

    private Player p1;
    private Player p2;
    Board board = Board.newBoard;
    public static double INITIAL_MONEY = 1200; //initial money the players start with

    /**
     * Play game method
     */
    public void playGame() {
        addPlayers();
        Player currPlayer = p1;
        /**
         * While loop for playing the game until a player loses
         */
        while (true) {
            currPlayer.playTurn();
            if (currPlayer.hasLostGame()) {
                System.out.println(currPlayer.getName() + " has lost the game");
                break;
            }
            currPlayer = togglePlayer();
            board.printBoard();
            System.out.println();
            System.out.println("=====================");
            System.out.println();
        }
    }

    /**
     * Add players to the game
     */
    private void addPlayers() {
        SquareType s = board.getSquare(1);

        p1 = new Player();
        p1.setMoney(INITIAL_MONEY);
        p1.setName("A");
        p1.setSquareType(s);
        p1.setMyTurn(true);
        p1.setBoard(this.board);

        p2 = new Player();
        p2.setMoney(INITIAL_MONEY);
        p2.setName("B");
        p2.setSquareType(s);
        p2.setBoard(this.board);

        p1.setOpponent(p2);
        p2.setOpponent(p1);
    }

    /**
     * Switch turns in the game
     * @return
     */
    private Player togglePlayer() {
        if (p1.isMyTurn()){
            p1.setMyTurn(false);
            p2.setMyTurn(true);
            return p2;
        } else {
            p1.setMyTurn(true);
            p2.setMyTurn(false);
            return p1;
        }
    }

    /**
     * main method, which plays the game
     * @param args
     */
    public static void main(String[] args){
        Monopoly m = new Monopoly();
        m.playGame();
    }

}
