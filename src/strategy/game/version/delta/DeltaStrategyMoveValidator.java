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

	/**
	 * Creates a new DeltaStrategyMoveValidator
	 */
	public DeltaStrategyMoveValidator()
	{
		moveRememberator = new StrategyMoveRememberator(4);
	}
	
	/**
	 * Sets up the move constraints for the game.
	 */
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

	/**
	 * @see strategy.game.version.StrategyMoveValidator#checkMoveValidity(StrategyBoard, PlayerColor, PieceType, Location, Location)
	 */
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
			checkScoutValidity(gameBoard, currentTurn, movePiece, moveFromLocation, moveToLocation);
		}
		
		moveRememberator.addMove(entry);
	}
	
	/**
	 * Checks if the move is valid for a scout piece type
	 * @param gameBoard the current game board
	 * @param currentTurn the player whose turn it is
	 * @param movePiece the piece that is being moved
	 * @param moveFromLocation the move from location
	 * @param moveToLocation the move to location
	 * @throws StrategyException if the move is not valid.
	 */
	private void checkScoutValidity(StrategyBoard gameBoard, PlayerColor currentTurn, PieceType movePiece, 
			Location moveFromLocation, Location moveToLocation) throws StrategyException
	{
		final int distance = moveFromLocation.distanceTo(moveToLocation);
		
		if (gameBoard.getPieceAt(moveToLocation) != null && distance > 1) {
			throw new StrategyException("Scout cannot move and strike on the same turn");
		}
		
		final boolean verticalMove = 
				moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) == moveToLocation.getCoordinate(Coordinate.X_COORDINATE);
		final boolean forward = verticalMove ? moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) 
				< moveToLocation.getCoordinate(Coordinate.Y_COORDINATE) : 
					moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) < moveToLocation.getCoordinate(Coordinate.X_COORDINATE);
		
		//Check every spot to the to location.
		Location2D nextCoordinate;
		final int startX = moveFromLocation.getCoordinate(Coordinate.X_COORDINATE);
		final int startY = moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE);
		
		final int step = forward ? 1 : -1;
		final int start = verticalMove ? startY : startX; //start at the next spot
		final int stop = verticalMove ? startY + step * distance : startX + step * distance;
		for(int i = start + step; i != stop ; i += step)
		{
			
			nextCoordinate = verticalMove ? new Location2D(startX, i) : new Location2D(i, startY);
				
			if (gameBoard.getPieceAt(nextCoordinate) != null) {
				throw new StrategyException("Cannot move scout through another piece");
			}
		}
	}
}
