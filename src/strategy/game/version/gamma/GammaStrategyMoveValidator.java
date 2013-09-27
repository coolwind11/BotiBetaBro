/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version.gamma;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.common.Location;
import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveValidator;
import strategy.game.version.PieceMoveEntry;
import strategy.game.version.StrategyBoard;
import strategy.game.version.StrategyMoveRememberator;

/**
 * Provides a move validator strategy for the GammaStrategy implementation of Strategy
 * @author cbotaish, drob
 * @version Sept 22, 2013
 */
public class GammaStrategyMoveValidator extends BaseStrategyMoveValidator
{	
	private final StrategyMoveRememberator moveRememberator;

	public GammaStrategyMoveValidator()
	{
		moveRememberator = new StrategyMoveRememberator(4);
	}
	
	@Override
	protected void setupMoveConstraints()
	{
		boardHeight = 6;
		boardWidth = 6;
		validMoveDistances.put(PieceType.CAPTAIN, 1);
		validMoveDistances.put(PieceType.COLONEL, 1);
		validMoveDistances.put(PieceType.FLAG, 0);
		validMoveDistances.put(PieceType.CHOKE_POINT, 0);
		validMoveDistances.put(PieceType.LIEUTENANT, 1);
		validMoveDistances.put(PieceType.MARSHAL, 1);
		validMoveDistances.put(PieceType.SERGEANT, 1);
	}

	@Override
	public void checkMoveValidity(StrategyBoard gameBoard, PlayerColor currentTurn, PieceType movePiece, 
			Location moveFromLocation, Location moveToLocation) throws StrategyException
	{
		//Check for choke points
		if(gameBoard.getPieceAt(moveToLocation) != null && 
				gameBoard.getPieceAt(moveToLocation).getType() == PieceType.CHOKE_POINT)
		{
			throw new StrategyException("Cannot move onto a chokepoint!");
		}
		
		//Check the base functionality.
		super.checkMoveValidity(gameBoard, currentTurn, movePiece, moveFromLocation, moveToLocation);
		
		//Add to the rememberator
		final PieceMoveEntry entry = new PieceMoveEntry(gameBoard.getPieceAt(moveFromLocation), moveFromLocation, moveToLocation);
		
		if(moveRememberator.isMoveInList(entry))
		{
			throw new StrategyException("Broke move repitition rule");
		}
		
		moveRememberator.addMove(entry);
	}
}
