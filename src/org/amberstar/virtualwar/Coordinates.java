package org.amberstar.virtualwar;

public class Coordinates {
	private int width;
	private int height;

	public Coordinates(int height, int width) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordinates [width=" + width + ", height=" + height + "]";
	}

	public Coordinates add(Coordinates c) {
		return new Coordinates(height + c.height, width + c.width);
	}
	
	public Coordinates times(int n){
		return new Coordinates(height*n, width*n);
	}

}
