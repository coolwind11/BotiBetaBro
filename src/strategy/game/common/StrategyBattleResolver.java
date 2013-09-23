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
 * This interface handles Strategy battles that occur when a piece moves to an occupied location
 * @author drob, cbotaish
 *
 */
public interface StrategyBattleResolver {
	
	/**
	 * Resolve a battle between a piece and the piece that occupies the location in question.
	 * @param battleInitiator the piece moving into
	 * @param battleLocation the location where the battle will occur
	 * @param gameBoard the game board for reference
	 * @return the move result
	 */
	MoveResult resolveBattle(StrategyBoard gameBoard, Piece battleInitiator, Location battleLocation);
}
