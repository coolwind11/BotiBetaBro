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

import strategy.common.PlayerColor;

/**
 * This interface handles Strategy moves and the battles that can occur from them
 * @author drob, cbotaish
 *
 */
public interface StrategyMoveResolver {
	
	/**
	 * Resolve a move between a piece and the piece that occupies the location in question.
	 * @param pieceMoving the piece that is moving
	 * @param currentTurn the player whose turn it currently is.
	 * @param fromLocation the location where the piece is moving from
	 * @param toLocation the location where the piece is moving to
	 * @param gameBoard the game board for reference
	 * @return the move result
	 */
	MoveResult resolveMove(StrategyBoard gameBoard, PlayerColor currentTurn,
			PieceType pieceMoving, Location fromLocation, Location toLocation);
}
