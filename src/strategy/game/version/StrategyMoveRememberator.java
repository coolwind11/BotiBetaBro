/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version;

/**
 * Holds information on the history of moves made in a Strategy game.
 * Used to implement Move repitition rule.
 * @author cbotaish, drob
 * @version Sept 22, 2013
 */
public class StrategyMoveRememberator {
	
	private final int moveWindow; //how many moves will be remembered
	private int index;
	private final PieceMoveEntry[] buffer;
	
	/**
	 * Creates a new StrategyMoveRememberator with the given number of
	 * moves that will be saved.
	 * @param moveWindow the number of moves for the rememberator to remember.
	 */
	public StrategyMoveRememberator(int moveWindow) {
		this.moveWindow = moveWindow;
		buffer = new PieceMoveEntry[moveWindow];
		index = 0;
	}
	
	/**
	 * Adds the given move to the move rememberator
	 * @param move the move to add to the rememberator.
	 */
	public void addMove(PieceMoveEntry move) {
		buffer[index] = move;
		
		if(index >= moveWindow - 1) {
			index = 0;
		} else {
			index++;
		}
	}
	
	/**
	 * Checks to see if the given move is in the rememberator
	 * @param move the move to check
	 * @return whether or not the given move is present in the rememberator.
	 */
	public boolean isMoveInList(PieceMoveEntry move) {
		for(int i = 0; i < buffer.length; i++)
		{
			if(buffer[i] == null)
			{
				continue;
			}
			
			if(buffer[i].equals(move))
			{
				return true;
			}
		}
		
		return false;
	}
}
