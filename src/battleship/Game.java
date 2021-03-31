package battleship;

public class Game {
    String player1;
    String player2;
    Board playerBoard1;
    Board playerBoard2;
    int sizeX;
    int sizeY;
    public int destroyers;
    public int submarines;
    public int cruisers;
    public int battleships;
    public int carriers;
    boolean player1turn = true;
    boolean board1set = false;

    public Game (String player1, String player2, int sizeX, int sizeY) {
        playerBoard1 = new Board(sizeX, sizeY);
        playerBoard2 = new Board(sizeX, sizeY);
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.player1=player1;
        this.player2=player2;
    }
    
    public void setShips(int d, int s, int cr, int b, int c){
    	this.destroyers = d;
    	this.submarines = s;
    	this.cruisers = cr;
    	this.battleships = b;
    	this.carriers = c;
    }
}