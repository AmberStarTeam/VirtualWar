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
package org.amberstar.virtualwar;

// TODO: Auto-generated Javadoc
/**
 * The TextData Enum.
 */
public enum TextData {

    /** The tank name. */
    TANK_NAME("tank"),

    /** The scavenger name. */
    SCAVENGER_NAME("piegeur"),

    /** The shooter name. */
    SHOOTER_NAME("tireur"),

    /** The error move is obstacle. */
    ERROR_MOVE_IS_OBSTACLE("D�placement imposible : il y a un obstacle !"),

    /** The error move is occupied. */
    ERROR_MOVE_IS_OCCUPED(
            "D�placement imposible : il y a d�j� un robot pr�sent !"),

    /** The error move is mine. */
    ERROR_MOVE_IS_MINE(
            "D�placement imposible : il y a une mine de votre �quipe !"),

    /** The error move is out of bound. */
    ERROR_MOVE_IS_OUT_OF_BOUND(
            "D�placement imposible : ce n'est pas sur le plateau !"),

    /** The error move is base other. */
    ERROR_MOVE_IS_BASE_OTHER(
            "D�placement imposible : ce n'est pas votre base !"),

    /** The error move is not valid. */
    ERROR_MOVE_IS_NOT_VALID(
            "D�placement imposible : ce n'est pas un d�placement possible pour cette unit�e !"),

    /** The error board too small. */
    ERROR_BOARD_TOO_SMALL("Cr�ation impossible : le plateau est trop petit !"),

    /** The error board too big. */
    ERROR_BOARD_TOO_BIG("Cr�ation impossible : le plateau est trop grand !"),

    /** The error board must be positive. */
    ERROR_BOARD_MUST_BE_POSITIF(
            "Cr�ation impossible : les valeurs doivent �tre positives");

    /** The name. */
    private String nameFr = "";

    // Constructeur
    /**
     * Instantiates a new text data.
     *
     * @param nameFr
     *            the name
     */
    TextData(String nameFr) {
        this.nameFr = nameFr;
    }

    /**.
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     * @return the value of field
     */
    public String toString() {
        return nameFr;
    }
}
