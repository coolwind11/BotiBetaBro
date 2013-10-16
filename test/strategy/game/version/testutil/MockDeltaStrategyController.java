/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version.testutil;

import java.util.Collection;
import java.util.LinkedList;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.StrategyGameController;
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.version.StrategyBoard;
import strategy.game.version.StrategyMoveResolver;
import strategy.game.version.StrategyMoveValidator;
import strategy.game.version.delta.DeltaStrategyMoveResolver;
import strategy.game.version.delta.DeltaStrategyMoveValidator;

/**
 * Provides a mock delta strategy controller for testing purposes
 * @author cbotaish, drob
 */
public class MockDeltaStrategyController implements StrategyGameController {
	
	private final StrategyMoveValidator moveValidator;
	
	private final StrategyMoveResolver moveResolver;

	protected StrategyBoard gameBoard;
	
	private boolean gameStarted = false;
	private boolean gameOver = false;

	private PlayerColor playerTurn = PlayerColor.RED;
	
	public MockDeltaStrategyController(Collection<PieceLocationDescriptor> redPieces, 
			Collection<PieceLocationDescriptor> bluePieces){
		
		final Collection<PieceLocationDescriptor> allPieces = 
				new LinkedList<PieceLocationDescriptor>();
		allPieces.addAll(redPieces);
		allPieces.addAll(bluePieces);
		
		this.moveValidator = new DeltaStrategyMoveValidator();
		this.moveResolver = new DeltaStrategyMoveResolver();
		this.gameBoard = new StrategyBoard(allPieces);
	}
	@Override
	public void startGame() throws StrategyException
	{
		if(gameStarted) {
			throw new StrategyException("Must complete the current game "
					+ "before beginning a new one");
		}
		
		gameStarted = true;
		gameOver = false;
		
		playerTurn = PlayerColor.RED;
	}

	/**
	 * @see strategy.game.StrategyGameController#move(PieceType, Location,
	 *      Location)
	 */
	@Override
	public MoveResult move(PieceType piece, Location from, Location to)
			throws StrategyException
	{
		//if the game hasn't started, the move is invalid
		if (!gameStarted)
		{
			throw new StrategyException("Game hasn't been started yet.");
		}

		//if the game is already over, the move is invalid
		if (gameOver)
		{
			throw new StrategyException("The game is already over");
		}
		
		moveValidator.checkMoveValidity(gameBoard, playerTurn, piece, from, to);
		
		final MoveResult result = moveResolver.resolveMove(gameBoard, playerTurn, piece, from, to);
	
		if(result.getStatus() != MoveResultStatus.OK)
		{
			gameOver = true;
		}
		
		playerTurn = playerTurn == PlayerColor.BLUE ? PlayerColor.RED
				: PlayerColor.BLUE; // change the player turn
		
		return result;
	}
	
	public StrategyBoard getBoard() {
		return gameBoard;
	}

	/**
	 * @see strategy.game.StrategyGameController#getPieceAt(Location)
	 */
	@Override
	public Piece getPieceAt(Location location)
	{
		return gameBoard.getPieceAt(location);
	}
	

}
