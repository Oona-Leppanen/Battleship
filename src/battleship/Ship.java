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
}
