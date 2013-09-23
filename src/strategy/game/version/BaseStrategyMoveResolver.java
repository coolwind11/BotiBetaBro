package strategy.game.version;

import java.util.HashMap;
import java.util.Map;

import strategy.common.PlayerColor;
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;

public abstract class BaseStrategyMoveResolver implements StrategyMoveResolver {
	
	protected final Map<PieceType, Integer> pieceRank = new HashMap<PieceType, Integer>();
	
	
	public BaseStrategyMoveResolver() {
		setupResolverConfiguration();
	}
	protected abstract void setupResolverConfiguration();
	
	@Override
	public MoveResult resolveMove(StrategyBoard gameBoard,
			PlayerColor currentTurn, PieceType pieceMoving,
			Location fromLocation, Location toLocation) {
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
		
		return new MoveResult(moveResult, battleWinner);
	}
}
