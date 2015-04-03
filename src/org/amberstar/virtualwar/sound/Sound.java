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
package org.amberstar.virtualwar.sound;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

// TODO: Auto-generated Javadoc
/**
 * The Class Sound.
 * @author amberStar
 */
public class Sound {

	/** The format. */
	private AudioFormat format;

	/** The samples. */
	private byte[] samples;

	/** The time running. */
	private long timeRunning = -1;

	/**
     * Instantiates a new sound.
     *
     * @param filename
     *            the filename
     */
	public Sound(String filename) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(
					filename));
			format = stream.getFormat();
			samples = getSamples(stream);
		} catch (Exception e) {
		}
	}

	/**
     * Instantiates a new sound.
     *
     * @param filename
     *            the filename
     * @param timeRunning
     *            the time running
     */
	public Sound(String filename, long timeRunning) {
		this.timeRunning = timeRunning;
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(
					filename));
			format = stream.getFormat();
			samples = getSamples(stream);
		} catch (Exception e) {
		}
	}

	/**
     * Gets the samples.
     *
     * @return the samples
     */
	public byte[] getSamples() {
		return samples;
	}

	/**
     * Gets the samples.
     *
     * @param stream
     *            the stream
     * @return the samples
     */
	public byte[] getSamples(AudioInputStream stream) {
		int length = (int) (stream.getFrameLength() * format.getFrameSize());
		byte[] samples = new byte[length];
		DataInputStream in = new DataInputStream(stream);
		try {
			in.readFully(samples);
		} catch (Exception e) {
		}
		return samples;
	}

	/**
     * Play.
     *
     * @param source
     *            the source
     */
	public void play(InputStream source) {
		int bufferSize = format.getFrameSize()
				* Math.round(format.getSampleRate() / 10);
		byte[] buffer = new byte[bufferSize];
		SourceDataLine line;
		try {
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format, bufferSize);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			return;
		}
		Long startTime = System.currentTimeMillis();
		line.start();
		try {
			int numBytesRead = 0;
			while (numBytesRead != -1) {
				numBytesRead = source.read(buffer, 0, buffer.length);
				if (numBytesRead != -1) {
					line.write(buffer, 0, numBytesRead);
				} else if (timeRunning > 0) {
					numBytesRead = 0;
					source.reset();
				}
				if (timeRunning > 0
						&& System.currentTimeMillis() - startTime > timeRunning) {
					line.drain();
					line.close();
					return;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		line.drain();
		line.close();
	}
}