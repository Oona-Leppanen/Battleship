package battleship;

public class Game {
    String player1;
    String player2;
    Board playerBoard1;
    Board playerBoard2;
    int sizeX;
    int sizeY;

    public Game (String player1, String player2, int sizeX, int sizeY) {
        playerBoard1 = new Board(sizeX, sizeY, Player.P1);
        playerBoard2 = new Board(sizeX, sizeY, Player.P2);
        this.player1=player1;
        this.player2=player2;
    }
}