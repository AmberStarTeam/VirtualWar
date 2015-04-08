package org.amberstar.virtualwar;

public enum TextData {
	TANK_NAME("tank"),
	SCAVENGER_NAME("piegeur"),
	SHOOTER_NAME("tireur"),
	ERROR_MOVE_IS_OBSTACLE("Déplacement imposible : il y a un obstacle !"),
	ERROR_MOVE_IS_OCCUPED("Déplacement imposible : il y a déjà un robot présent !"),
	ERROR_MOVE_IS_MINE("Déplacement imposible : il y a une mine de votre équipe !"),
	ERROR_MOVE_IS_OUT_OF_BOUND("Déplacement imposible : ce n'est pas sur le plateau !"),
	ERROR_MOVE_IS_BASE_OTHER("Déplacement imposible : ce n'est pas votre base !"),
	ERROR_MOVE_IS_NOT_VALID("Déplacement imposible : ce n'est pas un déplacement possible pour cette unitée !"),
	ERROR_BOARD_TOO_SMALL("Création impossible : le plateau est trop petit !"),
	ERROR_BOARD_TOO_BIG("Création impossible : le plateau est trop grand !"),
	ERROR_BOARD_MUST_BE_POSITIF("Création impossible : les valeurs doivent être positives");

	private String name = "";

	// Constructeur
	TextData(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
