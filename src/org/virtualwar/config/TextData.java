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
package org.virtualwar.config;

// TODO: Auto-generated Javadoc
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

// TODO: Auto-generated Javadoc
/**
 * The TextData Enum.
 *
 * @author amberstar
 */
public enum TextData {

	/** The label regle. */
	LABEL_REGLE(
			"\tR�gles du jeu :\n"
					+ "Les pays s'affrontent en faisent combattre les robots sur un plateau.\n\n"
					+ "Le jeu se d�roule au tour par tour."
					+ " L'�quipe qui commence est choisie al�atoirement.\n\n"
					+ "Les robot commencent dans leur base respective.\n\n"
					+ "A chaque tour, chaque �quipe choisit"
					+ " un de ses robots pour r�aliser une de ses actions.\n\n"
					+ "Une �quipe ne peut pas passer son tour.\n"
					+ "Elle doit jouer tant que ses robots ont"
					+ " assez d'�nergie pour r�aliser une action.\n\n"
					+ "Au cours de la partie chaque �quipe doit"
					+ " conserver au moins un robot hors de sa base.\n\n"
					+ "La partie se termine d�s que l'un des deux"
					+ " parties ne poss�de plus de robot vivant.\n\n"
					+ "\t Comment jouer ? : \n\n"
					+ "A) Les robots : \n\n"
					+ "Tireur : Robot l�ger, capable de tirer sur une courte distance."
					+ " Peut se d�placer dans tout les sens.\n\n"
					+ "Pi�geur : Poseur de mine. Peut se d�placer dans tous les sens.\n\n"
					+ "Char : Robot lourd, capable de tirer sur de longues distanfces."
					+ " Ne peut pas ce d�placer en diagonale.\n\n\n"
					+ "B) Bases et energie : \n\n"
					+ "Chaque action co�te de l'�nergie."
					+ "Quand un robot n'a plus d'�nergie, il ne peut plus jouer.\n\n"
					+ "Quand il retrouve dans sa base, un robot r�cup�re peu � peu son �nergie."),

	/** The label up. */
	LABEL_UP("haut"),

	/** The label down. */
	LABEL_DOWN("bas"),

	/** The label right. */
	LABEL_RIGHT("droite"),

	/** The label left. */
	LABEL_LEFT("gauche"),

	/** The label up right. */
	LABEL_UP_RIGHT(LABEL_UP + " � " + LABEL_RIGHT),

	/** The label up left. */
	LABEL_UP_LEFT(LABEL_UP + " � " + LABEL_LEFT),

	/** The label down right. */
	LABEL_DOWN_RIGHT(LABEL_DOWN + " � " + LABEL_RIGHT),

	/** The label down left. */
	LABEL_DOWN_LEFT(LABEL_DOWN + " � " + LABEL_LEFT),

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
	LABEL_LEGENDE_TITLE("L�gande"),

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
	ERROR_MOVE_IS_OBSTACLE("D�placement imposible : il y a un obstacle !"),

	/** The error move is obstacle. */
	ERROR_MOVE_NOT_ENOUTH_ENERGY(
			"D�placement imposible : il y pas assez d'�nergie"),

	/** The error move is obstacle. */
	ERROR_ATTACK_NOT_ENOUTH_ENERGY(
			"Attaque imposible : il y pas assez d'�nergie"),

	/** The error nothing to attack. */
	ERROR_ATTACK_NOTHING_TO_ATTACK("Attaque imposible : il y a rien � attaquer"),

	/** The error nothing to attack. */
	ERROR_ATTACK_NOT_ENOUTH_MINE(
			"Attaque imposible : il y a pas assez de mines"),

	/** The error nothing to attack. */
	ERROR_ATTACK_ALREADY_MINED("Attaque imposible : il y a d�ja une mine !"),

	/** The error nothing to attack. */
	ERROR_ATTACK_CANT_MINE_BASE(
			"Attaque imposible : tu ne peut pas miner une base !"),

	/** The error nothing to attack. */
	ERROR_ATTACK_CANT_MINE_ROBOT(
			"Attaque imposible : tu ne peut pas miner un robot !"),

	/** The error move is out of bound. */
	ERROR_ATTTACK_IS_OUT_OF_BOUND(
			"Attaque imposible : ce n'est pas sur le plateau !"),

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
	private TextData(String nameFr) {
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
