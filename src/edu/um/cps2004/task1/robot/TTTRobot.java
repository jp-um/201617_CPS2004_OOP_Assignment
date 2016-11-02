/*
 * CPS2004 - OOP, Department of CS, University of Malta.
 * (C)2016/7 Jean-Paul Ebejer
 * All rights reserved.
 */
package edu.um.cps2004.task1.robot;

import edu.um.cps2004.task1.catalog.Mark;
import edu.um.cps2004.task1.engine.GameBoard;

/**
 * This interface defines the behaviour of a Tic Tac Toe Robot.
 * 
 * All Tic Tac Toe players should implement this class, since
 * it is the way the game engine 'interacts' with the players  
 * 
 * @author <a href="mailto:jean.p.ebejer@um.edu.mt">JP</a>
 * @version 1.0
 */
public interface TTTRobot {
	
	/**
	 * Simply returns a String with the name of
	 * the Robot creator (e.g. "James Gosling").
	 * 
	 * This is used mostly for display purposes and
	 * to give a friendly name to the Robot (other than
	 * a class name - yuck!!)
	 * 
	 * @return The name of the robot master, never null
	 */
	String getRobotMasterName();
	
	/**
	 * Defines the actual playing logic for the Robot.
	 * 
	 * @param board The current game board state - empty at first.  <b>Never Null</b>
	 * @param turn The players turn, either X or O should play on the given board. <b>Never Null</b>  
	 * @return The position at which to play the <i>turn</i> mark (2nd parameter above).  Must be
	 * an integer from 0 to <i>board.getBoardSize() - 1</i>.  If integer is outside this
	 * range the robot loses.  Also if the position is already taken, the robot loses. 
	 */
	int play(GameBoard board, Mark turn);

}
