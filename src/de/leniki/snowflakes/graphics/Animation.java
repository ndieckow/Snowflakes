package de.leniki.snowflakes.graphics;

public class Animation {

	public Sprite[] frames;
	public int currentFrame;
	private int delay; // 60 = 1 second
	private int timer;
	public boolean animating;
	private boolean repeating;

	public Animation(Sprite[] frames, int delay, boolean repeating) {
		this.frames = frames;
		this.delay = delay;
		this.repeating = repeating;
	}

	public Sprite getSprite() {
		return frames[currentFrame];
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void start() {
		currentFrame = 0;
		timer = 0;
		animating = true;
	}

	public void stop() {
		animating = false;
		if (currentFrame == frames.length) currentFrame -= 1;
		timer = 0;
	}

	public void update() {
		if (!animating || delay <= 0) return;
		timer++;
		if (timer >= delay) {
			timer = 0;
			currentFrame++;
			if (currentFrame == frames.length) {
				if (!repeating) stop();
				else currentFrame = 0;
			}
		}
	}
}