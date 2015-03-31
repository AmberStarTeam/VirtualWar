package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The Board class is the class for the actual board of game containing robots,
 * obstacle and mines.
 * 
 * @author AmberStar
 *
 */
public class Board {

    /** the size of the board in height */
    private int sizeHeight;

    /** the size of the board in width */
    private int sizeWidth;

    /** a array of Cell for storing the grind */
    private Cell[][] grind;

    /**
     * public constructor
     * 
     * @param height
     *            the height size of the board
     * @param width
     *            the width size of the board
     */
    public Board(int height, int width) {
        this.sizeHeight = height;
        this.sizeWidth = width;
        initEmptyGrind();
    }

    /**
     * Create a empty Board with only the bases
     */
    private void initEmptyGrind() {
        grind = new Cell[sizeHeight][sizeWidth];
        for (int i = 0; i < grind.length; i++) {
            for (int j = 0; j < grind[0].length; j++) {
                grind[i][j] = new Case(new Coordinates(j, i));
            }
        }
        grind[0][0] = new Base(new Coordinates(0, 0), 1);

        grind[sizeHeight - 1][sizeWidth - 1] = new Base(new Coordinates(
                sizeHeight - 1, sizeWidth - 1), 2);
    }

    /**
     * 
     * @param pourcentageGen
     *            0<= the percentage of obstacles in the way
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
     * Fill the area with obstacle, checks if a way exist while adding obstacle
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
            tmp.setObscacle();
            if (!hasPath(containsTank)) {
                tmp.removeObscacle();
            } else {
                nmbObstacl--;
            }
        }

    }

    /**
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
                if (grind[i][j].isObscacle()) {
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
     * recursive function for finding if a path exist only for regular units
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
     *            if true, will find a path for tanks
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
     * @return sizeHeight
     */
    public final int getHeight() {
        return sizeHeight;
    }

    /**
     * @return sizeWidth
     */
    public int getWidth() {
        return sizeWidth;
    }

    /**
     * @param coordinates
     *            the coordinate to get
     * @return the cell at the coordinates, null if none or not valid
     */
    public Cell getCell(Coordinates coordinates) {
        return (isValid(coordinates)) ? grind[coordinates.getHeight()][coordinates
                .getWidth()] : null;
    }

    /**
     * @param coordinates
     *            the coordinate to get
     * @return the robot at the coordinates, null if none or not valid
     */

    public Robot getRobot(Coordinates coordinates) {
        return (isValid(coordinates)) ? getCell(coordinates).getRobotIn()
                : null;
    }

    /**
     * set robot to the given coordinates; don't do anything if coordinates
     * aren't valid
     * 
     * @param r
     *            robot to set to
     * @param coordinates
     *            the place to set to
     */
    public void setRobot(Robot r, Coordinates coordinates) {
        Cell tmp = getCell(coordinates);
        if (tmp == null) {
            return;
        }
        tmp.moveOn(r);
    }

    /**
     * set robot to the given coordinates; don't do anything if coordinates
     * aren't valid
     * 
     * @param mine
     *            mine to set to
     * @param cords
     *            the place to set to
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
     * 
     * @param coordinates
     *            the position of the test
     * @return true if base
     */
    public boolean isBase(Coordinates coordinates) {
        return getCell(coordinates) instanceof Base;
    }

    /**
     * 
     * @param coordinates
     *            the position of the test
     * @return true if obstacle
     */
    public boolean isObstacle(Coordinates coordinates) {
        return (isValid(coordinates)) ? getCell(coordinates).isObscacle()
                : false;
    }

    /**
     * 
     * @param coordinates
     *            the position of the test
     * @return true if mine
     */
    public int isMine(Coordinates coordinates) {
        return (isValid(coordinates)) ? getCell(coordinates).mineContains()
                : -1;
    }

    /**
     * if coordinates are valid
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
     * generate a printable version
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
                    switch (cpt) {
                    case 0:
                        if (tmp instanceof Base) {
                            build.append("   |");
                        } else if (tmp.isObscacle()) {
                            build.append("   |");
                        } else if (tmp.mineContains() == equipe
                                || (tmp.mineContains() != 0 && equipe == -1)) {
                            build.append("   |");
                        } else if (tmp.getRobotIn() == null) {
                            build.append("   |");
                        } else if (tmp.getRobotIn() instanceof Tank) {
                            build.append((tmp.getRobotIn().getTeam() == 1) ? " c |"
                                    : " C |");
                        } else if (tmp.getRobotIn() instanceof Scavenger) {
                            build.append((tmp.getRobotIn().getTeam() == 1) ? " p |"
                                    : " P |");
                        } else if (tmp.getRobotIn() instanceof Shooter) {
                            build.append((tmp.getRobotIn().getTeam() == 1) ? " t |"
                                    : " T |");
                        }
                        break;
                    case 1:
                        if (tmp instanceof Base) {
                            build.append((tmp.isBase() == 1) ? " b |" : " B |");
                        } else if (tmp.isObscacle()) {
                            build.append(" O |");
                        } else if (tmp.mineContains() == equipe
                                || (tmp.mineContains() != 0 && equipe == -1)) {
                            build.append((tmp.mineContains() == 1) ? " x |"
                                    : " X |");
                        } else if (tmp.getRobotIn() == null) {
                            build.append("   |");
                        } else if (!(tmp.getRobotIn() instanceof Scavenger)) {
                            build.append("   |");
                        } else if (tmp.getRobotIn() instanceof Scavenger) {
                            build.append(String.format("M%02d|",
                                    ((Scavenger) tmp.getRobotIn()).getStock()));
                        }

                        break;
                    case 2:
                        if (tmp instanceof Base) {
                            build.append("   |");
                        } else if (tmp.isObscacle()) {
                            build.append("   |");
                        } else if (tmp.mineContains() != 0) {
                            build.append("   |");
                        } else if (tmp.getRobotIn() == null) {
                            build.append("   |");
                        } else if (tmp.getRobotIn() != null) {
                            build.append(String.format("E%02d|", tmp
                                    .getRobotIn().getEnergy()));
                        }

                        break;

                    default:
                        break;
                    }
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
     * @return return a debug version only with bases and obstacles
     */
    public String toString() {
        String ret = "";
        for (int i = 0; i < grind.length; i++) {
            for (int j = 0; j < grind[0].length; j++) {
                if (grind[i][j] instanceof Base) {
                    ret += "B";
                } else {
                    if (grind[i][j].isObscacle()) {
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

    /**
     * debug command
     * 
     * @param args
     *            input stream parameters
     */
    public static void main(String[] args) {
        Board b = new Board(5, 7);
        // b.generate(300, true);
        // System.out.println(b);
        // System.out.println(b);
        /*
         * Board da = new Board(30, 30); for (int i = 0; i < 100; i++) {
         * da.generate(i, false); // System.out.println(i+"\n"+da.toString());
         * System.out.println(i); }
         */

        System.out.println(b);
        System.out.println(b.outGrind(1));
        Coordinates tanCop = new Coordinates(2, 2);
        Coordinates topCop = new Coordinates(1, 2);
        Coordinates lopCop = new Coordinates(1, 1);
        Robot tan = new Tank(1, tanCop, b);
        Robot top = new Shooter(1, topCop, b);
        Robot lop = new Scavenger(1, lopCop, b); // tan.setEnergy(1);
        b.setRobot(tan, tanCop);
        b.setRobot(top, topCop);
        b.setRobot(lop, lopCop);

        Coordinates min = new Coordinates(0, 4);
        Coordinates min2 = new Coordinates(2, 4);
        b.setMine(min, 2);
        b.setMine(min2, 1);
        b.setMine(new Coordinates(0, 6), 1);
        System.out.println(b.outGrind(1));
        /*
         * f //System.out.println(b.outGrind(-1)); String tmp = b.outGrind(-1);
         * String[] splied = tmp.split("\n"); for (String str : splited) {
         * System.out.println(str + " lol "); }
         */

    }
}
