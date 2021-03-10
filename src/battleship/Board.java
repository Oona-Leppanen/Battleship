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
	
	//Need to add a counter variable here to manage when to return the boolean
	public boolean willFit(Ship s, int x, int y) {
		//Vertical check
		if (s.vertical) {
			//Anywhere in the middle
			if ((s.size+y < boardSizeY) && ((x != 0) && (x != boardSizeX))) {
				for (int i=x-1; i < x+1; i++) {
					for (int j=y+1; j < y-s.size-1; j--) {
						if (pelilauta[i][j] == 0) {
							//Fix return
							return true;
						}
					}
				}
			}
			//Leftmost edge
			if ((s.size+y < boardSizeY) && (x == 0)) {
				for (int i=x; i < x+1; i++) {
					for (int j=y+1; j < y-s.size-1; j--) {
						if (pelilauta[i][j] == 0) {
							//Fix return
							return true;
						}
					}
				}

			}
			//Rightmost edge
			if ((s.size+y < boardSizeY) && (x == boardSizeX)) {
				for (int i=x; i < x+1; i++) {
					for (int j=y+1; j < y-s.size-1; j--) {
						if (pelilauta[i][j] == 0) {
							//Fix return
							return true;
						}
					}
				}

			}
		}
		
		//Horizontal check
		if (!s.vertical) {
			//Anywhere in the middle
			if ((s.size+x < boardSizeX) && ((y != 0) && (y != boardSizeY))) {
				//Y-axis
				for (int i=y-1; i < y+1; i++) {
					//X-axis
					for (int j=x-1; j < x+s.size+1; j++) {
						if (pelilauta[i][j] == 0) {
							//Fix return
							return true;
						}
					}
				}
			}
			//Check if the ship lies on the bottom edge
			if ((s.size+x < boardSizeX) && (y == 0)) {
				for (int i=y; i < y+1; i++) {
					for (int j=x-1; j < x+s.size+1; j++) {
						if (pelilauta[i][j] == 0) {
							//Fix return
							return true;
						}
					}
				}
			}
			//Check if the ship lies on the top edge
			if ((s.size+x < boardSizeX) && (y == boardSizeY)) {
				for (int i=y; y < y-1; y--) {
					for (int j=x-1; j < x+s.size+1; j++) {
						if (pelilauta[i][j] == 0) {	
							//Fix return
							return true;
						}
					}
				}
			}
		}
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
