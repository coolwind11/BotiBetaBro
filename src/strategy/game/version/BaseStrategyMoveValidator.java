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

import java.util.HashMap;
import java.util.Map;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.PieceType;

/**
 * Provides a base abstract class that can be extended to create strategy move validators.
 * @author cbotaish, drob
 * @version 9/23/13
 */
public abstract class BaseStrategyMoveValidator implements
		StrategyMoveValidator
{
	protected Map<PieceType, Integer> validMoveDistances = new HashMap<PieceType, Integer>();
	
	protected int boardHeight = 0;
	protected int boardWidth = 0;
	
	/**
	 * Creates a new base strategy move validator
	 */
	protected BaseStrategyMoveValidator()
	{
		setupMoveConstraints();
	}
	
	/**
	 * Sets up the move constraints for the game.
	 */
	protected abstract void setupMoveConstraints();
	

	/**
	 * @see StrategyMoveValidator#checkMoveValidity(StrategyBoard, 
	 * PlayerColor, PieceType, Location, Location)
	 */
	@Override
	public void checkMoveValidity(StrategyBoard gameBoard,
			PlayerColor currentTurn, PieceType movePiece,
			Location moveFromLocation, Location moveToLocation)
			throws StrategyException
	{
		//if the from coordinate is out of bounds, the move is invalid
				if (moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) >= boardWidth
						|| moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) < 0
						|| moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) >= boardHeight
						|| moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) < 0)
				{
					throw new StrategyException("From coordinate out of board bounds");
				}

				//if the to coordinate is out of bounds...the move; is invalid
				if (moveToLocation.getCoordinate(Coordinate.X_COORDINATE) >= boardWidth
						|| moveToLocation.getCoordinate(Coordinate.X_COORDINATE) < 0
						|| moveToLocation.getCoordinate(Coordinate.Y_COORDINATE) >= boardHeight
						|| moveToLocation.getCoordinate(Coordinate.Y_COORDINATE) < 0)
				{
					throw new StrategyException("To coordinate out of board bounds");
				}

				//if there is no piece at the supplied location, the move is invalid
				if (gameBoard.getPieceAt(moveFromLocation) == null)
				{
					throw new StrategyException("No piece to move at location");
				}
				
				//if the piece at the supplied location isnt the right type, the move is invalid
				if(gameBoard.getPieceAt(moveFromLocation).getType() != movePiece)
				{
					throw new StrategyException("Incorrect piece at the given location");
				}

				//if you try to move your opponents piece, the move is invalid
				if (gameBoard.getPieceAt(moveFromLocation).getOwner() != currentTurn)
				{
					throw new StrategyException("Not correct player turn");
				}

				//if the piece is the flag, ya can't move that.
				if (gameBoard.getPieceAt(moveFromLocation).getType() == PieceType.FLAG)
				{
					throw new StrategyException("Cannot move the flag");
				}

				//if you move onto your own piece, your gonna have a bad time
				if (gameBoard.getPieceAt(moveToLocation) != null && gameBoard.getPieceAt(moveToLocation).getOwner() == currentTurn)
				{
					throw new StrategyException("Cant attack own piece");
				}

				//if you try to move diagonally, the move is invalid
				final boolean inSameRow = moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) == moveToLocation.getCoordinate(Coordinate.X_COORDINATE);
				final boolean inSameCol = moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) == moveToLocation.getCoordinate(Coordinate.Y_COORDINATE);
				
				if(!(inSameRow || inSameCol))
				{
					throw new StrategyException("Cannot move diagonally");
				}
				
				//if you try to move too far, THE MOVE IS INVALID
				int validMoveDistance = validMoveDistances.get(movePiece);
				if(moveFromLocation.distanceTo(moveToLocation) >= validMoveDistance && validMoveDistance >= 0)
				{
					throw new StrategyException("Moved too far");
				}
	}

}
