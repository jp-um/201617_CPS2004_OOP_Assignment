/*
 * CPS2004 - OOP, Department of CS, University of Malta.
 * (C)2016/7 Jean-Paul Ebejer
 * All rights reserved.
 */
package edu.um.cps2004.task1.exception;

/**
 * The exception which shows that a playing position is out of range.
 *
 * @author <a href="mailto:jean.p.ebejer@um.edu.mt">JP</a>
 * @version 1.0
 */
public class PositionOutOfRangeException extends Exception {

	/* The serial version UID */
	private static final long serialVersionUID = -2124668628305192129L;

	/**
	 * The constructor - we always want to pass a meaningful message
	 * @param message The message
	 */
	public PositionOutOfRangeException(int pos, int boardSize) {
		super("Position: " + pos + " does not exist on board.  Board size: " + boardSize);
	}

	/**
	 * Converts this to a String, used
	 * for informational purposes 
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + " : " + getMessage();
	}

}
