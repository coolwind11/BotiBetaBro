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
import strategy.game.common.StrategyBoard;
import strategy.game.common.StrategyBoardValidator;
import strategy.game.common.StrategyMoveResolver;
import strategy.game.common.StrategyMoveValidator;

/**
 * An implementation of the game controller for the Beta Strategy version.
 * 
 * @author Dan Robertson, Chris Botaish
 * @version Sep 9, 2013
 */
public class BetaStrategyGameController implements StrategyGameController
{
	
	private final StrategyBoardValidator boardValidator;
	
	private final StrategyMoveValidator moveValidator;
	
	private final StrategyMoveResolver moveResolver;

	private final StrategyBoard gameBoard;
	
	private boolean gameStarted = false;
	private boolean gameOver = false;

	private PlayerColor playerTurn = PlayerColor.RED;

	/**
	 * Creates a BetaStrategyGameController with the given initial configs
	 * 
	 * @param redPieces the locations of the red pieces at the start
	 * @param bluePieces the locations of the blue pieces at the start
	 * @throws StrategyException if the initial game board is invalid.
	 */
	public BetaStrategyGameController(Collection<PieceLocationDescriptor> redPieces, 
			Collection<PieceLocationDescriptor> bluePieces) throws StrategyException
	{
		boardValidator = new BetaStrategyBoardValidator();
		moveValidator = new BetaStrategyMoveValidator();
		moveResolver = new BetaStrategyMoveResolver();

		if(!boardValidator.isValidInitialSetup(redPieces, bluePieces))
		{
			throw new StrategyException("Game board is invalid!");
		}
		
		Collection<PieceLocationDescriptor> allPieces = new LinkedList<PieceLocationDescriptor>();
		allPieces.addAll(redPieces);
		allPieces.addAll(bluePieces);
		
		gameBoard = new StrategyBoard(allPieces);
	}

	/**
	 * @see strategy.game.StrategyGameController#startGame()
	 */
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
		
		MoveResult result = moveResolver.resolveMove(gameBoard, playerTurn, piece, from, to);
	
		if(result.getStatus() != MoveResultStatus.OK)
			gameOver = true;
		
		playerTurn = playerTurn == PlayerColor.BLUE ? PlayerColor.RED
				: PlayerColor.BLUE; // change the player turn
		
		return result;
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
