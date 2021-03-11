package battleship;


public class Ship {
	int size;
	int hits = 0;
	boolean alive = true;
	boolean vertical = true;

	public Ship (int size, boolean vertical) {
        this.size = size;
        this.vertical = vertical;
    }
	
	int getSize() {
		return this.size;
	}

	boolean isAlive() {
		if (hits == size) {
			this.alive = false;
			return this.alive;
		}
		return this.alive;
	}

	void gotHit() {
		this.hits += 1;
	}
}

