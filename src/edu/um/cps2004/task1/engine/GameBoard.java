/*
 * CPS2004 - OOP, Department of CS, University of Malta.
 * (C)2016/7 Jean-Paul Ebejer
 * All rights reserved.
 */
package edu.um.cps2004.task1.engine;

import java.util.ArrayList;
import java.util.List;

import edu.um.cps2004.task1.catalog.Mark;
import edu.um.cps2004.task1.exception.PositionAlreadyOccupiedException;
import edu.um.cps2004.task1.exception.PositionOutOfRangeException;

/**
 * The actual Tic-Tac-Toe Gameboard.
 * 
 * This class represents the current game state. Moreover it does so by keeping
 * a one dimensional array as follows (kiss, principle - Keep It Stupid Simple).
 * 
 * The board is a 3x3 matrix with positions defined as
 * 
 * <pre>
 *   0 | 1 | 2
 *  -----------
 *   3 | 4 | 5 
 *  -----------
 *   6 | 7 | 8
 * </pre>
 * 
 * Each position is initially empty and may be filled with an X or O.
 * 
 * We now keep the number of moves played - as a cache for easier calculation of
 * <code>isFull</code>, <code>isEmpty</code> methods.
 * 
 * @author <a href="mailto:jean.p.ebejer@um.edu.mt">JP</a>
 * @version 1.0
 */
public final class GameBoard {

    /**
     * The board size (max number of X and Os which we can fit on the board)
     */
    private final int BOARD_SIZE = 9;

    /**
     * The array holding the actual board state. We will populate this as the
     * game goes by.
     */
    private final Mark[] board = new Mark[BOARD_SIZE];

    /**
     * The number of moves played on this board. Never negative, =< BOARD_SIZE
     */
    private int movesPlayed = 0;

    /**
     * A list which contains all empty positions on the board. Updated upon
     * playing at a particular position. We keep an attribute so we do not have
     * to iterate (linear time) over the board each time to check for empty
     * positions. Max size is BOARD SIZE
     */
    private final List<Integer> emptyPositions = new ArrayList<Integer>(BOARD_SIZE); // size is max of 9...

    /**
     * Constructor which initialises the empty positions list to all positions
     * on the board
     */
    public GameBoard() {
        // fill empty positions with ALL positions available
        // as all of them are empty (at the beginning)
        for (int i = 0; i < BOARD_SIZE; i++) {
            emptyPositions.add(i);
        }
    }

    /**
     * Gets the board state (the array containing Xs and 0s). Note that this
     * method actually returns a copy of the actual array so none of the players
     * have a live reference to the actual board array (which they can change,
     * hack and cheat with). Hence this method is a bit heavy and should be
     * called sparingly.
     * 
     * @return The single dimensional array representing the board state. Never
     *         null.
     */
    public Mark[] getBoardState() {
        // return a copy of the board! as some "clever" student may try
        // to change the original board ...
        return board.clone();
    }

    /**
     * Checks if the board is full.
     * 
     * @return true if the board is full, false otherwise
     */
    public boolean isFull() {
        return (movesPlayed == BOARD_SIZE);
    }

    /**
     * Checks if the board is empty, simply by checking the number of moves
     * played on this board.
     * 
     * @return true if the board is full, false otherwise
     */
    public boolean isEmpty() {
        return (movesPlayed == 0);
    }

    /**
     * Gets the board size (this may actually be determined by calling <code>length</code> on
     * the board array, but for convenience we offer this method).
     * 
     * @return An <code>int</code> representing the board size (the one dimensional
     *         array). Positive, and always greater than 0.
     */
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    /**
     * Checks if a position is available.
     * 
     * Note that this method will throw an exception if the position is out
     * of range. That is < 0 and >= BOARD_SIZE (currently at 9).
     * 
     * @param position
     *            The position to check for vacancy
     * @return true if occupied, false otherwise
     * @throws PositionOutOfRangeException
     */
    public boolean isOccupied(final int position) throws PositionOutOfRangeException {
        // check range... avoids IndexOutOfBounds exception later on
        if ((position < 0) || (position >= BOARD_SIZE)) {
            throw new PositionOutOfRangeException(position, BOARD_SIZE);
        }
        // check vacancy
        return (board[position] != null);
    }

    /**
     * This available method check for the validity of a move.
     * 
     * Particularly it checks if the tentative position is in a valid range
     * 0..(BOARD_SIZE-1).
     * 
     * Also it checks if there already is a mark in the tentative position.
     *
     * This method should be called by all players prior to returning their
     * playing position. The engine will disqualify any robot which does not
     * comply to this validation.
     * 
     * @param tentativePosition
     *            the tentative position where to play
     * @throws PositionOutOfRangeException
     *             If the tentative exception is out of range < 0 and >=
     *             BOARD_SIZE (9)
     * @throws PositionAlreadyOccupiedException
     *             If the tentative position is already occupied.
     */
    public void validatePlayTentative(final int tentativePosition)
            throws PositionOutOfRangeException, PositionAlreadyOccupiedException {
        // check range
        if ((tentativePosition < 0) || (tentativePosition >= BOARD_SIZE)) {
            throw new PositionOutOfRangeException(tentativePosition, BOARD_SIZE);
        }
        // check position vacancy
        if (isOccupied(tentativePosition)) {
            throw new PositionAlreadyOccupiedException(
                    "Position " + tentativePosition + " is already occupied by " + board[tentativePosition]);
        }

    }

    /**
     * Displays the game board, with some funky characters. Writes to
     * console (using <code>System.out</code>).
     */
    public void display() {
        // simply print the toString version of the board
        System.out.println(toString());
    }

    /**
     * Proper toString() method which is mostly used for display purposes.
     */
    @Override
    public String toString() {
        // not the smartest way how to do this, but we want something fast...
        // TODO make dynamic according to size
        final StringBuilder buffer = new StringBuilder();
        buffer.append('\n'); // new line
        buffer.append(' '); // use char, faster than creating a String etc.
        buffer.append(board[0] == null ? ' ' : board[0].name());
        buffer.append(" \u00B3 ");
        buffer.append(board[1] == null ? ' ' : board[1].name());
        buffer.append(" \u00B3 ");
        buffer.append(board[2] == null ? ' ' : board[2].name());
        buffer.append('\n'); // new line
        buffer.append("\u00C4\u00C4\u00C4\u00C5\u00C4\u00C4\u00C4\u00C5\u00C4\u00C4\u00C4\n");
        buffer.append(' '); // use char, faster than creating a String etc.
        buffer.append(board[3] == null ? ' ' : board[3].name());
        buffer.append(" \u00B3 ");
        buffer.append(board[4] == null ? ' ' : board[4].name());
        buffer.append(" \u00B3 ");
        buffer.append(board[5] == null ? ' ' : board[5].name());
        buffer.append('\n'); // new line
        buffer.append("\u00C4\u00C4\u00C4\u00C5\u00C4\u00C4\u00C4\u00C5\u00C4\u00C4\u00C4\n");
        buffer.append(' '); // use char, faster than creating a String etc.
        buffer.append(board[6] == null ? ' ' : board[6].name());
        buffer.append(" \u00B3 ");
        buffer.append(board[7] == null ? ' ' : board[7].name());
        buffer.append(" \u00B3 ");
        buffer.append(board[8] == null ? ' ' : board[8].name());
        buffer.append('\n'); // new line
        return buffer.toString();
    }

    /**
     * Checks if this board is in a winning position.
     * 
     * There are 8 winning positions in Tic Tac Toe wars.
     * 
     * <pre>
     *   A |   |         | A |         |   | A      A |   |   
     *  -----------   -----------   -----------    -----------
     *   A |   |         | A |         |   | A        | A |   
     *  -----------   -----------   -----------    -----------
     *   A |   |         | A |         |   | A        |   | A
     *
     *  
     *   A | A | A       |   |         |   |          |   | A 
     *  -----------   -----------   -----------    -----------
     *     |   |       A | A | A       |   |          | A |   
     *  -----------   -----------   -----------    -----------
     *     |   |         |   |       A | A | A      A |   |
     * </pre>
     * 
     * TODO should be dynamically sized according to BOARD_SIZE.
     * 
     * @return true if the board is in a winning position - false otherwise
     */
    public boolean isWinningPosition() {
        if ((board[0] != null) && (board[1] != null) && (board[2] != null) && (board[0].equals(board[1]))
                && (board[1].equals(board[2]))) {
            return true;
        }
        if ((board[3] != null) && (board[4] != null) && (board[5] != null) && (board[3].equals(board[4]))
                && (board[4].equals(board[5]))) {
            return true;
        }
        if ((board[6] != null) && (board[7] != null) && (board[8] != null) && (board[6].equals(board[7]))
                && (board[7].equals(board[8]))) {
            return true;
        }
        if ((board[0] != null) && (board[3] != null) && (board[6] != null) && (board[0].equals(board[3]))
                && (board[3].equals(board[6]))) {
            return true;
        }
        if ((board[1] != null) && (board[4] != null) && (board[7] != null) && (board[1].equals(board[4]))
                && (board[4].equals(board[7]))) {
            return true;
        }
        if ((board[2] != null) && (board[5] != null) && (board[8] != null) && (board[2].equals(board[5]))
                && (board[5].equals(board[8]))) {
            return true;
        }
        if ((board[0] != null) && (board[4] != null) && (board[8] != null) && (board[0].equals(board[4]))
                && (board[4].equals(board[8]))) {
            return true;
        }
        if ((board[2] != null) && (board[4] != null) && (board[6] != null) && (board[2].equals(board[4]))
                && (board[4].equals(board[6]))) {
            return true;
        }
        return false;
    }

    /**
     * Plays the position on the board. Before the actual playing, the position
     * is validated by calling the validate method above.
     * 
     * @param positionToPlay
     *            The position to play
     * @param mark
     *            The mark (either X or O) to place at the position
     * @throws PositionOutOfRangeException
     *             If the position to play is out of range
     * @throws PositionAlreadyOccupiedException
     *             If the position is already occupied
     */
    public void playAtPosition(final int positionToPlay, final Mark mark)
            throws PositionOutOfRangeException, PositionAlreadyOccupiedException {
        // check
        validatePlayTentative(positionToPlay);
        // actually play the move on the board
        board[positionToPlay] = mark;
        // increment moves played
        movesPlayed++;
        // remove from empty positions list
        // autoboxing will not work here - since
        // there is a remove which takes an int as param
        emptyPositions.remove(new Integer(positionToPlay));
    }

    /**
     * Gets an integer list of all empty positions on the board.
     * 
     * When board is full this should return a list of size 0. Notice that we
     * give a copy of the original emptyPositions so no one will have a live
     * reference to it.
     * 
     * This method is much more performant than iterating through the collection
     * and adding to a new arraylist
     * 
     * @return The list of empty positions on the board.
     */
    public List<Integer> getEmptyPositions() {
        return new ArrayList<Integer>(emptyPositions);
    }

}
