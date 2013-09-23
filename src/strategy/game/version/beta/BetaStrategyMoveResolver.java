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
import strategy.game.version.StrategyBoard;
import strategy.game.version.StrategyMoveResolver;

public class BetaStrategyMoveResolver implements StrategyMoveResolver
{
	private final int MAX_TURNS = 12;
	
	private final Map<PieceType, Integer> pieceRank = new HashMap<PieceType, Integer>();

	private int turnsPlayed = 0;
	
	public BetaStrategyMoveResolver()
	{
		pieceRank.put(PieceType.MARSHAL, 12);
		pieceRank.put(PieceType.CAPTAIN, 8);
		pieceRank.put(PieceType.COLONEL, 10);
		pieceRank.put(PieceType.LIEUTENANT, 7);
		pieceRank.put(PieceType.SERGEANT, 6);
		pieceRank.put(PieceType.FLAG, 1);
	}

	@Override
	public MoveResult resolveMove(StrategyBoard gameBoard, PlayerColor currentTurn,
			PieceType pieceMoving, Location fromLocation, Location toLocation)
	{
		final Piece movePiece = gameBoard.getPieceAt(fromLocation);
		final Piece opponentPiece = gameBoard.getPieceAt(toLocation);

		PieceLocationDescriptor battleWinner = null;
		MoveResultStatus moveResult = MoveResultStatus.OK;
		
		// If theres a battle.
		if (opponentPiece != null)
		{
			if (opponentPiece.getType() == PieceType.FLAG)
			{ 				
				battleWinner = new PieceLocationDescriptor(movePiece, toLocation);
				moveResult = currentTurn == PlayerColor.BLUE ? MoveResultStatus.BLUE_WINS
						: MoveResultStatus.RED_WINS;
			}
			else if (pieceRank.get(movePiece.getType()) > pieceRank.get(opponentPiece.getType()))
			{ // moving player wins
				battleWinner = new PieceLocationDescriptor(movePiece, toLocation);
				gameBoard.movePiece(fromLocation, toLocation);
			}
			else if (pieceRank.get(movePiece.getType()) == pieceRank.get(opponentPiece.getType()))
			{ // draw
				// Both pieces get removed in a draw.
				gameBoard.removePiece(toLocation);
				gameBoard.removePiece(fromLocation);
			}
			else
			{ // opponent wins
				battleWinner = new PieceLocationDescriptor(opponentPiece, fromLocation);
				gameBoard.movePiece(toLocation, fromLocation);
			}
		}
		else
		{
			gameBoard.movePiece(fromLocation, toLocation);
		}

		// end the game after 6 turns if nobody has won.
		if (turnsPlayed == MAX_TURNS - 1 && moveResult == MoveResultStatus.OK)
		{
			moveResult = MoveResultStatus.DRAW;
		}

		turnsPlayed++;
		
		return new MoveResult(moveResult, battleWinner);
	}

}
