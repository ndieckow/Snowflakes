package de.leniki.snowflakes.entity.particle;

import de.leniki.snowflakes.Game;
import de.leniki.snowflakes.entity.Entity;
import de.leniki.snowflakes.graphics.Animation;
import de.leniki.snowflakes.graphics.Screen;
import de.leniki.snowflakes.graphics.Sprite;

import java.awt.*;

public abstract class Particle extends Entity {

	protected Sprite sprite;
	protected Animation animation;

	public Particle(Game game, int x, int y) {
		super(game, x, y);
	}

	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y, 0);
	}

	public Rectangle getBounds() {
		return null;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Animation getAnimation() {
		return animation;
	}
}