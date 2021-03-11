package battleship;


public class Board {
	int boardSizeX;
	int boardSizeY;
	int[][] pelilauta;
	int shipsOnBoard;
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

	public boolean legalSize() {
		//Have to configure to adapt if both players have their own Board object (Divide by 2)
		if (boardSizeX * boardSizeY > shipsOnBoard) {
			return true;
		}
		return false;
	}

	public boolean hasAShip(int x, int y) {
		if (pelilauta[x][y] == 1) {
			return true;
		}
		return false;
	}

	public boolean willFit(Ship s, int x, int y) {
		if (s.vertical) {
			if (s.size+y < boardSizeY) {
				for (int i=x-1; i <= x+1; i++) {
					if ( (i < 0) || (i > boardSizeX) ) {
						continue;
					}
					for (int j=y-1; j < y+s.size+1; j++) {
						if ( (j < 0 ) || (j > boardSizeY) ) {
							continue;
						}
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
			if (s.size+x < boardSizeX) {
				for (int i=y-1; i <= y+1; i++) {
					if ( (i<0) || (i > boardSizeY) ) {
						//						System.out.println("i = " + i);
						continue;
					}
					for (int j=x-1; j < x+s.size+1; j++) {
						if ( (j < 0) || (j > boardSizeX) ) {
							//							System.out.println("j = " + j);
							continue;
						}
						//						System.out.println("(" + j +"," + i + ")");
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
			if (s.vertical) {
				for (int i=0; i<s.size; i++) {
					pelilauta[x][y+1] = 1;
				}
			} else {
				for (int i=0; i<s.size; i++) {
					pelilauta[x+i][y] = 1;
				}
			}
			//Will have to adjust to GUI, placeholder print line
		} else {
			System.out.println("Ei muuten mahdu");
		}
	}
}
