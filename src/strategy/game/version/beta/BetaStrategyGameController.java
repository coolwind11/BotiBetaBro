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

import java.util.HashMap;
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
import strategy.game.common.StrategyBoard;

/**
 * The BetaStrategyGameController implements the game core for the Beta Strategy
 * version.
 * 
 * @author Dan Robertson, Chris Botaish
 * @version Sep 9, 2013
 */
public class BetaStrategyGameController implements StrategyGameController
{

	final private int BOARD_HEIGHT = 6;
	final private int BOARD_WIDTH = 6;
	final private int MAX_MOVE_DISTANCE = 2;

	final private StrategyBoard gameBoard;
	final private Map<PieceType, Integer> pieceRank = new HashMap<PieceType, Integer>();

	private boolean gameStarted = false;
	private boolean gameOver = false;

	private int turnsPlayed;

	private PlayerColor playerTurn = PlayerColor.RED;

	/**
	 * Pass the piece arrangements to the controller so it has a clue what's
	 * going on
	 * 
	 * @param Location
	 *            of all pieces at the start of the game, ~provided by the
	 *            player~
	 */
	public BetaStrategyGameController(StrategyBoard gameBoard)
	{
		this.gameBoard = gameBoard;
		pieceRank.put(PieceType.MARSHAL, 12);
		pieceRank.put(PieceType.CAPTAIN, 8);
		pieceRank.put(PieceType.COLONEL, 10);
		pieceRank.put(PieceType.LIEUTENANT, 7);
		pieceRank.put(PieceType.SERGEANT, 6);
		pieceRank.put(PieceType.FLAG, 1);
	}

	/**
	 * @see strategy.game.StrategyGameController#startGame()
	 */
	@Override
	public void startGame() throws StrategyException
	{
		if (gameStarted)
		{
			throw new StrategyException("Game is already in progress");
		}

		gameBoard.resetBoard();

		if (!gameBoard.hasValidInitialBoardSetup())
		{
			throw new StrategyException("Game board is invalid");
		}

		gameStarted = true;
		gameOver = false;
		turnsPlayed = 0;
	}

	/**
	 * @see strategy.game.StrategyGameController#move(PieceType, Location,
	 *      Location)
	 */
	@Override
	public MoveResult move(PieceType piece, Location from, Location to)
			throws StrategyException
	{
		verifyMove(piece, from, to); // check that we can make this move.

		Piece movePiece = getPieceAt(from);
		Piece opponentPiece = getPieceAt(to);

		final PieceLocationDescriptor battleWinner;
		MoveResultStatus moveResult = MoveResultStatus.OK;

		// If theres a battle.
		if (opponentPiece != null)
		{
			if (opponentPiece.getType() == PieceType.FLAG)
			{ // game over
				gameOver = true;

				battleWinner = new PieceLocationDescriptor(movePiece, to);
				moveResult = playerTurn == PlayerColor.BLUE ? MoveResultStatus.BLUE_WINS
						: MoveResultStatus.RED_WINS;
			}
			else if (getPieceRank(movePiece) > getPieceRank(opponentPiece))
			{ // moving player wins
				battleWinner = new PieceLocationDescriptor(movePiece, to);
				gameBoard.movePiece(from, to);
			}
			else if (getPieceRank(movePiece) == getPieceRank(opponentPiece))
			{ // draw
				// Both pieces get removed in a draw.
				gameBoard.removePiece(to);
				gameBoard.removePiece(from);
				battleWinner = null;
			}
			else
			{ // opponent wins
				battleWinner = new PieceLocationDescriptor(opponentPiece, to);
				gameBoard.movePiece(to, from);
			}
		}
		else
		{
			battleWinner = new PieceLocationDescriptor(movePiece, to);
			gameBoard.movePiece(from, to);
		}

		// end the game after 6 turns if nobody has won.
		if (turnsPlayed == 5 && moveResult == MoveResultStatus.OK)
		{
			moveResult = MoveResultStatus.DRAW;
			gameOver = true;
		}

		turnsPlayed++;
		playerTurn = playerTurn == PlayerColor.BLUE ? PlayerColor.RED
				: PlayerColor.BLUE; // change the player turn
		return new MoveResult(moveResult, battleWinner);
	}

	/**
	 * @see strategy.game.StrategyGameController#getPieceAt(Location)
	 */
	@Override
	public Piece getPieceAt(Location location)
	{
		return gameBoard.getPieceAt(location);
	}

	/**
	 * Check to see if a move is valid, throws exception if not.
	 * 
	 * @param piece
	 *            the piece supplied
	 * @param from
	 *            location under scrutiny
	 * @param to
	 *            location under scrutiny
	 * @throws StrategyException
	 */
	private void verifyMove(PieceType piece, Location from, Location to)
			throws StrategyException
	{
		if (!gameStarted)
		{
			throw new StrategyException(
					"Wait until game begins before making a move");
		}

		if (gameOver)
		{
			throw new StrategyException("The game is already over");
		}

		if (from.getCoordinate(Coordinate.X_COORDINATE) >= BOARD_WIDTH
				|| from.getCoordinate(Coordinate.X_COORDINATE) < 0
				|| from.getCoordinate(Coordinate.Y_COORDINATE) >= BOARD_HEIGHT
				|| from.getCoordinate(Coordinate.Y_COORDINATE) < 0)
		{
			throw new StrategyException("From coordinate out of board bounds");
		}

		if (to.getCoordinate(Coordinate.X_COORDINATE) >= BOARD_WIDTH
				|| to.getCoordinate(Coordinate.X_COORDINATE) < 0
				|| to.getCoordinate(Coordinate.Y_COORDINATE) >= BOARD_HEIGHT
				|| to.getCoordinate(Coordinate.Y_COORDINATE) < 0)
		{
			throw new StrategyException("To coordinate out of board bounds");
		}

		if (getPieceAt(from) == null)
		{
			throw new StrategyException("No piece to move at location");
		}

		if (getPieceAt(from).getOwner() != playerTurn)
		{
			throw new StrategyException("Not correct player turn");
		}

		if (getPieceAt(from).getType() == PieceType.FLAG)
		{
			throw new StrategyException("Cannot move the flag");
		}

		if (getPieceAt(to) != null && getPieceAt(to).getOwner() == playerTurn)
		{
			throw new StrategyException("Cant attack own piece");
		}

		if (from.distanceTo(to) >= MAX_MOVE_DISTANCE)
		{
			throw new StrategyException("Moved too far");
		}
	}

	/**
	 * Gets the piece rank for the given piece, which is used to determine the
	 * outcome of battles
	 * 
	 * @param piece
	 *            the piece to get the rank for
	 * @return the piece's rank
	 */
	private int getPieceRank(Piece piece)
	{
		return pieceRank.get(piece.getType());
	}
}
