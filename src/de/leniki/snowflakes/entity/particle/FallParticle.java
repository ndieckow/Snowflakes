package de.leniki.snowflakes.entity.particle;

import de.leniki.snowflakes.Game;
import de.leniki.snowflakes.graphics.Art;

public class FallParticle extends Particle {

	private int xAdd, yAdd;

	public FallParticle(Game game, int x, int y, int xAdd, int yAdd) {
		super(game, x, y);
		this.xAdd = xAdd;
		this.yAdd = yAdd;
		animation = Art.particle_fall;
		animation.start();
	}

	public void update() {
		animation.update();
		sprite = animation.getSprite();

		y += yAdd;
		x += xAdd;
	}
}