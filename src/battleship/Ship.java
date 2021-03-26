package battleship;


public class Ship {
	int size;
	int hits = 0;
	boolean alive = true;
	boolean vertical = true;
	int coordX;
	int coordY;

	public Ship (int size, boolean vertical) {
        this.size = size;
        this.vertical = vertical;
    }

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

	void gotHit() {
		this.hits += 1;
	}
	
	public int[] onBoard(int order) {
		if (vertical) {
			int [] coordinates = new int[2];
				coordinates[0] = coordX;
				coordinates[1] = coordY+order;
			return coordinates;
		}
		if (!vertical) {
			int [] coordinates = new int[2];
				coordinates[0] = coordX+order;
				coordinates[1] = coordY;
			return coordinates;
		}
		int[] bootleg = {1,2,3,4,5};
		return bootleg;
	}
}
