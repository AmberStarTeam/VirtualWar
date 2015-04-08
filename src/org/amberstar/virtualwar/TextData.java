package org.amberstar.virtualwar;

public enum TextData {
	TANK_NAME("tank"),
	SCAVENGER_NAME("piegeur"),
	SHOOTER_NAME("tireur"),
	ERROR_MOVE_IS_OBSTACLE("D�placement imposible : il y a un obstacle !"),
	ERROR_MOVE_IS_OCCUPED("D�placement imposible : il y a d�j� un robot pr�sent !"),
	ERROR_MOVE_IS_MINE("D�placement imposible : il y a une mine de votre �quipe !"),
	ERROR_MOVE_IS_OUT_OF_BOUND("D�placement imposible : ce n'est pas sur le plateau !"),
	ERROR_MOVE_IS_BASE_OTHER("D�placement imposible : ce n'est pas votre base !"),
	ERROR_MOVE_IS_NOT_VALID("D�placement imposible : ce n'est pas un d�placement possible pour cette unit�e !"),
	ERROR_BOARD_TOO_SMALL("Cr�ation impossible : le plateau est trop petit !"),
	ERROR_BOARD_TOO_BIG("Cr�ation impossible : le plateau est trop grand !"),
	ERROR_BOARD_MUST_BE_POSITIF("Cr�ation impossible : les valeurs doivent �tre positives");

	private String name = "";

	// Constructeur
	TextData(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
