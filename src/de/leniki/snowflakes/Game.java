package de.leniki.snowflakes;

import de.leniki.snowflakes.entity.Entity;
import de.leniki.snowflakes.entity.Player;
import de.leniki.snowflakes.entity.Snowflake;
import de.leniki.snowflakes.entity.particle.Particle;
import de.leniki.snowflakes.graphics.Screen;
import de.leniki.snowflakes.input.InputHandler;
import de.leniki.snowflakes.sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final int SCALE = 2;
	private final String TITLE;

	private Thread thread;
	private boolean running;
	private JFrame frame;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	public boolean start;

	private Screen screen;
	private InputHandler input;
	private Player player;
	private List<Entity> entities = new ArrayList<Entity>();
	private long nextSnowflakeTime;
	private long lastSnowflake = System.currentTimeMillis();

	private int ticks;

	public Game(String title) {
		Sound.init();

		TITLE = title;
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		screen = new Screen(WIDTH, HEIGHT);
		input = new InputHandler(this);
		addKeyListener(input);
		setFocusable(true);
		player = new Player(this, 200, 100);

		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		start();
		start = true;
	}

	public InputHandler getInput() {
		return input;
	}

	public void addEntity(Entity ent) {
		entities.add(ent);
	}

	public void removeEntity(Entity ent) {
		entities.remove(ent);
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		long lastTimer = System.currentTimeMillis();
		int ticks = 0;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				ticks++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
	}

	public void update() {
		ticks++;
		if (ticks >= 6000) ticks = 0;
		if (start) return;
		input.update();
		player.update();
		if (System.currentTimeMillis() - lastSnowflake > nextSnowflakeTime) {
			Snowflake sf = new Snowflake(this);
			addEntity(sf);
			lastSnowflake = System.currentTimeMillis();
			nextSnowflakeTime = 500;
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();

			if (entities.get(i) instanceof Snowflake) {
				Snowflake snowflake = (Snowflake) entities.get(i);

				if (snowflake.getBounds().intersects(player.getBounds()) && player.isCatching()) {
					removeEntity(snowflake);
					player.increaseSnowflakeCount(1);
				}

				if (snowflake.getY() > HEIGHT) {
					removeEntity(snowflake);
				}
			}

			if (entities.get(i) instanceof Particle) {
				Particle particle = ((Particle) entities.get(i));
				if (particle.getAnimation() != null && !particle.getAnimation().animating) removeEntity(particle);
			}
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		if (start) {
			screen.renderBackground();
			Font.draw("Controls:\n\nLeft, Right: Walk\nUp or Space: Jump\nX: Snowflake Box\n", screen, 50, 50);
			if (ticks % 60 > 30 && ticks % 60 < 60) Font.draw("Press ENTER to start!", screen, 130, 220);
		} else {
			screen.renderBackground();
			screen.renderGround();
			player.render(screen);
			for (Entity entity : entities) {
				entity.render(screen);
			}
			Font.draw("Score: " + player.getSnowflakeCount(), screen, 5, 5);
		}

		System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
}