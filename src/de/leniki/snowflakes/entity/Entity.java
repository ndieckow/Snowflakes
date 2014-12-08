package de.leniki.snowflakes.entity;

import de.leniki.snowflakes.Game;
import de.leniki.snowflakes.graphics.Screen;

import java.awt.*;
import java.util.Random;

public abstract class Entity {

	protected final Random r = new Random();
	protected Game game;
	protected int x, y;

	public Entity(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}

	public abstract void update();
	public abstract void render(Screen screen);

	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}