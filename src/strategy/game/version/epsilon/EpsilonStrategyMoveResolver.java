/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version.epsilon;

import strategy.common.PlayerColor;
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveResolver;
import strategy.game.version.StrategyBoard;

/**
 * Provides an implementation of the BaseStrategyMoveResolver for the delta strategy
 * @author cbotaish, drob
 * @version 9/23/13
 */
public class EpsilonStrategyMoveResolver extends BaseStrategyMoveResolver {
	
	/**
	 * Sets up the configuration of the base resolver.
	 */
	protected int redFlags = 2;
	protected int blueFlags = 2;
	
	@Override
	protected void setupResolverConfiguration() {
		pieceRank.put(PieceType.MARSHAL, 12);
		pieceRank.put(PieceType.GENERAL, 11);
		pieceRank.put(PieceType.COLONEL, 10);
		pieceRank.put(PieceType.MAJOR, 9);
		pieceRank.put(PieceType.CAPTAIN, 8);
		pieceRank.put(PieceType.LIEUTENANT, 7);
		pieceRank.put(PieceType.FIRST_LIEUTENANT, 7);
		pieceRank.put(PieceType.SERGEANT, 6);
		pieceRank.put(PieceType.MINER, 5);
		pieceRank.put(PieceType.SCOUT, 4);
		pieceRank.put(PieceType.SPY, 3);
		pieceRank.put(PieceType.BOMB, 2);
		pieceRank.put(PieceType.FLAG, 1);
	}
	
	/**
	 * @see strategy.game.version.StrategyMoveResolver#resolveMove(StrategyBoard, PlayerColor, PieceType, Location, Location)
	 */
	public MoveResult resolveMove(StrategyBoard gameBoard, PlayerColor currentTurn,
			PieceType pieceMoving, Location fromLocation, Location toLocation)
	{	
		final MoveResult firstResult;
		final Piece attacker = gameBoard.getPieceAt(fromLocation);
		final Piece defender = gameBoard.getPieceAt(toLocation);
		final MoveResult specialResult = dealWithSpecialCases(gameBoard, currentTurn, pieceMoving, fromLocation, toLocation);
		
		if (specialResult != null){
			firstResult = specialResult;
		} else {
			firstResult = super.resolveMove(gameBoard, currentTurn, pieceMoving, fromLocation, toLocation);
			
			//deal with double flag case
			if ((firstResult.getStatus() == MoveResultStatus.RED_WINS && --blueFlags > 0)
					|| (firstResult.getStatus() == MoveResultStatus.BLUE_WINS && --redFlags > 0)){
				gameBoard.movePiece(fromLocation, toLocation);
				return new MoveResult(MoveResultStatus.FLAG_CAPTURED,new PieceLocationDescriptor(attacker,toLocation));
			}		
		}
		
		//if the First Lieutenant lost, move the winning piece back to the proper spot (it doesnt swap spaces).
		if (pieceMoving == PieceType.FIRST_LIEUTENANT 
				&& firstResult.getBattleWinner() != null 
				&& firstResult.getBattleWinner().getPiece().getOwner() != currentTurn
				&& (fromLocation.distanceTo(toLocation) > 1)) {
			
			gameBoard.movePiece(fromLocation, toLocation); //move winner back
			return new MoveResult(firstResult.getStatus(),new PieceLocationDescriptor(defender,toLocation));
		}
		
		return checkForEndgame(gameBoard, firstResult);
		
	}
	
	
	/**
	 * Handles the end game conditions to see if any player won.
	 * @param gameBoard the current game board
	 * @param firstResult the initial move result
	 * @return the new move result if the game has been won or lost.
	 */
	private MoveResult checkForEndgame(StrategyBoard gameBoard,
			MoveResult firstResult) {
		MoveResultStatus resultStatus = firstResult.getStatus();
		
		//if player has no remaining pieces, or only a flag, they lose
		if (gameBoard.getRemainingPieceCount(PlayerColor.RED) == gameBoard.getRemainingPieceCount(PlayerColor.BLUE) 
			&& gameBoard.getRemainingPieceCount(PlayerColor.RED) <= 1) {
			resultStatus = MoveResultStatus.DRAW; //both have only flag left, so draw
		} else if (gameBoard.getRemainingPieceCount(PlayerColor.RED) <= 1) { //assume 1 remaining piece must be flag
			resultStatus = MoveResultStatus.BLUE_WINS;
		} else if (gameBoard.getRemainingPieceCount(PlayerColor.BLUE) <= 1) {
			resultStatus = MoveResultStatus.RED_WINS;
		}
		
		
		//check if either player has only immovable pieces remaining
		boolean blueHasMoveable = false;
		boolean redHasMoveable = false;
		for (Piece piece : gameBoard.getRemainingPieces(PlayerColor.RED)) {
			if (piece.getType() != PieceType.BOMB || piece.getType() != PieceType.FLAG){
				redHasMoveable = true;
				break;
			}
		}
		
		for (Piece piece : gameBoard.getRemainingPieces(PlayerColor.BLUE)){
			if (piece.getType() != PieceType.BOMB || piece.getType() != PieceType.FLAG){
				blueHasMoveable = true;
				break;
			}
		}
		
		if(!redHasMoveable && !blueHasMoveable) {
			resultStatus = MoveResultStatus.DRAW;
		} else if (!redHasMoveable){
			resultStatus = MoveResultStatus.BLUE_WINS;
		} else if (!blueHasMoveable){
			resultStatus = MoveResultStatus.RED_WINS;
		}
			
		return new MoveResult(resultStatus, firstResult.getBattleWinner());
	}

	/**
	 * Deals with the different special cases, such as spy/marshall relationship and bomb
	 * @param gameBoard the current game board
	 * @param currentTurn the player whose turn it is
	 * @param pieceMoving the piece type that is being moved
	 * @param fromLocation the location that is being moved from
	 * @param toLocation the location that is being moved to
	 * @return the result of the move
	 */
	private MoveResult dealWithSpecialCases(StrategyBoard gameBoard, PlayerColor currentTurn, 
			PieceType pieceMoving, Location fromLocation, Location toLocation) {
		final MoveResult result;

		if(gameBoard.getPieceAt(fromLocation) == null && gameBoard.getPieceAt(toLocation) == null){
			return new MoveResult(currentTurn == PlayerColor.BLUE ? MoveResultStatus.RED_WINS : MoveResultStatus.BLUE_WINS,
					new PieceLocationDescriptor(null,null));
		}
		final Piece enemyPiece = gameBoard.getPieceAt(toLocation);
		if (enemyPiece != null && pieceMoving == PieceType.SPY && enemyPiece.getType() == PieceType.MARSHAL){
			result = dealWithSpyMarshall(gameBoard, currentTurn, pieceMoving, fromLocation, toLocation);
		} else if (enemyPiece != null && enemyPiece.getType() == PieceType.BOMB) {
			result = dealWithBomb(gameBoard, currentTurn, pieceMoving, fromLocation, toLocation);
		} else {
			result = null;
		}
		
		return result;
	}
	
	/**
	 * Deals with the spy and marshal relationship
	 * @param gameBoard the current game board
	 * @param currentTurn the current turn of the player
	 * @param pieceMoving the piece type that is moving
	 * @param fromLocation the location that is being moved from
	 * @param toLocation the location that is being moved to
	 * @return the resulting move result.
	 */
	private MoveResult dealWithSpyMarshall(StrategyBoard gameBoard, PlayerColor currentTurn, 
			PieceType pieceMoving, Location fromLocation, Location toLocation) {
		gameBoard.removePiece(toLocation);
		gameBoard.movePiece(fromLocation, toLocation);
		return new MoveResult(MoveResultStatus.OK,
				new PieceLocationDescriptor(gameBoard.getPieceAt(toLocation), toLocation));
	}
	
	/**
	 * Deals with the bomb special condition
	 * @param gameBoard the current game board
	 * @param currentTurn the current turn of the player
	 * @param pieceMoving the piece type that is moving
	 * @param fromLocation the location that is being moved from
	 * @param toLocation the location that is being moved to
	 * @return the resulting move result.
	 */
	private MoveResult dealWithBomb(StrategyBoard gameBoard, PlayerColor currentTurn, 
			PieceType pieceMoving, Location fromLocation, Location toLocation) {
		
		final PieceLocationDescriptor battleWinner;
		if (pieceMoving == PieceType.MINER){
			//remove the bomb and put the miner in its place
			gameBoard.removePiece(toLocation);
			gameBoard.movePiece(fromLocation, toLocation);
			
		} else {
			gameBoard.removePiece(fromLocation); //kill the piece that stepped on the bomb
		}
		
		battleWinner = new PieceLocationDescriptor(gameBoard.getPieceAt(toLocation), toLocation);
		return new MoveResult(MoveResultStatus.OK, battleWinner);
	}	
	
}
