package de.leniki.snowflakes.entity;

import de.leniki.snowflakes.Game;
import de.leniki.snowflakes.entity.particle.FallParticle;
import de.leniki.snowflakes.graphics.Animation;
import de.leniki.snowflakes.graphics.Art;
import de.leniki.snowflakes.graphics.Screen;
import de.leniki.snowflakes.graphics.Sprite;
import de.leniki.snowflakes.obj.EnergyBar;
import de.leniki.snowflakes.sound.Sound;

import java.awt.*;

public class Player extends Entity {

	public Rectangle hitbox = new Rectangle(x, y, 35, 23);
	private int direction; /* Right = 0; Left = 1 */
	private double speed = 2;
	private Sprite sprite;
	private Animation walking;

	private final double gravity = 1000;
	private double yVelocity;
	private final int terrainHeight = 220;

	private int jumpTimes;
	private boolean jumpKeyPressed;
	private boolean canJump;

	private boolean catching;
	private boolean catchKeyPressed;
	private long lastCatch;

	private int snowflakeCount;
	public EnergyBar energyBar;
	public double energy = 200;
	public final double MAX_ENERGY = 200;

	public Player(Game game, int x, int y) {
		super(game, x, y);
		walking = Art.character_walk;
		walking.start();
		sprite = walking.getSprite();
		lastCatch = System.currentTimeMillis();
		energyBar = new EnergyBar(this);
	}

	public boolean isCatching() {
		return catching;
	}

	public int getDirection() {
		return direction;
	}

	public double getEnergy() {
		return energy;
	}

	public double getMaxEnergy() {
		return MAX_ENERGY;
	}

	private void jump() {
		Sound.jump.play();
		yVelocity = 400;
		setSprite(Art.character_jump2);

		if (jumpTimes == 1) {
			game.addEntity(new FallParticle(game, x + 30, y + 40, 1, 1));
			game.addEntity(new FallParticle(game, x + 15, y + 45, 0, 1));
			game.addEntity(new FallParticle(game, x, y + 40, -1, 1));
		}

		jumpTimes++;
	}

	private void startCatching() {
		if (energy <= 0) return;
		catching = true;
		lastCatch = System.currentTimeMillis();
	}

	private void stopCatching() {
		catching = false;
	}

	public void increaseSnowflakeCount(int add) {
		snowflakeCount += add;
	}

	public int getSnowflakeCount() {
		return snowflakeCount;
	}

	public void update() {
		energyBar.update();

		canJump = jumpTimes < 2;

		yVelocity -= gravity * (1.0 / 60.0);
		y -= yVelocity * (1.0 / 60.0);

		if (y > terrainHeight) {
			setSprite(walking, false);
			yVelocity = 0;
			y = terrainHeight;
			jumpKeyPressed = false;
			if (jumpTimes != 0) {
				jumpTimes = 0;
				game.addEntity(new FallParticle(game, x + 30, y + 40, 1, -1));
				game.addEntity(new FallParticle(game, x, y + 40, -1, -1));
			}
		}

		if (game.getInput().up) {
			if (canJump && !jumpKeyPressed) {
				jump();
			}
			jumpKeyPressed = true;
		} else {
			jumpKeyPressed = false;
		}

		if (game.getInput().left) {
			if (jumpTimes < 1) setSprite(walking, true);

			direction = 1;
			if (jumpTimes > 0) {
				x -= speed / 2.0;
			} else {
				x -= speed;
			}
		} else if (game.getInput().right) {
			if (jumpTimes < 1) setSprite(walking, true);

			direction = 0;
			if (jumpTimes > 0) {
				x += speed / 2.0;
			} else {
				x += speed;
			}
		} else {
			walking.animating = false;
			walking.currentFrame = 0;
		}

		walking.update();

		if (game.getInput().catchKey) {
			if (!catchKeyPressed) {
				if (!catching) startCatching();
				else stopCatching();
			}
			catchKeyPressed = true;
		} else {
			catchKeyPressed = false;
		}

		if (System.currentTimeMillis() - lastCatch > 3000) {
			stopCatching();
		}

		if (isCatching()) {
			energy--;
			if (energy <= 0) stopCatching();
		} else {
			if (energy < MAX_ENERGY) energy++;
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y, direction);
		if (catching) {
			screen.renderSprite(Art.box, x, y, direction);
			screen.renderSprite(Art.box_open, x, y, direction);
		}
		energyBar.render(screen);
	}

	public Rectangle getBounds() {
		if (direction == 1) return new Rectangle((x + 48) * Game.SCALE, (y) * Game.SCALE, (15) * Game.SCALE, (8) * Game.SCALE);
		else return new Rectangle((x) * Game.SCALE, (y) * Game.SCALE, (15) * Game.SCALE, (8) * Game.SCALE);
	}

	private void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	private void setSprite(Animation sprite, boolean start) {
		this.sprite = sprite.getSprite();
		if (start) sprite.animating = true;
	}
}