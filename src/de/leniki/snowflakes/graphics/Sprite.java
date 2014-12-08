package de.leniki.snowflakes.graphics;

public class Sprite {

	private SpriteSheet sheet;
	private int x, y;
	private int size;
	public int[] pixels;

	protected Sprite() {
	}

	public Sprite(SpriteSheet sheet, int x, int y, int size) {
		this.sheet = sheet;
		this.x = x * size;
		this.y = y * size;
		this.size = size;
		pixels = new int[size * size];

		cut();
	}

	public Sprite(int[] pixels, int size) {
		this.pixels = pixels;
		this.size = size;
	}

	public void cut() {
		for (int y = 0; y < size; y++) {
			int ya = this.y + y;
			for (int x = 0; x < size; x++) {
				int xa = this.x + x;
				pixels[x + y * size] = sheet.pixels[xa + ya * sheet.getWidth()];
			}
		}
	}

	public int getLetterWidth() {
		int c = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int col = pixels[x + y * size];
				if (!(col < 0)) c++;
			}
			if (c == size) return x;
			c = 0;
		}
		return size;
	}

	public SpriteSheet getSheet() {
		return sheet;
	}

	public int getSize() {
		return size;
	}
}