package de.leniki.snowflakes.graphics;

import de.leniki.snowflakes.Game;

public class Screen {

	private int width, height;
	public int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void renderSprite(Sprite sprite, int xp, int yp, int flip) {
		for (int y = 0; y < sprite.getSize(); y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) ys = (sprite.getSize() - 1) - y;
			for (int x = 0; x < sprite.getSize(); x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) xs = (sprite.getSize() - 1) - x;
				if (xa < -sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) continue;
				int col = sprite.pixels[xs + ys * sprite.getSize()];
				if (col < 0) pixels[xa + ya * width] = col;
			}
		}
	}

	public void drawQuad(int color, int xp, int yp, int width, int height) {
		for (int y = 0; y < height; y++) {
			int ya = y + yp;
			for (int x = 0; x < width; x++) {
				int xa = x + xp;
				pixels[xa + ya * this.width] = color;
			}
		}
	}

	public void renderGround() {
		for (int i = 1; i < 6; i++) {
			renderSprite(Art.ground, i * Art.ground.getSize(), Game.HEIGHT - Art.ground.getSize(), 0);
		}

		renderSprite(Art.ground_left, 0, Game.HEIGHT - Art.ground_left.getSize(), 0);
		renderSprite(Art.ground_right, Game.WIDTH - Art.ground_right.getSize(), Game.HEIGHT - Art.ground_right.getSize(), 0);
	}

	public void renderBackground() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = 0xAAD5F0;
			}
		}
	}
}