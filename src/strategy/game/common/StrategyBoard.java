/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.common;

/**
 * Provides an interface to a strategy board.
 * @author Chris Botaish, Dan Robertson
 * @version Sep 9, 2013
 */
public interface StrategyBoard
{
	/**
	 * Returns the piece at the given location
	 * @param location the location to check for a piece
	 * @return the piece at the given location, or null if there is no piece at that location.
	 */
	Piece getPieceAt(Location location);
	
	/**
	 * Moves the piece at location from to the location to.
	 * @param from the location to move the piece from
	 * @param to the location to move the piece to.
	 */
	void movePiece(Location from, Location to);
	
	/**
	 * Removes the piece at the given location.
	 * @param fromLocation the location to remove the piece from.
	 */
	void removePiece(Location fromLocation);
	
	/**
	 * Resets the board to its initial state.
	 */
	void resetBoard();
	
	/**
	 * @return whether or not the initial state of the board is valid.
	 */
	boolean hasValidInitialBoardSetup();
	
	
}

