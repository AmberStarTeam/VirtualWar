package org.amberstar.virtualwar;

/**
 * 
 * @author beaussan
 *
 */
public class Coordinates {
    /** the width coordinate */
    private int width;
    /** the height coordinate */
    private int height;

    /**
     * default constructor
     * 
     * @param height
     *            height coordinates
     * @param width
     *            width coordinates
     */
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

    /**
     * 
     * @param c
     *            coordinate to add
     * @return a new coordinate
     */
    public Coordinates add(Coordinates c) {
        return new Coordinates(height + c.height, width + c.width);
    }

    /**
     * 
     * @param c
     *            coordinate to minus
     * @return a new coordinate
     */
    public Coordinates minus(Coordinates c) {
        return new Coordinates(height - c.height, width - c.width);
    }
    
    

    /**
     * 
     * @param n
     *            the number to multiply by
     * @return a new coordinate
     */
    public Coordinates times(int n) {
        return new Coordinates(height * n, width * n);
    }

}
