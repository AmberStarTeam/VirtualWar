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

public class Son {

	private AudioFormat format;

	private byte[] samples;

	private long timeRunning = -1;

	public Son(String filename) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(
					filename));
			format = stream.getFormat();
			samples = getSamples(stream);
		} catch (Exception e) {
		}
	}

	public Son(String filename, long timeRunning) {
		this.timeRunning = timeRunning;
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(
					filename));
			format = stream.getFormat();
			samples = getSamples(stream);
		} catch (Exception e) {
		}
	}

	public byte[] getSamples() {
		return samples;
	}

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