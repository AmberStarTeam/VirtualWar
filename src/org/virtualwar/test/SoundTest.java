package org.virtualwar.test;
import org.virtualwar.util.sound.ThreadSoundRun;

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
 * The Class SoundTest.
 *
 * @author Nicolas Beaussart
 */
public final class SoundTest {

    /**
     * empty constructor.
     */
    private SoundTest() {
    }

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(String[] args) {
        ThreadSoundRun tmp;

        for (String str : ("boom.wav\ndeathOfRobots.wav\n"
                + "moveTank.wav\nrunning.wav" + "\nshotShooter.wav\n"
                + "shotTank.wav").split("\n")) {
            System.out.println(str);
            tmp = new ThreadSoundRun("sounds/" + str);
            tmp.start();
            while (tmp.isAlive()) {
                System.out.println("Running");
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        tmp = new ThreadSoundRun("sounds/running.wav", 1000);
        tmp.start();

    }

}
