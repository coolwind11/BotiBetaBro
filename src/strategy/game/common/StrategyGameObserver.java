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

import java.util.Collection;

import strategy.common.StrategyException;

/**
 * This interface provides an interface for an observer of a strategy game
 * @author gpollice
 * @version Aug 31, 2013
 */
public interface StrategyGameObserver
{
	/**
	 * Alerts the observer that the game started
	 * @param redConfiguration the initial red config for the game
	 * @param blueConfiguration the initial blue config for the game.
	 */
	void gameStart(Collection<PieceLocationDescriptor> redConfiguration, Collection<PieceLocationDescriptor> blueConfiguration);
	
	/**
	 * Alerts the observer that a move occurred
	 * @param piece the piece that was moved 
	 * @param from the location it was moved from
	 * @param to the location it was moved to
	 * @param result the result of the move.  This will be null if an exception occurred.
	 * @param fault if there was an exception that occurred, the exception will be passed here. 
	 * Otherwise, this will be null.
	 */
	void moveHappened(PieceType piece, Location from, Location to, MoveResult result, StrategyException fault);
	
}
