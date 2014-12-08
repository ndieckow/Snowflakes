package de.leniki.snowflakes.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	private AudioClip clip;

	public static Sound jump;

	public static void init() {
		jump = new Sound("/sound/jump.wav");
	}

	private Sound(String path) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(path));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		clip.play();
	}
}