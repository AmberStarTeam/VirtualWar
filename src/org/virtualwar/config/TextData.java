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
/**
 * The TextData Enum.
 *
 * @author amberstar
 */
public enum TextData {

	/** The label regle. */
	LABEL_REGLE(
			"\tRègles du jeu :\n"
					+ "Les pays s'affrontent en faisant combattre des robots sur un plateau.\n\n"
					+ "Le jeu se déroule au tour par tour."
					+ " L'équipe qui commence est choisie aléatoirement.\n\n"
					+ "Les robots commencent dans leur base respective.\n\n"
					+ "A chaque tour, chaque équipe choisit"
					+ " un de ses robots pour réaliser une de ses actions.\n\n"
					+ "Une équipe ne peut pas passer son tour.\n"
					+ "Elle doit jouer tant que ses robots ont"
					+ " assez d'énergie pour réaliser une action.\n\n"
					+ "Au cours de la partie chaque équipe doit"
					+ " conserver au moins un robot hors de sa base.\n\n"
					+ "La partie se termine dès que l'un des deux"
					+ " partis ne possède plus de robot vivant.\n\n"
					+ "\t Comment jouer ? : \n\n"
					+ "A) Les robots : \n\n"
					+ "Tireur : Robot léger, capable de tirer sur une courte distance."
					+ " Peut se déplacer dans tout les sens.\n\n"
					+ "Piégeur : Poseur de mine. Peut se déplacer dans tous les sens.\n\n"
					+ "Char : Robot lourd, capable de tirer sur de longues distances."
					+ " Ne peut pas se déplacer en diagonale.\n\n\n"
					+ "B) Bases et energie : \n\n"
					+ "Chaque action coûte de l'énergie."
					+ "Quand un robot n'a plus d'énergie, il ne peut plus jouer.\n\n"
					+ "Quand il retourne dans sa base, un robot récupère peu à peu son énergie."),

	/** The label up. */
	LABEL_UP("haut", "up"),

	/** The label down. */
	LABEL_DOWN("bas", "down"),

	/** The label right. */
	LABEL_RIGHT("droite", "right"),

	/** The label left. */
	LABEL_LEFT("gauche", "left"),

	/** The label up right. */
	LABEL_UP_RIGHT(LABEL_UP.get("fr") + " à " + LABEL_RIGHT.get("fr"), LABEL_UP
			.get("en") + " " + LABEL_RIGHT.get("en")),

	/** The label up left. */
	LABEL_UP_LEFT(LABEL_UP.get("fr") + " à " + LABEL_LEFT.get("fr"), LABEL_UP
			.get("en") + " " + LABEL_LEFT.get("en")),

	/** The label down right. */
	LABEL_DOWN_RIGHT(LABEL_DOWN.get("fr") + " à " + LABEL_RIGHT.get("fr"),
			LABEL_DOWN.get("en") + " " + LABEL_RIGHT.get("en")),

	/** The label down left. */
	LABEL_DOWN_LEFT(LABEL_DOWN.get("fr") + " à " + LABEL_LEFT.get("fr"),
			LABEL_DOWN.get("en") + " " + LABEL_LEFT.get("en")),

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
	LABEL_LEGENDE_TITLE("Légende", "Legend"),

	/** The tank name. */
	TANK_NAME("Tank", "Tank"),

	/** The scavenger name. */
	SCAVENGER_NAME("Piegeur", "Scavenger"),

	/** The shooter name. */
	SHOOTER_NAME("Tireur", "Shooter"),

	/** The label legende base. */
	LABEL_LEGENDE_BASE(LABEL_BASE_TEAM_A + "\\" + LABEL_BASE_TEAM_B + " : base"),

	/** The label legende char. */
	LABEL_LEGENDE_CHAR(LABEL_TANK_TEAM_A + "\\" + LABEL_TANK_TEAM_B + " : "
			+ TANK_NAME.get("fr"), LABEL_TANK_TEAM_A + "\\" + LABEL_TANK_TEAM_B
			+ " : " + TANK_NAME.get("en")),

	/** The label legende shooter. */
	LABEL_LEGENDE_SHOOTER(LABEL_SHOOTER_TEAM_A + "\\" + LABEL_SHOOTER_TEAM_B
			+ " : " + SHOOTER_NAME.get("fr"), LABEL_SHOOTER_TEAM_A + "\\"
			+ LABEL_SHOOTER_TEAM_B + " : " + SHOOTER_NAME.get("en")),

	/** The label legende scavenger. */
	LABEL_LEGENDE_SCAVENGER(LABEL_SCAVENGER_TEAM_A + "\\"
			+ LABEL_SCAVENGER_TEAM_B + " : " + SCAVENGER_NAME.get("fr"),
			LABEL_SCAVENGER_TEAM_A + "\\" + LABEL_SCAVENGER_TEAM_B + " : "
					+ SCAVENGER_NAME.get("en")),

	/** The label legende mine. */
	LABEL_LEGENDE_MINE(LABEL_MINE_TEAM_A + "\\" + LABEL_MINE_TEAM_B + " : mine"),

	/** The label legende obstacle. */
	LABEL_LEGENDE_OBSTACLE(LABEL_OBSTACLE + " : obstacle"),

	/** The label legende energy. */
	LABEL_LEGENDE_ENERGY("E : energie", "E : energy"),

	/** The label legende nmb mine. */
	LABEL_LEGENDE_NMB_MINE("M : nombre de mines", "M : number of mines"),

	/** The label legende help. */
	LABEL_LEGENDE_HELP("h - regles", "h - rules"),

	/** The label legende quit. */
	LABEL_LEGENDE_QUIT("q - quitter", "q - quit"),

	/** The error move is obstacle. */
	ERROR_MOVE_IS_OBSTACLE("Déplacement imposible : Il y a un obstacle !",
			"Move impossible : There is an obstacle on the way !"),

	/** The error move is obstacle. */
	ERROR_MOVE_NOT_ENOUTH_ENERGY(
			"Déplacement imposible : Il n'a pas assez d'énergie",
			"Move impossible : You don't have enough energy !"),

	/** The error move is obstacle. */
	ERROR_ATTACK_NOT_ENOUTH_ENERGY(
			"Attaque imposible : Il n'a pas assez d'énergie",
			"Attack impossible : You don't have enough energy !"),

	/** The error nothing to attack. */
	ERROR_ATTACK_NOTHING_TO_ATTACK(
			"Attaque imposible : Il n'y a rien à attaquer",
			"Attack impossible : Nothing to attack"),

	/** The error nothing to attack. */
	ERROR_ATTACK_NOT_ENOUTH_MINE(
			"Attaque imposible : Il n'a pas assez de mines",
			"Attack impossible : You don't have enough mine !"),

	/** The error nothing to attack. */
	ERROR_ATTACK_ALREADY_MINED("Attaque imposible : Il y a déjà une mine !",
			"Attack impossible : There is already a mine here !"),

	/** The error nothing to attack. */
	ERROR_ATTACK_CANT_ATTACK_BASE(
			"Attaque imposible : Tu ne peux pas attaquer une base",
			"Attack impossible : You can't attack a base"),

	/** The error nothing to attack. */
	ERROR_ATTACK_CANT_FROM_BASE(
			"Attaque imposible : Tu ne peux pas attaquer depuis base",
			"Attack impossible : You can't attack from your base"),

	/** The error nothing to attack. */
	ERROR_ATTACK_CANT_MINE_BASE(
			"Attaque imposible : Tu ne peux pas miner une base !",
			"Attack impossible : You can't mine a base !"),

	/** The error nothing to attack. */
	ERROR_ATTACK_CANT_MINE_ROBOT(
			"Attaque imposible : Tu ne peux pas miner un robot !",
			"Attack impossible : You can't mine a robot !"),

	/** The error nothing to attack. */
	ERROR_ATTACK_CANT_MINE_OBSTACLE("Mine imposible : Il y a un obstacle !",
			"Mine impossible : It's an obstacle"),

	/** The error move is out of bound. */
	ERROR_ATTTACK_IS_OUT_OF_BOUND(
			"Attaque imposible : Ce n'est pas sur le plateau !",
			"Attack impossible : It's not on the board"),

	/** The robot has been shot. */
	ERROR_MOVE_NOWHERE_TO_MOVE(
			"Déplacement imposible : Pas d'endroit où aller !",
			"Move impossible : Nowhere to go !"),

	/** The error move is occupied. */
	ERROR_MOVE_IS_OCCUPED(
			"Déplacement imposible : Il y a déjà un robot présent !",
			"Move impossible : A robot is already there !"),

	/** The error move is mine. */
	ERROR_MOVE_IS_MINE(
			"Déplacement imposible : Il y a une mine de votre équipe !",
			"Move impossible : There is one of your team's mines !"),

	/** The error move is out of bound. */
	ERROR_MOVE_IS_OUT_OF_BOUND(
			"Déplacement imposible : Ce n'est pas sur le plateau !",
			"Move impossible : It's not on the board !"),

	/** The error move is base other. */
	ERROR_MOVE_IS_BASE_OTHER(
			"Déplacement imposible : Ce n'est pas votre base !",
			"Move impossible : It's not your base !"),

	/** The error move is not valid. */
	ERROR_MOVE_IS_NOT_VALID(
			"Déplacement imposible : Ce n'est pas un déplacement possible pour cette unité !",
			"Move impossible : It's not a valid move for this robot !"),

	/** The error board too small. */
	ERROR_BOARD_TOO_SMALL("Création impossible : Le plateau est trop petit !",
			"Creation impossible : The board is too small !"),

	/** The error board too big. */
	ERROR_BOARD_TOO_BIG("Création impossible : Le plateau est trop grand !",
			"Creation impossible : The board is too big !"),

	/** The error board must be positive. */
	ERROR_BOARD_MUST_BE_POSITIF(
			"Création impossible : Les valeurs doivent être positives",
			"Creation impossible : The values must be positive"),

	/** The robot has been shot. */
	ROBOT_HAS_BEEN_SHOT(" a été touché !", "has been shoot"),

	/** The robot has been mine. */
	ROBOT_HAS_BEEN_MINE(" a été piégé !", "has been mined"),

	/** The robot mine. */
	ROBOT_MINE("Un piégeur a miné !", "A scavenger has mined !"),

	/** The game move. */
	GAME_MOVE("Déplacer", "Move"),

	/** The game attack. */
	GAME_ATTACK("Attaquer", "Attack"),

	/** The game mine. */
	GAME_MINE("Miner", "Mine"),

	/** The game or. */
	GAME_OR("ou", "or"),

	/** The game cancel. */
	GAME_CANCEL("Anuler", "Cancel"),

	/** The game moving on. */
	GAME_MOVING_ON("Déplacement en ", "Moving on "),

	/** The game board size. */
	GAME_BOARD_SIZE(
			"Choisissez la taille du plateau (elle ne doit pas être inférieure à 3)",
			"Chose a board size (can't be inferior to 3)"),

	/** The game height. */
	GAME_HEIGHT("Hauteur", "Height"),

	/** The game width. */
	GAME_WIDTH("Largeur", "Width"),

	/** The game is player x ia. */
	GAME_IS_PLAYER_X_IA("Le joueur X est-il une ia? ", "Is the player X an ia?"),

	/** The game chose ia input. */
	GAME_CHOSE_IA_INPUT("o\nn" + '\u9999' + "(o) oui, (n) non", "y\nn"
			+ '\u9999' + "(y) yes, (n) no"),

	GAME_LANG_INPUT("fr\nen" + '\u9999' + "fr " + GAME_OR.get("fr") + " en :"),

	/** The game chose sound. */
	GAME_CHOSE_SOUND("Son : ", "Sound : "),

	/** The game chose ia input. */
	GAME_CHOSE_SOUND_INPUT("o\nf" + '\u9999' + "(o) on, (f) off"),

	/** The game obstacle. */
	GAME_OBSTACLE("Pourcentage d'obstacles : ", "Percentage of obstacle : "),

	/** The game turn player. */
	GAME_TURN_PLAYER("Au tour de ", "It's the turn of "),

	/** The game win team. */
	GAME_WIN_TEAM("L'équipe XXX à gagner !", "The team XXX has won !"),

	/** The game end. */
	GAME_END("Appuyez sur une touche pour quitter", "Push any key to quit"),

	/** The game name team x. */
	GAME_NAME_TEAM_X("Nom de la team X : ", "Name of the X team : "),

	/** The game chose nmb bots. */
	GAME_CHOSE_NMB_BOTS(
			"Choisissez le nombre de robots pour les équipes (<6:) :",
			"Chose the number of robots for all teams (<6) :"),

	/** The game invalid input. */
	GAME_INVALID_INPUT("Entrée invalide ! ", "Invalid input !"),

	/** The game chose robots type. */
	GAME_CHOSE_ROBOTS_TYPE("Choisissez le type de vos robots pour X : ",
			"Chose the robots' type for X : "),

	/** The error not number. */
	ERROR_NOT_NUMBER("Ce n'est pas un nombre !", "Not a number !"),

	/** The error team same name. */
	ERROR_TEAM_SAME_NAME("Les deux équipes ont le même nom !",
			"Both team have the same name !");

	/** The french name. */
	private String fr;

	/** The English name. */
	private String en;

	// Constructeur
	/**
	 * Instantiates a new text data.
	 *
	 * @param fr
	 *            the name
	 */
	private TextData(String fr) {
		this.fr = fr;
	}

	/**
	 * Instantiates a new text data.
	 *
	 * @param fr
	 *            the fr
	 * @param en
	 *            the en
	 */
	private TextData(String fr, String en) {
		this.fr = fr;
		this.en = en;
	}

	/**
	 * Gets the.
	 *
	 * @param language
	 *            the language
	 * @return the string
	 */
	public String get(String language) {
		if (language.equals("en")) {
			if (en != null) {
				return en;
			} else {
				return fr;
			}
		}
		if (language.equals("fr")) {
			if (fr != null) {
				return fr;
			}
		}
		return fr;
	}

	/**
	 * . (non-Javadoc)
	 *
	 * @return the value of field
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		if (Config.language.equals("en")) {
			if (en != null) {
				return en;
			} else {
				return fr;
			}
		}
		if (Config.language.equals("fr")) {
			if (fr != null) {
				return fr;
			}
		}
		return fr;
	}
}
