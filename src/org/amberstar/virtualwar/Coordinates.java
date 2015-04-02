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
    public Coordinates(int width, int height) {
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
        return "Coordinates [height=" + height + ", width=" + width + "]";
    }

    /**
     * 
     * @param c
     *            coordinate to add
     * @return a new coordinate
     */
    public Coordinates add(Coordinates c) {
        return new Coordinates(width + c.width, height + c.height);
    }

    /**
     * 
     * @param c
     *            coordinate to minus
     * @return a new coordinate
     */
    public Coordinates minus(Coordinates c) {
        return new Coordinates(width - c.width, height - c.height);
    }

    /**
     * 
     * @param n
     *            the number to multiply by
     * @return a new coordinate
     */
    public Coordinates times(int n) {
        return new Coordinates(width * n, height * n);
    }

    /**
     * 
     * @param n
     *            the number to divide by
     * @return a new coordinate
     */
    public Coordinates divide(int n) {
        return new Coordinates(width / n, height / n);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + width;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Coordinates other = (Coordinates) obj;
        if (height != other.height) {
            return false;
        }
        if (width != other.width) {
            return false;
        }
        return true;
    }

}
