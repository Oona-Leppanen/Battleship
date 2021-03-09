package battleship;


public class Board {
	int boardSizeX;
	int boardSizeY;
	int[][] pelilauta;
	int shipsOnBoard;
	Player p;

	public Board(int boardSizeX, int boardSizeY, Player p) {
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
	//willFit(3PitkäHorisonttiLaiva, 3, 4)
	public boolean willFit(Ship s, int x, int y) {
		//Horizontal check
		if (!s.vertical) {
			//Anywhere in the middle
			if ((s.size+x < boardSizeX) && (x != 0)) {
				//X-axis
				for (int i=x-1; i < x+1; i++) {
					//Y-axis
					for (int j=y-1; j < y+1; j++) {
						if (pelilauta[i][y] == 0) {
							return true;
						}
					}
				}
			}
		}
		//Check if the ship lies on the leftmost edge
		if (x == 0) {
			for (int i=x; i < x+1; i++) {
				for (int j=y-1; j < y+1; j++) {
					if (pelilauta[i][y] == 0) {
						return true;
					}
				}
			}
		}
		if ((s.size+x < boardSizeX) && (x == boardSizeX)) {
			//for (int i=x; i < x-1;)
		}
//		if ((!s.vertical && s.size+x > boardSizeX) || (s.vertical &&s.size+y > boardSizeY)) {
//			
//			return false;
//		}
		return false;
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
			System.out.println("Ei muuten mahdu");
		}
	}

}
