package positioning;

public class Hitbox {
	
	private float x, y;
	
	private int width, height;
	
	private float x2, y2;
	
	public Hitbox(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.x2 = x + width;
		this.y2 = y + height;
	}
	
	
	public void shiftHitbox(int x, int y) {
		this.x += x;
		this.y += y;
		this.x2 += x;
		this.y2 += y;
	}
	
	public boolean intersectsWith(Hitbox hitbox) {
		if(hitbox.getX() < x2 && hitbox.getX2() > x) {
			if(hitbox.getY() < y2 && hitbox.getY2() > y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isOnPoint(int x, int y) {
		if(this.x < x && this.x2 > x) {
			if(this.y < y && this.y2 > y) {
				return true;
			}
		}
		return false;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public float getPreciseX() {
		return x;
	}

	public int getY() {
		return (int) y;
	}
	
	public float getPreciseY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX2() {
		return (int) x2;
	}

	public float getPreciseX2() {
		return x2;
	}

	public int getY2() {
		return (int) y2;
	}

	public float getPreciseY2() {
		return y2;
	}
	
}
