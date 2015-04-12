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
 *
 * @author amberStar
 */
public enum TextData {

	/** The label tank team a. */
	LABEL_TANK_TEAM_A("t"),

	/** The label tank team b. */
	LABEL_TANK_TEAM_B("T"),

	/** The label scavenger team a. */
	LABEL_SCAVENGER_TEAM_A("p"),

	/** The label scavenger team b. */
	LABEL_SCAVENGER_TEAM_B("P"),

	/** The label shooter team a. */
	LABEL_SHOOTER_TEAM_A("s"),

	/** The label shooter team b. */
	LABEL_SHOOTER_TEAM_B("S"),

	/** The label base team a. */
	LABEL_BASE_TEAM_A("b"),

	/** The label base team b. */
	LABEL_BASE_TEAM_B("B"),

	/** The label mine team a. */
	LABEL_MINE_TEAM_A("x"),

	/** The label mine team b. */
	LABEL_MINE_TEAM_B("X"),

	/** The label obstacle. */
	LABEL_OBSTACLE("O"),

	/** The label legende title. */
	LABEL_LEGENDE_TITLE("Légande"),

	/** The label legende base. */
	LABEL_LEGENDE_BASE(LABEL_BASE_TEAM_A + "\\" + LABEL_BASE_TEAM_B + " : base"),

	/** The label legende char. */
	LABEL_LEGENDE_CHAR(LABEL_TANK_TEAM_A + "\\" + LABEL_TANK_TEAM_B + " : char"),

	/** The label legende shooter. */
	LABEL_LEGENDE_SHOOTER(LABEL_SHOOTER_TEAM_A + "\\" + LABEL_SHOOTER_TEAM_B
			+ " : tireur"),

	/** The label legende scavenger. */
	LABEL_LEGENDE_SCAVENGER(LABEL_SCAVENGER_TEAM_A + "\\"
			+ LABEL_SCAVENGER_TEAM_B + " : piegeur"),

	/** The label legende mine. */
	LABEL_LEGENDE_MINE(LABEL_MINE_TEAM_A + "\\" + LABEL_MINE_TEAM_B + " : mine"),

	/** The label legende obstacle. */
	LABEL_LEGENDE_OBSTACLE(LABEL_OBSTACLE + " : obstacle"),

	/** The label legende energy. */
	LABEL_LEGENDE_ENERGY("E : energie"),

	/** The label legende nmb mine. */
	LABEL_LEGENDE_NMB_MINE("M : nbr de mines"),

	/** The label legende help. */
	LABEL_LEGENDE_HELP("h - regles"),

	/** The label legende quit. */
	LABEL_LEGENDE_QUIT("q - quitter"),

	/** The tank name. */
	TANK_NAME("tank"),

	/** The scavenger name. */
	SCAVENGER_NAME("piegeur"),

	/** The shooter name. */
	SHOOTER_NAME("tireur"),

	/** The error move is obstacle. */
	ERROR_MOVE_IS_OBSTACLE("Déplacement imposible : il y a un obstacle !"),

	/** The error move is occupied. */
	ERROR_MOVE_IS_OCCUPED(
			"Déplacement imposible : il y a déjà un robot présent !"),

	/** The error move is mine. */
	ERROR_MOVE_IS_MINE(
			"Déplacement imposible : il y a une mine de votre équipe !"),

	/** The error move is out of bound. */
	ERROR_MOVE_IS_OUT_OF_BOUND(
			"Déplacement imposible : ce n'est pas sur le plateau !"),

	/** The error move is base other. */
	ERROR_MOVE_IS_BASE_OTHER(
			"Déplacement imposible : ce n'est pas votre base !"),

	/** The error move is not valid. */
	ERROR_MOVE_IS_NOT_VALID(
			"Déplacement imposible : ce n'est pas un déplacement possible pour cette unitée !"),

	/** The error board too small. */
	ERROR_BOARD_TOO_SMALL("Création impossible : le plateau est trop petit !"),

	/** The error board too big. */
	ERROR_BOARD_TOO_BIG("Création impossible : le plateau est trop grand !"),

	/** The error board must be positive. */
	ERROR_BOARD_MUST_BE_POSITIF(
			"Création impossible : les valeurs doivent être positives"),

	UP("haut"), DOWN("bas"), RIGHT("droite"), LEFT("gauche"), DIAG_UP_RIGHT(
			"diagonale haut droite"), DIAG_UP_LEFT("diagonale haut gauche"), DIAG_DOWN_RIGHT(
			"diagonale bas droite"), DIAG_DOWN_LEFT("diagonale bas gauche");

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

	/**
	 * . (non-Javadoc)
	 *
	 * @return the value of field
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return nameFr;
	}
}
