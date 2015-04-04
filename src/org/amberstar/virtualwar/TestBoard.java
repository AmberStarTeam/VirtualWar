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

import java.util.HashMap;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The TestBoard Class.
 */
public final class TestBoard {

    /**
     * Instantiates a new test board.
     */
    private TestBoard() {
    }

    /**
     * Test gen.
     */
    public static void testGen() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        Long timeBef, timeAft;
        HashMap<Integer, Long> dataGen = new HashMap<Integer, Long>();

        System.out.println("Veuillez entrer la taille du plateau (h w)");
        Board b = new Board(sc.nextInt(), sc.nextInt());
        // System.out.println("Le plateau contient des tanks?");
        // input = sc.nextLine();
        boolean tankIn = false;
        if (input.equalsIgnoreCase("oui") || input.equalsIgnoreCase("o")
                || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            tankIn = true;
        }
        // System.out.println("Voulez vous afficher le plateau?");
        // input = sc.nextLine();

        boolean showBoard = false;
        if (input.equalsIgnoreCase("oui") || input.equalsIgnoreCase("o")
                || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            tankIn = true;
        }
        for (int i = 0; i < 100; i++) {
            timeBef = System.currentTimeMillis();
            b.generate(i, tankIn);
            timeAft = System.currentTimeMillis();
            dataGen.put(i, (timeAft - timeBef));
            if (showBoard) {
                System.out.println(b);
            }
            if (i % 5 == 0) {
                System.out.print(" .");
            }

        }
        System.out.println();
        for (int i : dataGen.keySet()) {
            System.out.println("For " + i + " % of obstacle it took "
                    + dataGen.get(i) + "ms to generate on a " + b.getHeight()
                    + "/" + b.getWidth() + " board ("
                    + (b.getHeight() * b.getWidth()) + " cells !!)");
        }
        sc.close();

    }

    /**
     * debug command.
     *
     * @param args
     *            input stream parameters
     */
    public static void main(String[] args) {
        Board b = new Board(5, 8);
        Scanner sc = new Scanner(System.in);
        String input = "";
        Robot top = null;
        Coordinates topCop = new Coordinates(0, 0);

        while (top == null) {
            System.out.println("Class to test : ");
            input = sc.nextLine().toLowerCase();
            switch (input) {
            case "tank":
                top = new Tank(1, topCop, b);
                break;
            case "piegeur":
            case "scavenger":
                top = new Scavenger(1, topCop, b);
                break;
            case "tireur":
            case "shooter":
                top = new Shooter(1, topCop, b);
                break;
            case "gen":
                testGen();
                System.exit(0);
            default:
                break;
            }
        }

        // System.out.println(b);
        // System.out.println(b.outGrind(1));
        Coordinates tanCop = new Coordinates(2, 2);
        Coordinates otherCop = new Coordinates(7, 0);
        // Coordinates lopCop = new Coordinates(1, 1);

        Robot tan = new Tank(1, tanCop, b);
        // (1, topCop, b);
        Robot other = new Shooter(2, otherCop, b);
        // Robot lop = new Scavenger(1, lopCop, b); // tan.setEnergy(1);
        b.setRobot(tan, tanCop);
        b.setRobot(top, topCop);
        b.setRobot(other, otherCop);
        // b.setRobot(lop, lopCop);

        // Coordinates min = new Coordinates(0, 2);
        Coordinates min2 = new Coordinates(2, 1);
        // b.generate(5, true);
        // b.setMine(min, 2);
        b.setMine(min2, 2);
        b.setMine(new Coordinates(0, 6), 1);
        // System.out.println(b.outGrind(1));
        // System.out.println(Constant.MOVE_LIGHT_ROBOT);
        Action ac = null;
        String[] splitedInput = null;
        while (!input.equals("stop null")) {
            if (top.isInBase()) {
                top.runBaseAction();
            }
            System.out.println(' ' + b.outGrindPlusLegend(-1).replaceAll("\n",
                    "\n "));

            input = sc.nextLine() + " null";

            splitedInput = input.split(" ");
            switch (splitedInput[0]) {
            case "up":
                switch (splitedInput[1]) {
                case "right":
                    ac = new Move(top, Constant.DIAG_UP_RIGHT);
                    break;
                case "left":
                    ac = new Move(top, Constant.DIAG_UP_LEFT);
                    break;
                default:
                    ac = new Move(top, Constant.UP);
                    break;
                }
                break;

            case "down":
                switch (splitedInput[1]) {
                case "right":
                    ac = new Move(top, Constant.DIAG_DOWN_RIGHT);
                    break;
                case "left":
                    ac = new Move(top, Constant.DIAG_DOWN_LEFT);
                    break;
                default:
                    ac = new Move(top, Constant.DOWN);
                    break;
                }
                break;

            case "right":
                ac = new Move(top, Constant.RIGHT);
                break;

            case "left":
                ac = new Move(top, Constant.LEFT);
                break;
            case "shoot":
                switch (splitedInput[1]) {
                case "up":
                    ac = new Attack(top, Constant.UP);
                    break;

                case "down":
                    ac = new Attack(top, Constant.DOWN);
                    break;

                case "right":
                    ac = new Attack(top, Constant.RIGHT);
                    break;

                case "left":
                    ac = new Attack(top, Constant.LEFT);
                    break;
                default:
                    break;
                }
                break;
            default:
                break;
            }
            // System.out.println("before : " + top.getMoving());
            if (ac != null) {
                ac.act();
                ac = null;
            }
            // System.out.println("After : " + top);
            // System.out.println(top.getMoving());

        }
        // System.out.println(Constant.MOVE_LIGHT_ROBOT);
        sc.close();
        /*
         * f //System.out.println(b.outGrind(-1)); String tmp = b.outGrind(-1);
         * String[] splied = tmp.split("\n"); for (String str : splited) {
         * System.out.println(str + " lol "); }
         */

    }
}
