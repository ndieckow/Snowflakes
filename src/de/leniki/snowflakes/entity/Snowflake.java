package de.leniki.snowflakes.entity;

import de.leniki.snowflakes.Game;
import de.leniki.snowflakes.graphics.Art;
import de.leniki.snowflakes.graphics.Screen;
import de.leniki.snowflakes.graphics.Sprite;

import java.awt.*;

public class Snowflake extends Entity {

	private Sprite sprite;
	private int fall = (int) (1 + Math.random() * 2);
	private boolean pos = r.nextBoolean();

	public Snowflake(Game game) {
		super(game, (int) (Math.random() * Game.WIDTH), -64);
		if (getBounds().getMinX() / Game.SCALE < 0) {
			x = 0;
		} else if (getBounds().getMaxX() / Game.SCALE > Game.WIDTH) {
			x = (int) (Game.WIDTH - getBounds().getX());
		}

		sprite = Art.snowflake1;
	}

	public void update() {
		y += fall;

		if (pos) x += 1;
		else x -= 1;

		if (getBounds().getMinX() / Game.SCALE <= 0 || getBounds().getMaxX() / Game.SCALE >= Game.WIDTH) pos = !pos;
	}

	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((x + 8) * Game.SCALE, (y + 8) * Game.SCALE, (32 - 16) * Game.SCALE, (32 - 16) * Game.SCALE);
	}
}