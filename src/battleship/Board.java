package battleship;

import java.util.ArrayList;

public class Board {
	int boardSizeX;
	int boardSizeY;
	int[][] pelilauta;
	ArrayList<Ship> shipsOnBoard = new ArrayList<Ship>();
	Player p;

	public Board(int boardSizeX, int boardSizeY, Player p) {
		this.boardSizeX = boardSizeX;
		this.boardSizeY = boardSizeY;
		pelilauta = new int[boardSizeX][boardSizeY];
		for (int i=0; i<boardSizeX; i++) {
			for (int j=0; j<boardSizeY; j++) {
				pelilauta[i][j] = 0;
			}
		}
		p = this.p;
	}

	public boolean hasAShip(int x, int y) {
		if (pelilauta[x][y] == 1) {
			return true;
		}
		return false;
	}
	
	public void deleteAShip(Ship s) {
		if (!s.isAlive()) {
			if (s.vertical) {
				for (int i=s.coordX; i < s.coordX+s.size; i++) {
					for (int j=s.coordY; j < s.coordY+s.size; j++) {
						pelilauta[i][j] = 0;
					}
				}
			}
			if (!s.vertical) {
				for (int i=s.coordY; i < s.coordY+s.size; i++) {
					for (int j=s.coordX; j < s.coordX+s.size; j++) {
						pelilauta[j][i] = 0;
					}
				}
			}
		}
	}
	
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
//						System.out.println(i + " and " + j);
						if (pelilauta[i][j] == 1) {
							System.out.println("ei muuten mahdu");
							return false;
						}
					}
				}
			} else {
			System.out.println("ei mahdu laudalle");
			return false;
			}
		}
		if (!s.vertical) {
			if (s.size+x <= boardSizeX) {
				for (int i=y-1; i <= y+1; i++) {
					if ( (i<0) || (i >= boardSizeY) ) {
						//						System.out.println("i = " + i);
						continue;
					}
					for (int j=x-1; j < x+s.size+1; j++) {
						if ( (j < 0) || (j >= boardSizeX) ) {
							//							System.out.println("j = " + j);
							continue;
						}
//						System.out.println(j + " and " + i);
						if (pelilauta[j][i] == 1) {
							System.out.println("ei muuten mahdu");
							return false;
						}
					}
				}
			} else {
			System.out.println("ei mahdu laudalle");
			return false;
			}
		}

		System.out.println("Kyllä mahtuu");
		return true;
	}

	public void setAShip(Ship s, int x, int y) {
		if (willFit(s,x,y)) {
			s.coordinates(x, y);
			if (s.vertical) {
				for (int i=0; i<s.size; i++) {
					pelilauta[x][y+i] = 1;
					shipsOnBoard.add(s);
				}
			} else {
				for (int i=0; i<s.size; i++) {
					pelilauta[x+i][y] = 1;
					shipsOnBoard.add(s);
				}
			}
			//Will have to adjust to GUI, placeholder print line
		} else {
			System.out.println("Ei muuten mahdu");
		}
	}
	public void clearBoard() {
        for (int i=0; i<boardSizeX; i++) {
            for (int j=0; j<boardSizeY; j++) {
            	pelilauta[i][j] = 0;
            	shipsOnBoard.removeAll(shipsOnBoard);
            }
        }
    }
	public boolean lost(Board b) {
		for (int i = 0; i < b.boardSizeX; i++) {
			for (int j=0; j < b.boardSizeY; j++) {
				b.pelilauta[i][j] = 1;
				System.out.println("Et hävinnyt");
				return false;
			}
		}
		System.out.println("Hävisit pelin");
		return true;
	}
}