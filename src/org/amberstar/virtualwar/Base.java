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

import java.util.List;

/**
 * The Base Class who stock multiple robots.
 *
 * @author amberStar
 */
public class Base extends Cell {

    /**
     * public constructor.
     *
     * @param coordinates
     *            coordinates of Base
     * @param baseTeam
     *            Owner of base
     */
    public Base(Coordinates coordinates, int baseTeam) {
        super(coordinates, baseTeam);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#getContents()
     */
    @Override
    public List<Robot> getContents() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#moveOn(org.amberstar.virtualwar.Robot)
     */
    @Override
    public boolean moveOn(Robot robot) {
        // TODO Auto-generated method stub
        return true;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#addMine(int)
     */
    @Override
    public boolean addMine(int team) {
        // TODO Auto-generated method stub
        return false;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#clearBox()
     */
    @Override
    public void clearBox() {
        // TODO Auto-generated method stub
    }

}
