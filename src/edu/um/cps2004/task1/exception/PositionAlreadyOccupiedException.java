/*
 * CPS2004 - OOP, Department of CS, University of Malta.
 * (C)2016/7 Jean-Paul Ebejer
 * All rights reserved.
 */
package edu.um.cps2004.task1.exception;

/**
 * Exception thrown when trying to play in a position which is already occupied.
 *
 * @author <a href="mailto:jean.p.ebejer@um.edu.mt">JP</a>
 * @version 1.0
 */
public class PositionAlreadyOccupiedException extends Exception {

	/* Serial version UID */
	private static final long serialVersionUID = -650219220004771191L;

	/**
	 * Constructor - we always want to pas a meaningful message 
	 * @param message The message
	 */
	public PositionAlreadyOccupiedException(final String message) {
		super(message);
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
