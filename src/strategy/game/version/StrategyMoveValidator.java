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

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.common.Location;
import strategy.game.common.PieceType;

/**
 * This interface handles all the validation logic for Strategy game moves
 * @author drob,cbotaish
 * @version Sept 22, 2013
 */
public interface StrategyMoveValidator {

	/**
	 * Validates a StrategyGame move
	 * @param movePiece the piece to be moved
	 * @param currentTurn the player whose turn it is.
	 * @param moveFromLocation the location to move from
	 * @param moveToLocation the location to move to
	 * @param gameBoard checked against by isValidMove
	 * @throws StrategyException if the move is invalid
	 */
	void checkMoveValidity(StrategyBoard gameBoard, PlayerColor currentTurn, PieceType movePiece, 
			Location moveFromLocation, Location moveToLocation) throws StrategyException;
}
