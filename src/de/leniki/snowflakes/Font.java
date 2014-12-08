package de.leniki.snowflakes;

import de.leniki.snowflakes.graphics.Screen;
import de.leniki.snowflakes.graphics.Sprite;
import de.leniki.snowflakes.graphics.SpriteSheet;

public class Font {

	private static String characters = "" //
			+ "ABCDEFGHIJKLM" //
			+ "NOPQRSTUVWXYZ" //
			+ "abcdefghijklm" //
			+ "nopqrstuvwxyz" //
			+ "0123456789.,:" //
			+ ";!?()         ";
	private static Sprite[] sprites = SpriteSheet.font.split(16);

	public static void draw(String message, Screen screen, int x, int y) {
		int xOffset = 0;
		for (int i = 0; i < message.length(); i++) {
			char character = message.charAt(i);
			if (character == ' ') {
				xOffset += 10;
				continue;
			} else if (character == '\n') {
				y += 20;
				xOffset = 0;
			}
			int index = characters.indexOf(character);
			if (index == -1) continue;
			if (index >= 0) screen.renderSprite(sprites[index], x + xOffset, y, 0);
			xOffset += sprites[index].getLetterWidth() + 3;
		}
	}
}