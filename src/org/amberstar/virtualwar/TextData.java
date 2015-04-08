package org.amberstar.virtualwar;

public enum TextData {
	TANK_NAME("tank"), SCAVENGER_NAME("piegeur"), SHOOTER_NAME("tireur"), ERROR_MOVE_ISOBSTACLE(
			"Déplacement imposible : il y a un obstacle");

	private String name = "";

	// Constructeur
	TextData(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
