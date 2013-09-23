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
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveResolver;
import strategy.game.version.StrategyBoard;
import strategy.game.version.StrategyMoveResolver;

/**
 * Provides a move resolver strategy for the BetaStrategy implementation
 * @author cbotaish, drob
 * @version Sept 22, 2013
 */
public class BetaStrategyMoveResolver extends BaseStrategyMoveResolver
{
	protected final int MAX_TURNS = 12;
	protected int turnsPlayed = 0;
	
	public BetaStrategyMoveResolver()
	{
		super();
	}

	@Override
	public MoveResult resolveMove(StrategyBoard gameBoard, PlayerColor currentTurn,
			PieceType pieceMoving, Location fromLocation, Location toLocation)
	{
		MoveResult firstResult = super.resolveMove(gameBoard, currentTurn, pieceMoving, fromLocation, toLocation);
		
		MoveResultStatus resultStatus = firstResult.getStatus();
		// end the game after 6 turns if nobody has won.
		
		if (turnsPlayed == MAX_TURNS - 1 && resultStatus == MoveResultStatus.OK)
		{
			resultStatus = MoveResultStatus.DRAW;
		}
		
		turnsPlayed++;
		
		return new MoveResult(resultStatus, firstResult.getBattleWinner());

	}

	@Override
	protected void setupResolverConfiguration() {
		// TODO Auto-generated method stub
		pieceRank.put(PieceType.MARSHAL, 12);
		pieceRank.put(PieceType.CAPTAIN, 8);
		pieceRank.put(PieceType.COLONEL, 10);
		pieceRank.put(PieceType.LIEUTENANT, 7);
		pieceRank.put(PieceType.SERGEANT, 6);
		pieceRank.put(PieceType.FLAG, 1);
	}

}
