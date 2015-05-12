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
package org.virtualwar.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.virtualwar.config.Constant;
import org.virtualwar.config.TextData;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;

/**
 * The Board class is the class for the actual board of game containing robots,
 * obstacle and mines.
 *
 * @author amberstar
 *
 */
public class Board {

	/**
	 * Board factory.
	 *
	 * @param height
	 *            the height size of the board
	 * @param width
	 *            the width size of the board
	 * @return the instance, if not valid, get a null instance
	 */
	public static Board newBoard(int height, int width) {
		if (height <= 3 || width <= 3) {
			return null;
		}
		return new Board(height, width);
	}

	/** the size of the board in height. */
	private int sizeHeight;

	/** the size of the board in width. */
	private int sizeWidth;

	/** a array of Cell for storing the grind. */
	private Cell[][] grind;

	/**
	 * private constructor.
	 *
	 * @param height
	 *            the height size of the board
	 * @param width
	 *            the width size of the board
	 */
	public Board(int height, int width) {
		sizeHeight = height;
		sizeWidth = width;
		initEmptyGrind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		Board other = (Board) obj;
		if (!Arrays.deepEquals(grind, other.grind)) {
			return false;
		}
		if (sizeHeight != other.sizeHeight) {
			return false;
		}
		if (sizeWidth != other.sizeWidth) {
			return false;
		}
		return true;
	}

	/**
	 * Fill the area with obstacle, checks if a way exist while adding obstacle.
	 *
	 * @param nm
	 *            Parentage of obstacles over the field
	 * @param containsTank
	 *            true will make a path for tanks
	 */
	private void fillAlea(int nm, boolean containsTank) {
		Random r = new Random();
		int nmbObstacl = (int) ((nm * sizeHeight * sizeWidth - 2.0) / 100.0);

		List<Cell> lsEmpty = new ArrayList<Cell>();
		for (int i = 0; i < grind.length; i++) {
			for (int j = 0; j < grind[0].length; j++) {
				if (grind[i][j] instanceof Base) {
					continue;
				}
				lsEmpty.add(grind[i][j]);
			}
		}

		while (nmbObstacl >= 0 && lsEmpty.size() > 1) {
			Cell tmp = lsEmpty.remove(r.nextInt(lsEmpty.size() - 1));
			tmp.setObstacle();
			if (!hasPath(containsTank)) {
				tmp.removeObstacle();
			} else {
				nmbObstacl--;
			}
		}

	}

	/**
	 * Generate the whole grind.
	 *
	 * @param pourcentageGen
	 *            the percentage of obstacles in the way
	 * @param containsTank
	 *            True will generate for tanks, False for other (tank can't go
	 *            in diagonal)
	 */
	public void generate(int pourcentageGen, boolean containsTank) {
		initEmptyGrind();
		if (pourcentageGen > 0) {
			fillAlea(pourcentageGen, containsTank);
		}
	}

	/**
	 * Gets the cell.
	 *
	 * @param coordinates
	 *            the coordinate to get
	 * @return the cell at the coordinates, null if none or not valid
	 */
	public Cell getCell(Coordinates coordinates) {
		return (isValid(coordinates)) ? grind[coordinates.getHeight()][coordinates
				.getWidth()] : null;
	}

	public Coordinates getCoordsBase(int team) {
		return (team == Constant.ID_TEAM_A) ? new Coordinates(0, 0)
				: new Coordinates(sizeWidth - 1, sizeHeight - 1);
	}

	/**
	 * Gets the height.
	 *
	 * @return sizeHeight
	 */
	public final int getHeight() {
		return sizeHeight;
	}

	/**
	 * Gets the robot.
	 *
	 * @param coordinates
	 *            the coordinate to get
	 * @return the robot at the coordinates, null if none or not valid
	 */

	public Robot getRobot(Coordinates coordinates) {
		return (isValid(coordinates)) ? getCell(coordinates).getRobotIn()
				: null;
	}

	/**
	 * Get the value of the cell row (state) with the view (equip) into it.
	 *
	 * @param cell
	 *            the cell of chose
	 * @param state
	 *            0, 1 or 2
	 * @param equipe
	 *            the equip
	 * @return the string
	 */
	private String getValueOf(Cell cell, int state, int equipe) {
		if (equipe != Constant.ID_TEAM_A && equipe != Constant.ID_TEAM_B) {
			equipe = -1;
		}
		switch (state) {
		case 0:
			if (cell instanceof Base) {
				return "   |";
			} else if (cell.isObstacle()) {
				return "   |";
			} else if (cell.mineContains() == equipe
					|| (cell.mineContains() != 0 && equipe == -1)) {
				return "   |";
			} else if (cell.getRobotIn() == null) {
				return "   |";
			} else if (cell.getRobotIn() instanceof Tank) {
				if (cell.getRobotIn().getTeam() == Constant.ID_TEAM_A) {
					return " " + TextData.LABEL_TANK_TEAM_A + " |";
				} else if (cell.getRobotIn().getTeam() == Constant.ID_TEAM_B) {
					return " " + TextData.LABEL_TANK_TEAM_B + " |";
				}

			} else if (cell.getRobotIn() instanceof Scavenger) {
				if (cell.getRobotIn().getTeam() == Constant.ID_TEAM_A) {
					return " " + TextData.LABEL_SCAVENGER_TEAM_A + " |";
				} else if (cell.getRobotIn().getTeam() == Constant.ID_TEAM_B) {
					return " " + TextData.LABEL_SCAVENGER_TEAM_B + " |";
				}

			} else if (cell.getRobotIn() instanceof Shooter) {
				if (cell.getRobotIn().getTeam() == Constant.ID_TEAM_A) {
					return " " + TextData.LABEL_SHOOTER_TEAM_A + " |";
				} else if (cell.getRobotIn().getTeam() == Constant.ID_TEAM_B) {
					return " " + TextData.LABEL_SHOOTER_TEAM_B + " |";
				}
			}
			break;
		case 1:
			if (cell instanceof Base) {
				if (cell.isBase() == Constant.ID_TEAM_A) {
					return " " + TextData.LABEL_BASE_TEAM_A + " |";
				} else if (cell.isBase() == Constant.ID_TEAM_B) {
					return " " + TextData.LABEL_BASE_TEAM_B + " |";
				}

			} else if (cell.isObstacle()) {
				return " " + TextData.LABEL_OBSTACLE + " |";

			} else if (cell.mineContains() == equipe
					|| (cell.mineContains() != Constant.ID_TEAM_NULL && equipe == -1)) {

				if (cell.mineContains() == Constant.ID_TEAM_A) {
					return " " + TextData.LABEL_MINE_TEAM_A + " |";
				} else if (cell.mineContains() == Constant.ID_TEAM_B) {
					return " " + TextData.LABEL_MINE_TEAM_B + " |";
				}

			} else if (cell.getRobotIn() == null) {
				return "   |";
			} else if (!(cell.getRobotIn() instanceof Scavenger)) {
				return "   |";
			} else if (cell.getRobotIn() instanceof Scavenger) {
				return String.format("M%02d|",
						((Scavenger) cell.getRobotIn()).getStock());
			}

			break;
		case 2:
			if (cell instanceof Base) {
				return "   |";
			} else if (cell.isObstacle()) {
				return "   |";
			} else if (cell.mineContains() != Constant.ID_TEAM_NULL) {
				return "   |";
			} else if (cell.getRobotIn() == null) {
				return "   |";
			} else if (cell.getRobotIn() != null) {
				return String.format("E%02d|", cell.getRobotIn().getEnergy());
			}

			break;

		default:
			break;
		}
		return "   |";
	}

	/**
	 * Gets the width.
	 *
	 * @return sizeWidth
	 */
	public int getWidth() {
		return sizeWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(grind);
		result = prime * result + sizeHeight;
		result = prime * result + sizeWidth;
		return result;
	}

	/**
	 * Checks for path.
	 *
	 * @param containsTank
	 *            If true, will check so a tank can pass by
	 * @return if valid path exist
	 */
	private boolean hasPath(boolean containsTank) {
		int[][] grindToInt = new int[sizeHeight][sizeWidth];
		int[][] tmpGrind = new int[sizeHeight][sizeWidth];
		for (int i = 0; i < grindToInt.length; i++) {
			for (int j = 0; j < tmpGrind[0].length; j++) {
				if (grind[i][j].isObstacle()) {
					grindToInt[i][j] = 0;
				} else {
					grindToInt[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < tmpGrind.length; i++) {
			Arrays.fill(tmpGrind[i], -1);
		}
		return hasPathHelper(grindToInt, tmpGrind, 0, 0, containsTank);
	}

	/**
	 * recursive function for finding if a path exist only for regular units.
	 *
	 * @param grindToInt
	 *            Board converted to numbers : 0 obstacle, 1 empty
	 * @param tmpGrind
	 *            array of integer (-1 : visited, 0 visited, 1 good path)
	 * @param i
	 *            equivalent to y
	 * @param j
	 *            equivalent to x
	 * @param hasTank
	 *            if true, will find a path for tanks too
	 * @return if tmpGrind[i][j] == 1
	 */
	private boolean hasPathHelper(int[][] grindToInt, int[][] tmpGrind, int i,
			int j, boolean hasTank) {
		if (i < 0 || j < 0 || i >= grindToInt.length
				|| j >= grindToInt[0].length || tmpGrind[i][j] >= 0) {
			return false; // Index out of bounds
		}

		tmpGrind[i][j] = 0; // Mark as visited
		if (grindToInt[i][j] == 0) {
			return false;
		}
		if (j == grindToInt[0].length - 1 && i == grindToInt.length - 1 || // Right
																			// side
																			// reached!
				hasPathHelper(grindToInt, tmpGrind, i + 1, j, hasTank) || // Check
																			// down
				hasPathHelper(grindToInt, tmpGrind, i - 1, j, hasTank) || // Check
																			// up
				hasPathHelper(grindToInt, tmpGrind, i, j + 1, hasTank) || // Check
																			// right
				hasPathHelper(grindToInt, tmpGrind, i, j - 1, hasTank) // Check
																		// left
		) {
			tmpGrind[i][j] = 1; // Mark as good path
		}

		if (!hasTank) {
			if (j == grindToInt[0].length - 1
					&& i == grindToInt.length - 1
					|| // Right
						// side
						// reached!
					hasPathHelper(grindToInt, tmpGrind, i - 1, j + 1, hasTank)
					|| // Check
						// upper
						// right
					hasPathHelper(grindToInt, tmpGrind, i + 1, j + 1, hasTank)
					|| // Check
						// lower
						// right
					hasPathHelper(grindToInt, tmpGrind, i + 1, j - 1, hasTank)
					|| // Check
						// lower
						// left
					hasPathHelper(grindToInt, tmpGrind, i + 1, j - 1, hasTank)
			// Check
			// upper
			// left
			) {
				tmpGrind[i][j] = 1; // Mark as good path
			}
		}

		return tmpGrind[i][j] == 1;
	}

	/**
	 * Create a empty Board with only the bases.
	 */
	private void initEmptyGrind() {
		grind = new Cell[sizeHeight][sizeWidth];
		for (int i = 0; i < grind.length; i++) {
			for (int j = 0; j < grind[0].length; j++) {
				grind[i][j] = new Case(new Coordinates(j, i));
			}
		}
		grind[0][0] = new Base(new Coordinates(0, 0), Constant.ID_TEAM_A);

		grind[sizeHeight - 1][sizeWidth - 1] = new Base(new Coordinates(
				sizeHeight - 1, sizeWidth - 1), Constant.ID_TEAM_B);
	}

	/**
	 * Checks if is base.
	 *
	 * @param coordinates
	 *            the position of the test
	 * @return the team of the base (0 if none)
	 */
	public int isBase(Coordinates coordinates) {
		return (isValid(coordinates)) ? getCell(coordinates).isBase()
				: Constant.ID_TEAM_NULL;
	}

	/**
	 * Checks if is mine.
	 *
	 * @param coordinates
	 *            the position of the test
	 * @return the team of the mine (0 if none)
	 */
	public int isMine(Coordinates coordinates) {
		return (isValid(coordinates)) ? getCell(coordinates).mineContains()
				: Constant.ID_TEAM_NULL;
	}

	/**
	 * Checks if it is a obstacle.
	 *
	 * @param coordinates
	 *            the position of the test
	 * @return true if obstacle
	 */
	public boolean isObstacle(Coordinates coordinates) {
		return (isValid(coordinates)) ? getCell(coordinates).isObstacle()
				: false;
	}

	/**
	 * if coordinates are valid.
	 *
	 * @param coordinates
	 *            input coordinates
	 * @return true if good
	 */
	public boolean isValid(Coordinates coordinates) {
		return 0 <= coordinates.getHeight()
				&& coordinates.getHeight() < sizeHeight
				&& 0 <= coordinates.getWidth()
				&& coordinates.getWidth() < sizeWidth;
	}

	/**
	 * generate a printable version (only the board).
	 *
	 * @param equipe
	 *            the team to generate for
	 * @return text to print
	 */
	public String outGrind(int equipe) {
		StringBuilder build = new StringBuilder();
		for (int m = 0; m < 2; m++) {
			build.append("++");
			for (int i = 0; i < grind[0].length; i++) {
				build.append("---+");
			}
			build.append("+\n");
		}

		for (int yLo = 0; yLo < grind.length; yLo++) {

			for (int cpt = 0; cpt < 3; cpt++) {
				build.append("||");
				for (int xLo = 0; xLo < grind[0].length; xLo++) {
					Cell tmp = grind[yLo][xLo];
					build.append(getValueOf(tmp, cpt, equipe));
				}
				build.append("|\n");
			}

			// line separator
			build.append("++");
			for (int i = 0; i < grind[0].length; i++) {
				build.append("---+");
			}
			build.append("+\n");

		}

		for (int m = 0; m < 1; m++) {
			build.append("++");
			for (int i = 0; i < grind[0].length; i++) {
				build.append("---+");
			}
			build.append("+\n");
		}

		return build.toString();
	}

	/**
	 * Generate a printable version with the legend.
	 *
	 * @param equipe
	 *            the equip
	 * @return the string
	 */
	public String outGrindPlusLegend(int equipe) {
		String[] out = outGrind(equipe).split("\n");
		boolean[] status = new boolean[out.length];

		for (int i = 0; i < out.length; i++) {
			out[i].replace("\n", "");
			out[i] += "   ";
			status[i] = false;
		}
		int[] lsInt = new int[] { 2, 4, 6, 8, 10, 12, 14, 16, 18 };
		String[] lsTxt = new String[] {
				TextData.LABEL_LEGENDE_TITLE.toString(),
				TextData.LABEL_LEGENDE_BASE.toString(),
				TextData.LABEL_LEGENDE_CHAR.toString(),
				TextData.LABEL_LEGENDE_SHOOTER.toString(),
				TextData.LABEL_LEGENDE_SCAVENGER.toString(),
				TextData.LABEL_LEGENDE_MINE.toString(),
				TextData.LABEL_LEGENDE_OBSTACLE.toString(),
				TextData.LABEL_LEGENDE_ENERGY.toString(),
				TextData.LABEL_LEGENDE_NMB_MINE.toString() };
		if (sizeHeight == 4) {
			lsInt = new int[] { 1, 3, 4, 5, 6, 7, 8, 9, 10 };
		}
		for (int i = 0; i < lsTxt.length; i++) {
			out[lsInt[i]] += String.format("|   %-20s|",
					String.format("%.19s", lsTxt[i]));
			status[lsInt[i]] = true;
		}

		out[0] += "+-----------------------+";
		out[out.length - 3] += String.format("|   %-20s|",
				String.format("%.17s", TextData.LABEL_LEGENDE_HELP.toString()));
		out[out.length - 4] += String.format("|   %-20s|",
				String.format("%.17s", TextData.LABEL_LEGENDE_QUIT.toString()));
		out[out.length - 1] += "+-----------------------+";

		status[0] = true;
		status[out.length - 3] = true;
		status[out.length - 4] = true;
		status[out.length - 1] = true;

		for (int i = 0; i < status.length; i++) {
			if (!status[i]) {
				out[i] += "|                       |";
			}
		}

		StringBuilder finalOut = new StringBuilder();
		for (String string : out) {
			finalOut.append(string);
			finalOut.append('\n');
		}
		return finalOut.toString();
	}

	/**
	 * set robot to the given coordinates; don't do anything if coordinates
	 * aren't valid.
	 *
	 * @param cords
	 *            the place to set to
	 * @param mine
	 *            mine to set to
	 * @return if success
	 */
	public boolean setMine(Coordinates cords, int mine) {
		Cell tmp = getCell(cords);
		if (tmp instanceof Base || tmp == null) {
			return false;
		}
		tmp.setMine(mine);
		return true;
	}

	/**
	 * set robot to the given coordinates; don't do anything if coordinates
	 * aren't valid.
	 *
	 * @param r
	 *            robot to set to
	 * @param coordinates
	 *            the place to set to
	 * @return the success
	 */
	public boolean setRobot(Robot r, Coordinates coordinates) {
		Cell tmp = getCell(coordinates);
		if (tmp == null) {
			return false;
		}
		if (r.getCoordinates() != null && getCell(r.getCoordinates()) != null) {
			getCell(r.getCoordinates()).removeRobotIn(r);
		}
		r.setCoordinates(coordinates);

		if (tmp instanceof Base) {
			Base tmap = (Base) tmp;
			return tmap.moveOn(r);
		}

		return tmp.moveOn(r);
	}

	/**
	 * To string.
	 *
	 * @return return a debug version only with bases and obstacles
	 */
	@Override
	public String toString() {
		String ret = "";
		for (int i = 0; i < grind.length; i++) {
			for (int j = 0; j < grind[0].length; j++) {
				if (grind[i][j] instanceof Base) {
					ret += "B";
				} else {
					if (grind[i][j].isObstacle()) {
						ret += "*";
					} else {
						ret += ".";
					}
				}
			}
			ret += "\n";
		}
		return ret;
	}

}
