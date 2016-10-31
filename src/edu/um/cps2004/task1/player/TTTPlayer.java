/*
 * CPS2004 - OOP, Department of CS, University of Malta.
 * (C)2016/7 Jean-Paul Ebejer
 * All rights reserved.
 */
package edu.um.cps2004.task1.player;

import edu.um.cps2004.task1.catalog.Mark;
import edu.um.cps2004.task1.robot.TTTRobot;

/**
 * The player of the TTT game.
 * 
 * The player has got a robot responsible for
 * playing in the correct position and a mark
 * (either X or O) with which to play.
 *
 * @author <a href="mailto:jean.p.ebejer@um.edu.mt">JP</a>
 * @version 1.0
 */
public final class TTTPlayer {
	
	/**
	 * The sign of the player either X or O
	 */
	private final Mark mark;
	
	/**
	 * The robot playing TTT.  Contains the intelligence
	 * of the creator - as well as playing algorithm.
	 */
	private final TTTRobot robot;
	
	/**
	 * Sets the sign and the robot for this player.  The robot
	 * master name may not be null.
	 * 
	 * @param mark The sign with which this player is playing, may not be null
	 * @param robot The robot which is playing for this payer, may not be null
	 */
	public TTTPlayer(final Mark mark, final TTTRobot robot) {
		if (mark == null) {
			throw new IllegalArgumentException("mark cannot be null");
		}
		if (robot == null) {
			throw new IllegalArgumentException("robot cannot be null");
		}
		if (robot.getRobotMasterName() == null) {
			throw new IllegalArgumentException("Robot master name may not be null: " + robot);
		}
		
		this.mark = mark;
		this.robot = robot;
	}

	/**
	 * Gets the robot
	 * @return the robot
	 */
	public TTTRobot getRobot() {
		return robot;
	}

	/**
	 * Gets the playing mark for the player 
	 * @return The player's mark (either X or O), never null.
	 */
	public Mark getMark() {
		return mark;
	}

	/**
	 * Gets the playing mark of your opponent.  Assumes a two-player game (breaks otherwise).
	 * @return The mark of the player's opponent (either X or O), never null.
	 */
	public Mark getMarkOfOpponent() {
	    return Mark.X.equals(mark) ? Mark.O : Mark.X;  
	}
	
	/**
	 * Hashcode ovverride
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((robot == null) ? 0 : robot.hashCode());
		result = PRIME * result + ((mark == null) ? 0 : mark.hashCode());
		return result;
	}

	/**
	 * equals override - we want to compare similar players
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TTTPlayer other = (TTTPlayer) obj;
		if (robot == null) {
			if (other.robot != null)
				return false;
		} else if (!robot.equals(other.robot))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		return true;
	}

	/**
	 * toString for informational purposes only
	 */
	@Override
	public String toString() {
		return robot.getRobotMasterName() + " [" + mark.name() + "]";
	}
	
}
