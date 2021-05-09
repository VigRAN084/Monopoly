public class Monopoly {

    private Player p1;
    private Player p2;
    Board board = Board.newBoard;

    public void playGame() {
        addPlayers();
        boolean gameNotWon = false;
        Player currPlayer = p1;
        while (!gameNotWon) {
            currPlayer.playTurn();
            currPlayer = togglePlayer();
            board.printBoard();
        }
    }

    private void addPlayers() {
        Square s = board.getSquare(1);

        p1 = new Player();
        p1.setMoney(1500);
        p1.setName("A");
        p1.setSquare(s);
        p1.setMyTurn(true);

        p2 = new Player();
        p2.setMoney(1500);
        p2.setName("B");
        p2.setSquare(s);
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
