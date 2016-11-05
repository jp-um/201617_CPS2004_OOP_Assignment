/*
 * CPS2004 - OOP, Department of CS, University of Malta.
 * (C)2016/7 Jean-Paul Ebejer
 * All rights reserved.
 */
package edu.um.cps2004.task1.engine;

import edu.um.cps2004.task1.catalog.Mark;
import edu.um.cps2004.task1.player.TTTPlayer;
import edu.um.cps2004.task1.robot.TTTRobot;

/**
 * The Tic Tac Toe war engine.
 * 
 * Note that this class does <b>not</b> determine the intelligence of the robot
 * players but simply implements the 'rules of engagement' (i.e. the game).
 *
 * @author <a href="mailto:jean.p.ebejer@um.edu.mt">JP</a>
 * @version 1.0
 */
public abstract class TTTWarEngine {

    /**
     * The two players, one will be playing X and the other will be playing O.
     * 
     * Eventually it might be a good idea to have a list of players, instead of
     * hardcoding two (this way we can pair teams up etc.)
     */
    protected final TTTPlayer playerX, playerO;

    /**
     * The board we are going to play upon
     */
    protected final GameBoard board = new GameBoard();

    /**
     * The constructor of the robots war engine.
     * 
     * @param robotX
     *            An instance of the first robot, may not be null. This robot
     *            will play with X.
     * @param robotO
     *            An instance of the second robot, may not be null This robot
     *            will play with O.
     */
    public TTTWarEngine(final TTTRobot robotX, final TTTRobot robotO) {
        super(); // implied
        // do some validation
        if (robotX == null) {
            throw new IllegalArgumentException("robotX may not be null");
        }
        if (robotO == null) {
            throw new IllegalArgumentException("robotO may not be null");
        }

        // check that the names are not null either,
        // so that we dont fail later
        if (robotX.getRobotMasterName() == null) {
            throw new IllegalArgumentException(robotX + " master name may not be null");
        }
        if (robotO.getRobotMasterName() == null) {
            throw new IllegalArgumentException(robotO + " master name may not be null");
        }

        // create a player out of the first robot
        this.playerX = new TTTPlayer(Mark.X, robotX);
        // create a player out of the second robot
        this.playerO = new TTTPlayer(Mark.O, robotO);
    }

    /**
     * Plays the game (gives a turn each to the players to play) Should set a
     * winner (at the end of the encounter if non draw game).
     */
    public abstract void play();

}
