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
 * This interface handles all the validation logic for Strategy game moves
 * @author drob,cbotaish
 *
 */
public interface StrategyMoveValidator {

	/**
	 * Validates a StrategyGame move
	 * @param movePiece the piece to be moved
	 * @param moveLocation the location to move to
	 * @param gameBoard checked against by isValidMove
	 * @return
	 */
	boolean isValidMove(StrategyBoard gameBoard, Piece movePiece, Location moveLocation);
}
