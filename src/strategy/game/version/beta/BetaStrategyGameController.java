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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.StrategyGameController;
import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;

/**
 * The BetaStrategyGameController implements the game core for
 * the Beta Strategy version.
 * @author Dan Robertson, Chris Botaish
 * @version Sep 9, 2013
 */
public class BetaStrategyGameController implements StrategyGameController {
	
	final private int BOARD_HEIGHT = 6;
	final private int BOARD_WIDTH = 6;
	final private int MAX_MOVE_DISTANCE = 2;
	
	final private Map<Location,Piece> pieceLocationMap;
	final private List<PieceLocationDescriptor> pieceLocations;
	final private Map<PieceType,Integer> pieceRank = new HashMap<PieceType,Integer>();
	
	private boolean gameStarted = false;
	private boolean gameOver = false;
	
	private int turnsPlayed;
	
	private PlayerColor playerTurn = PlayerColor.RED;
	
	
	
	/**
	 * Pass the piece arrangements to the controller so it has a clue what's going on
	 * @param Location of all pieces at the start of the game, ~provided by the player~
	 */
	public BetaStrategyGameController(List<PieceLocationDescriptor> pieces) {
		this.pieceLocations = pieces;
		this.pieceLocationMap = new HashMap<Location,Piece>();
		
		pieceRank.put(PieceType.MARSHAL, 12);
		pieceRank.put(PieceType.CAPTAIN, 8);
		pieceRank.put(PieceType.COLONEL, 10);
		pieceRank.put(PieceType.LIEUTENANT, 7);
		pieceRank.put(PieceType.SERGEANT, 6);
		pieceRank.put(PieceType.FLAG, 1);
	}
	
	@Override
	public void startGame() throws StrategyException {
		
		if (gameStarted) {
			throw new StrategyException("game is already in progress");
		}
		
		pieceLocationMap.clear();
		for (PieceLocationDescriptor pieceLocation : pieceLocations) {
			pieceLocationMap.put(pieceLocation.getLocation(), pieceLocation.getPiece());
		}
		
		gameStarted = true;
		gameOver = false;
		turnsPlayed = 0;
	}

	@Override
	public MoveResult move(PieceType piece, Location from, Location to)
			throws StrategyException {
		
		verifyMove(piece,from,to); //check that we can make this move.
		Piece movePiece = getPieceAt(from);
		Piece opponentPiece;
		final MoveResultStatus moveResult;
		final PieceLocationDescriptor battleWinner;
		
		if (pieceLocationMap.containsKey(to)) {
			opponentPiece = getPieceAt(to);
			if (opponentPiece.getType() == PieceType.FLAG){ //game over
				battleWinner = new PieceLocationDescriptor(movePiece,to);
				moveResult = playerTurn == PlayerColor.BLUE ? MoveResultStatus.BLUE_WINS : MoveResultStatus.RED_WINS;
			} else if (getPieceRank(movePiece) > getPieceRank(opponentPiece)) { //moving player wins
				battleWinner = new PieceLocationDescriptor(movePiece,to);
				moveResult = MoveResultStatus.OK;
				pieceLocationMap.remove(from);
				pieceLocationMap.put(to, movePiece);
			} else if (getPieceRank(movePiece) == getPieceRank(opponentPiece)){ //draw
				pieceLocationMap.remove(to);
				pieceLocationMap.remove(from);
				battleWinner = null;
				moveResult = MoveResultStatus.OK;
			} else { //opponent wins
				battleWinner = new PieceLocationDescriptor(opponentPiece,to);
				moveResult = MoveResultStatus.OK;
				pieceLocationMap.remove(from);
			}
		} else {
			battleWinner = new PieceLocationDescriptor(movePiece,to);
			moveResult = MoveResultStatus.OK;
			pieceLocationMap.remove(from);
			pieceLocationMap.put(to, movePiece);
		}
		
		turnsPlayed++;
		playerTurn = playerTurn == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE; //change the player turn
		return new MoveResult(moveResult,battleWinner);
	}

	@Override
	public Piece getPieceAt(Location location) {
		return pieceLocationMap.get(location);
	}
	
	/**
	 * Check to see if a move is valid, throws exception if not.
	 * @param piece the piece supplied
	 * @param from location under scrutiny
	 * @param to location under scrutiny
	 * @throws StrategyException
	 */
	private void verifyMove(PieceType piece, Location from, Location to)
			throws StrategyException {
		if(!gameStarted) {
			throw new StrategyException("Wait until game begins before making a move");
		}

		if(!pieceLocationMap.containsKey(from)) {
			throw new StrategyException("No piece to move at location");
		}
				
		if(from.getCoordinate(Coordinate.X_COORDINATE) >= BOARD_WIDTH || from.getCoordinate(Coordinate.Y_COORDINATE) < 0
			|| to.getCoordinate(Coordinate.X_COORDINATE) >= BOARD_HEIGHT || to.getCoordinate(Coordinate.Y_COORDINATE) < 0){
			throw new StrategyException("Move coordinate out of board bounds");
		}
		
		if(getPieceAt(from).getOwner() != playerTurn){
			throw new StrategyException("Not correct player turn");
		}
		
		if(pieceLocationMap.containsKey(to) && getPieceAt(to).getOwner() == playerTurn) {
			throw new StrategyException("Cant attack own piece");
		}
		
		if(from.distanceTo(to) >= MAX_MOVE_DISTANCE) {
			throw new StrategyException("Moved too far");
		}
	}

	private int getPieceRank(Piece piece) throws StrategyException{
		return pieceRank.get(piece.getType());
	}
}
