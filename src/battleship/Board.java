package battleship;

import java.util.ArrayList;

/*
 * Board class is used for handling the actual game logic. The game is operated on an integer matrix. Ships on the matrix are 
 * marked with a 1, empty spots are marked with 0. This also uses the parameters of given board size to create a right sized
 * matrix. Two ArrayLists are also assigned as class attributes to track ships that are on the board and ships that have been
 * sunk.
 */

public class Board {
	int boardSizeX;
	int boardSizeY;
	int[][] pelilauta;
	ArrayList<Ship> shipsOnBoard = new ArrayList<Ship>();
	ArrayList<Ship> sunkShips = new ArrayList<Ship>();

	public Board(int boardSizeX, int boardSizeY) {
		this.boardSizeX = boardSizeX;
		this.boardSizeY = boardSizeY;
		pelilauta = new int[boardSizeX][boardSizeY];
		for (int i=0; i<boardSizeX; i++) {
			for (int j=0; j<boardSizeY; j++) {
				pelilauta[i][j] = 0;
			}
		}
	}

/*
 * Checks if the current coordinate has a ship.
 */
	
	public boolean hasAShip(int x, int y) {
		if (pelilauta[x][y] == 1) {
			return true;
		}
		return false;
	}

/*
 * Sinks a ship that is given as a parameter and marks a sinked ship as 4 on the matrix for later GUI usage.
 * Also handles the coordinates depending on if the ship is vertical or not.
 */
	
	public void sinkAShip(Ship s) {
		if (!s.isAlive()) {
			if (s.vertical) {
					for (int j=s.coordY; j < s.coordY+s.size; j++) {
						pelilauta[s.coordX][j] = 4;
					}
			}
			if (!s.vertical) {
					for (int j=s.coordX; j < s.coordX+s.size; j++) {
						pelilauta[j][s.coordY] = 4;
					}
			}
		}
	}

/*
 * willFit() -method is an important checker method for the functions of the game logic. It gets parameters of a ship and x and y
 * coordinates.	It will treat the x and y coordinates as the starting square of the ship and compare it through the board to see
 * if it will fit according to the rules of Battleship.
 */
	
	public boolean willFit(Ship s, int x, int y) {
		if (s.vertical) {
			if (s.size+y <= boardSizeY) {
				for (int i=x-1; i <= x+1; i++) {
					if ( (i < 0) || (i >= boardSizeX) ) {
						continue;
					}
					for (int j=y-1; j < y+s.size+1; j++) {
						if ( (j < 0 ) || (j >= boardSizeY) ) {
							continue;
						}
						if (pelilauta[i][j] == 1) {
							return false;
						}
					}
				}
			} else {
				return false;
			}
		}
		if (!s.vertical) {
			if (s.size+x <= boardSizeX) {
				for (int i=y-1; i <= y+1; i++) {
					if ( (i<0) || (i >= boardSizeY) ) {
						continue;
					}
					for (int j=x-1; j < x+s.size+1; j++) {
						if ( (j < 0) || (j >= boardSizeX) ) {
							continue;
						}
						if (pelilauta[j][i] == 1) {
							return false;
						}
					}
				}
			} else {
				return false;
			}
		}

		return true;
	}

/*
 * setAShip() sets a ship s given in parameters to the coordinates x and y that are also given. Sets the coordinates to 1 on the matrix 
 * and adds the ship s to the shipsOnBoard ArrayList.
 */
	
	public void setAShip(Ship s, int x, int y) {
			s.coordinates(x, y);
			if (s.vertical) {
				for (int i=0; i<s.size; i++) {
					pelilauta[x][y+i] = 1;
				}
				shipsOnBoard.add(s);
			} else {
				for (int i=0; i<s.size; i++) {
					pelilauta[x+i][y] = 1;
				}
				shipsOnBoard.add(s);
			}
	}
	
/*
 * Clears the matrix of everything setting everything to 0. Also empties the ArrayList shipsOnBoard.	
 */
	
	public void clearBoard() {
		for (int i=0; i<boardSizeX; i++) {
			for (int j=0; j<boardSizeY; j++) {
				pelilauta[i][j] = 0;
				shipsOnBoard.removeAll(shipsOnBoard);
			}
		}
	}
	
/*
 * Checks the shipsOnBoard list to see if it is empty. Returns true if it still has ships and the game continues, false if the list is
 * empty and the game is over.	
 */
	
	public boolean lost() {
        if (shipsOnBoard.size() == 0) {
        return true;
        }
        return false;
    }
}