package battleship;

/* 
 * Ship class handles the ship logic on the logic side of the program. 
 * It has attributes for size, hits, verticality and coordinates.
 */

public class Ship {
	int size;
	int hits = 0;
	boolean alive = true;
	boolean vertical;
	int coordX;
	int coordY;

	public Ship (int size, boolean vertical) {
		this.size = size;
		this.vertical = vertical;
	}

/*
 * Checks if the ship is still alive or if it has been destroyed. Returns true if ship is alive.
 */
	
	boolean isAlive() {
		if (hits == size) {
			alive = false;
			return alive;
		}
		return alive;
	}

	void coordinates(int x, int y) {
		this.coordX =  x;
		this.coordY =  y;
	}

/*
 * Registers a hit to the ship
 */
	
	void gotHit() {
		this.hits += 1;
	}

/*
 * Returns the ships' coordinates from the board. Order defines which coordinate of the ship is given.
 * This information is used in other classes to loop through every coordinate the ship.
 */
	
	public int[] onBoard(int order) {
		if (vertical) {
			int [] coordinates = new int[2];
			coordinates[0] = coordX;
			coordinates[1] = coordY+order;
			return coordinates;
		}
		else {
			int [] coordinates = new int[2];
			coordinates[0] = coordX+order;
			coordinates[1] = coordY;
			return coordinates;
		}
	}
}
