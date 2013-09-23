/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version.beta;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.common.Location;
import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveValidator;
import strategy.game.version.StrategyBoard;
import strategy.game.version.StrategyMoveValidator;

/**
 * Provides a move validator strategy for the BetaStrategy implementation
 * @author cbotaish, drob
 * @version Sept 23, 2013
 */
public class BetaStrategyMoveValidator extends BaseStrategyMoveValidator
{
	/**
	 * @see StrategyMoveValidator#checkMoveValidity(StrategyBoard, 
	 * PlayerColor, PieceType, Location, Location)
	 */
	@Override
	public void checkMoveValidity(StrategyBoard gameBoard, 
			PlayerColor currentTurn, PieceType movePiece, Location moveFromLocation, 
			Location moveToLocation) throws StrategyException
	{
		super.checkMoveValidity(gameBoard, currentTurn, movePiece, moveFromLocation, moveToLocation);
	}

	@Override
	protected void setupMoveConstraints()
	{
		super.boardHeight = 6;
		super.boardWidth = 6;
		super.maxMoveDistance = 2;	
	}

}
