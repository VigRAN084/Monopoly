public class Monopoly {

    private Player p1;
    private Player p2;
    Board board = Board.newBoard;
    public static double INITIAL_MONEY = 1200;

    public void playGame() {
        addPlayers();
        Player currPlayer = p1;
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

    private void addPlayers() {
        Square s = board.getSquare(1);

        p1 = new Player();
        p1.setMoney(INITIAL_MONEY);
        p1.setName("A");
        p1.setSquare(s);
        p1.setMyTurn(true);
        p1.setBoard(this.board);

        p2 = new Player();
        p2.setMoney(INITIAL_MONEY);
        p2.setName("B");
        p2.setSquare(s);
        p2.setBoard(this.board);

        p1.setOpponent(p2);
        p2.setOpponent(p1);
    }

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

    public static void main(String[] args){
        Monopoly m = new Monopoly();
        m.playGame();
    }

}
