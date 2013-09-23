package strategy.game.version.gamma;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.PieceType;
import strategy.game.common.StrategyBoard;
import strategy.game.common.StrategyMoveValidator;

public class GammaStrategyMoveValidator implements StrategyMoveValidator
{

	private final int BOARD_HEIGHT = 6;
	private final int BOARD_WIDTH = 6;
	private final int MAX_MOVE_DISTANCE = 2;
	
	public GammaStrategyMoveValidator()
	{
	}

	@Override
	public void checkMoveValidity(StrategyBoard gameBoard, PlayerColor currentTurn, PieceType movePiece, 
			Location moveFromLocation, Location moveToLocation) throws StrategyException
	{
		//if the from coordinate is out of bounds, the move is invalid
		if (moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) >= BOARD_WIDTH
				|| moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) < 0
				|| moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) >= BOARD_HEIGHT
				|| moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) < 0)
		{
			throw new StrategyException("From coordinate out of board bounds");
		}

		//if the to coordinate is out of bounds...the move; is invalid
		if (moveToLocation.getCoordinate(Coordinate.X_COORDINATE) >= BOARD_WIDTH
				|| moveToLocation.getCoordinate(Coordinate.X_COORDINATE) < 0
				|| moveToLocation.getCoordinate(Coordinate.Y_COORDINATE) >= BOARD_HEIGHT
				|| moveToLocation.getCoordinate(Coordinate.Y_COORDINATE) < 0)
		{
			throw new StrategyException("To coordinate out of board bounds");
		}

		//if there is no piece at the supplied location, the move is invalid
		if (gameBoard.getPieceAt(moveFromLocation) == null)
		{
			throw new StrategyException("No piece to move at location");
		}
		
		//if the piece at the supplied location isnt the right type, the move is invalid
		if(gameBoard.getPieceAt(moveFromLocation).getType() != movePiece)
		{
			throw new StrategyException("Incorrect piece at the given location");
		}

		//if you try to move your opponents piece, the move is invalid
		if (gameBoard.getPieceAt(moveFromLocation).getOwner() != currentTurn)
		{
			throw new StrategyException("Not correct player turn");
		}

		//if the piece is the flag, ya can't move that.
		if (gameBoard.getPieceAt(moveFromLocation).getType() == PieceType.FLAG)
		{
			throw new StrategyException("Cannot move the flag");
		}

		//if you move onto your own piece, your gonna have a bad time
		if (gameBoard.getPieceAt(moveToLocation) != null && gameBoard.getPieceAt(moveToLocation).getOwner() == currentTurn)
		{
			throw new StrategyException("Cant attack own piece");
		}

		//if you try to move diagonally, the move is invalid
		boolean inSameRow = moveFromLocation.getCoordinate(Coordinate.X_COORDINATE) == moveToLocation.getCoordinate(Coordinate.X_COORDINATE);
		boolean inSameCol = moveFromLocation.getCoordinate(Coordinate.Y_COORDINATE) == moveToLocation.getCoordinate(Coordinate.Y_COORDINATE);
		
		if(!(inSameRow || inSameCol))
		{
			throw new StrategyException("Cannot move diagonally");
		}
		
		//if you try to move too far, THE MOVE IS INVALID
		if(moveFromLocation.distanceTo(moveToLocation) >= MAX_MOVE_DISTANCE)
		{
			throw new StrategyException("Moved too far");
		}		
	}

}
