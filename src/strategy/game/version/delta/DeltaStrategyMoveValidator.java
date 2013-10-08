/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version.delta;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.Location2D;
import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveValidator;
import strategy.game.version.PieceMoveEntry;
import strategy.game.version.StrategyBoard;
import strategy.game.version.StrategyMoveRememberator;

/**
 * Provides a move validator strategy for the Delta implementation of Strategy
 * @author cbotaish, drob
 * @version Sept 22, 2013
 */
public class DeltaStrategyMoveValidator extends BaseStrategyMoveValidator
{	
	private final StrategyMoveRememberator moveRememberator;

	public DeltaStrategyMoveValidator()
	{
		moveRememberator = new StrategyMoveRememberator(4);
	}
	
	@Override
	protected void setupMoveConstraints()
	{
		boardHeight = 10;
		boardWidth = 10;
		validMoveDistances.put(PieceType.BOMB, 0);
		validMoveDistances.put(PieceType.CAPTAIN, 1);
		validMoveDistances.put(PieceType.COLONEL, 1);
		validMoveDistances.put(PieceType.FLAG, 0);
		validMoveDistances.put(PieceType.GENERAL, 1);
		validMoveDistances.put(PieceType.CHOKE_POINT, 0);
		validMoveDistances.put(PieceType.LIEUTENANT, 1);
		validMoveDistances.put(PieceType.MAJOR, 1);
		validMoveDistances.put(PieceType.MARSHAL, 1);
		validMoveDistances.put(PieceType.MINER, 1);
		validMoveDistances.put(PieceType.SCOUT, -1);
		validMoveDistances.put(PieceType.SERGEANT, 1);
		validMoveDistances.put(PieceType.SPY, 1);
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
		
		if(movePiece == PieceType.BOMB)
		{
			throw new StrategyException("Cannot move a bomb");
		}
		
		//Check the base functionality.
		super.checkMoveValidity(gameBoard, currentTurn, movePiece, moveFromLocation, moveToLocation);
		
		//Add to the rememberator
		final PieceMoveEntry entry = new PieceMoveEntry(gameBoard.getPieceAt(moveFromLocation), moveFromLocation, moveToLocation);
		
		if(moveRememberator.isMoveInList(entry))
		{
			throw new StrategyException("Broke move repitition rule");
		}
		
		if(movePiece == PieceType.SCOUT)
		{
			
			
			int distance = moveFromLocation.distanceTo(moveToLocation);
			
			if (gameBoard.getPieceAt(moveToLocation) != null && distance > 1) {
				throw new StrategyException("Scout cannot move and strike on the same turn");
			}
			
			boolean verticalMove = moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) == moveToLocation.getCoordinate(Coordinate.X_COORDINATE);
			boolean forward = verticalMove ? moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) < moveToLocation.getCoordinate(Coordinate.Y_COORDINATE)
					: moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) < moveToLocation.getCoordinate(Coordinate.X_COORDINATE);
			//Check every spot to the to location.
			
			Location2D nextCoordinate;
			int startX = moveToLocation.getCoordinate(Coordinate.X_COORDINATE);
			int startY = moveToLocation.getCoordinate(Coordinate.Y_COORDINATE);
			
			int step = forward ? 1 : -1;
			int start = verticalMove ? startY : startX; //start at the next spot
			int stop = verticalMove ? startY + step*distance : startX + step*distance;
			for(int i = start; i != stop ; i = i + step)
			{
				if (!forward) {
					nextCoordinate = verticalMove ? new Location2D(startX, i)
					: new Location2D(i, startY);
				} else {
					nextCoordinate = verticalMove ? new Location2D(startX, i)
					: new Location2D(i, startY);
				}

				
				if (gameBoard.getPieceAt(nextCoordinate) != null) {
					throw new StrategyException("Cannot move scout through another piece");
				}
			}
		}
		
		moveRememberator.addMove(entry);
	}
}
