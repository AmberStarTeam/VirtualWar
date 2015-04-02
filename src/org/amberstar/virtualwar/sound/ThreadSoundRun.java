package org.amberstar.virtualwar.sound;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ThreadSoundRun extends Thread {
	private Son sound;
	private InputStream is;

	public ThreadSoundRun(String file) {
		sound = new Son(file);
		is = new ByteArrayInputStream(sound.getSamples());
	}

	public ThreadSoundRun(String file, long timeRunning) {
		sound = new Son(file, timeRunning);
		is = new ByteArrayInputStream(sound.getSamples());
	}

	public ThreadSoundRun(Son sound, InputStream is) {
		this.sound = sound;
		this.is = is;
	}

	public void run() {
		sound.play(is);
	}
}
