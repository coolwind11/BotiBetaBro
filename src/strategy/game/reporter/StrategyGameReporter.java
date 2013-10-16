/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.reporter;

import java.util.Collection;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.common.StrategyGameObserver;

/**
 * Reports on actions occuring within strategy games by observing the games.
 * @author cbotaish, drob
 * @version October 14, 2013
 */
public class StrategyGameReporter implements StrategyGameObserver
{
	private PlayerColor currentPlayer = PlayerColor.RED;

	/**
	 * @see strategy.game.common.StrategyGameObserver#gameStart(Collection, Collection)
	 */
	@Override
	public void gameStart(Collection<PieceLocationDescriptor> redConfiguration,
			Collection<PieceLocationDescriptor> blueConfiguration)
	{
		System.out.println("Game Started!");
		System.out.println(" Red Piece Initial Setup: ");
		for(PieceLocationDescriptor piece : redConfiguration)
		{
			PieceType type = piece.getPiece().getType();
			int xCoord = piece.getLocation().getCoordinate(Coordinate.X_COORDINATE);
			int yCoord = piece.getLocation().getCoordinate(Coordinate.Y_COORDINATE);
			System.out.println("  " + type + " Location: (" + xCoord + ", " + yCoord + ")");
		}
		
		System.out.println("\n Blue Piece Initial Setup: ");
		for(PieceLocationDescriptor piece : blueConfiguration)
		{
			PieceType type = piece.getPiece().getType();
			int xCoord = piece.getLocation().getCoordinate(Coordinate.X_COORDINATE);
			int yCoord = piece.getLocation().getCoordinate(Coordinate.Y_COORDINATE);
			System.out.println("  " + type + " Location: (" + xCoord + ", " + yCoord + ")");
		}
	}

	/**
	 * @see strategy.game.common.StrategyGameObserver#moveHappened(PieceType, Location, Location, MoveResult, StrategyException)
	 */
	@Override
	public void moveHappened(PieceType piece, Location from, Location to,
			MoveResult result, StrategyException fault)
	{
		System.out.println(currentPlayer + " Move!");
		System.out.println(" Piece: " + piece);
		System.out.println(" From: " + from);
		System.out.println(" To: " + to);
		
		System.out.println(" BattleWinner: " + result.getBattleWinner().getPiece());
		System.out.println(" Move Result: " + result.getStatus());
		
		currentPlayer = currentPlayer == PlayerColor.RED ? PlayerColor.BLUE : PlayerColor.RED;
	}

}
