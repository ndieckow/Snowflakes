package de.leniki.snowflakes.entity;

import de.leniki.snowflakes.Game;

import java.awt.*;

public class CollisionBox {

	private Entity entity;
	private int x, y, width, height;

	public CollisionBox(Entity entity, int x, int y, int width, int height) {
		this.entity = entity;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(getX1() * Game.SCALE, getY1() * Game.SCALE, width * Game.SCALE, height * Game.SCALE);
	}

	public Entity getEntity() {
		return entity;
	}

	public int getX1() {
		return entity.getX() + x;
	}

	public int getY1() {
		return entity.getY() + y;
	}

	public int getX2() {
		return getX1() + width;
	}

	public int getY2() {
		return getY1() + height;
	}
}