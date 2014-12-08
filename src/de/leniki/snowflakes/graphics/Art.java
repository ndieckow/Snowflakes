package de.leniki.snowflakes.graphics;

public class Art {

	public static Sprite character_walk1 = new Sprite(SpriteSheet.character, 0, 0, 64);
	public static Sprite character_walk2 = new Sprite(SpriteSheet.character, 1, 0, 64);
	public static Sprite character_walk3 = new Sprite(SpriteSheet.character, 2, 0, 64);
	public static Animation character_walk = new Animation(new Sprite[] { character_walk1, character_walk2, character_walk3 }, 2, true);

	public static Sprite character_jump1 = new Sprite(SpriteSheet.character, 0, 1, 64);
	public static Sprite character_jump2 = new Sprite(SpriteSheet.character, 1, 1, 64);

	public static Sprite box = new Sprite(SpriteSheet.character, 3, 1, 64);
	public static Sprite box_open = new Sprite(SpriteSheet.character, 4, 1, 64);

	public static Sprite ground = new Sprite(SpriteSheet.environment, 0, 0, 64);
	public static Sprite ground_right = new Sprite(SpriteSheet.environment, 1, 0, 64);
	public static Sprite ground_left = new Sprite(SpriteSheet.environment, 2, 0, 64);

	public static Sprite paper = new Sprite(SpriteSheet.environment, 0, 1, 128);

	public static Sprite snowflake1 = new Sprite(SpriteSheet.environment, 0, 2, 32);
	public static Sprite snowflake2 = new Sprite(SpriteSheet.environment, 1, 2, 32);
	public static Animation snowflake = new Animation(new Sprite[] { snowflake1, snowflake2 }, 60, true);

	public static Sprite particle_fall1 = new Sprite(SpriteSheet.particles, 0, 0, 32);
	public static Sprite particle_fall2 = new Sprite(SpriteSheet.particles, 1, 0, 32);
	public static Sprite particle_fall3 = new Sprite(SpriteSheet.particles, 2, 0, 32);
	public static Sprite particle_fall4 = new Sprite(SpriteSheet.particles, 3, 0, 32);
	public static Animation particle_fall = new Animation(new Sprite[] { particle_fall1, particle_fall2, particle_fall3, particle_fall4 }, 20, false);

	public static Sprite particle_kappa = new Sprite(SpriteSheet.particles, 0, 1, 32);

	public static Sprite energyBar = new Sprite(SpriteSheet.environment, 4, 1, 64);
	public static Sprite energyPart = new Sprite(SpriteSheet.environment, 3, 1, 64);
}