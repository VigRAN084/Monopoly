public class Monopoly {

    private Player p1;
    private Player p2;

    public void playGame() {
        Board b = Board.newBoard;
        addPlayers();
        boolean gameNotWon = false;
        Player currPlayer = p1;
        while (!gameNotWon) {
            currPlayer.playTurn();
            currPlayer = togglePlayer();
        }
        b.printBoard();
    }

    private void addPlayers() {
        p1 = new Player();
        p1.setMoney(1500);
        p1.setPiece("A");
        p1.setSpaceCurrentlyOn(1);
        p1.setMyTurn(true);

        p2 = new Player();
        p2.setMoney(1500);
        p2.setPiece("B");
        p2.setSpaceCurrentlyOn(1);
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
