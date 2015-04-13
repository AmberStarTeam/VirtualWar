package org.virtualwar.util.sound;

/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class ThreadSoundRun.
 * 
 * @author amberstar
 */
public class ThreadSoundRun extends Thread {

	/** The sound. */
	private Sound sound;

	/** The is. */
	private InputStream is;

	/**
	 * Instantiates a new thread sound run.
	 *
	 * @param file
	 *            the file
	 */
	public ThreadSoundRun(String file) {
		if (file == null) {
			return;
		}
		sound = new Sound(file);
		is = new ByteArrayInputStream(sound.getSamples());
	}

	/**
	 * Instantiates a new thread sound run.
	 *
	 * @param file
	 *            the file
	 * @param timeRunning
	 *            the time running
	 */
	public ThreadSoundRun(String file, long timeRunning) {
		if (file == null) {
			return;
		}
		sound = new Sound(file, timeRunning);
		is = new ByteArrayInputStream(sound.getSamples());
	}

	/**
	 * Instantiates a new thread sound run.
	 *
	 * @param sound
	 *            the sound
	 * @param is
	 *            the is
	 */
	public ThreadSoundRun(Sound sound, InputStream is) {
		if (sound == null) {
			return;
		}
		this.sound = sound;
		this.is = is;
	}

	/**
	 * . (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		if (sound == null) {
			return;
		}
		sound.play(is);
	}
}
