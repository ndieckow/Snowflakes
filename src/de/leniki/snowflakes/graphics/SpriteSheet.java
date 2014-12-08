package de.leniki.snowflakes.graphics;

import de.leniki.snowflakes.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

	private int width, height;
	public int[] pixels;

	public static SpriteSheet character = new SpriteSheet("/graphics/char.png");
	public static SpriteSheet environment = new SpriteSheet("/graphics/environment.png");
	public static SpriteSheet particles = new SpriteSheet("/graphics/particles.png");

	public static SpriteSheet font = new SpriteSheet("/font/font.png");

	public SpriteSheet(String path) {
		load(path);
	}

	private void load(String path) {
		try {
			BufferedImage image = ImageIO.read(Main.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];

			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite[] split(int size) {
		Sprite[] res = new Sprite[(width * height) / (size * size)];

		int index = 0;
		for (int y = 0; y < height; y += size) {
			for (int x = 0; x < width; x += size) {
				int[] pixels = new int[size * size];
				for (int yy = 0; yy < size; yy++) {
					int yo = y + yy;
					for (int xx = 0; xx < size; xx++) {
						int xo = x + xx;
						pixels[xx + yy * size] = this.pixels[xo + yo * width];
					}
				}
				res[index++] = new Sprite(pixels, size);
			}
		}
		return res;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}