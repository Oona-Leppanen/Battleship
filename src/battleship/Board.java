package battleship;

import java.util.ArrayList;

public class Board {
	int boardSizeX;
	int boardSizeY;
	ArrayList<Integer> BoardX;
	ArrayList<Integer> BoardY;
	int shipsOnBoard;
	public Board(ArrayList<Integer> BoardX, ArrayList<Integer> BoardY, int boardSizeX, int boardSizeY) {
		for (int i = 0; i < boardSizeX; i++) {
			BoardX.add(i);
		}
		for (int j = 0; j < boardSizeY; j++) {
			BoardY.add(j);
		}
	}
	
	public boolean legalSize() {
		if (boardSizeX * boardSizeY > shipsOnBoard) {
			return true;
		}
		return false;
	}
	
	public boolean hasAShip(Ship s, int x, int y) {
		if (BoardX.get(x) == s.getX(x) || BoardY.get(y) == s.getY(y)) {
			return true;
		}
		return false;
	}
}
