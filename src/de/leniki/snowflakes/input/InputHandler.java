package de.leniki.snowflakes.input;

import de.leniki.snowflakes.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

	private Game game;

	private boolean[] keys = new boolean[Short.MAX_VALUE];
	public boolean up, left, right, catchKey;

	public InputHandler(Game game) {
		this.game = game;
	}

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_SPACE];
		left = (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]) && !right;
		right = (keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]) && !left;
		catchKey = keys[KeyEvent.VK_X];
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && game.start) game.start = false;
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}