package strategy.game.version.gamma;

import strategy.common.PlayerColor;
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveResolver;
import strategy.game.version.StrategyBoard;

public class GammaStrategyMoveResolver extends BaseStrategyMoveResolver {

	
	public GammaStrategyMoveResolver() {
		super();
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
	
	public MoveResult resolveMove(StrategyBoard gameBoard, PlayerColor currentTurn,
			PieceType pieceMoving, Location fromLocation, Location toLocation)
	{
		MoveResult firstResult = super.resolveMove(gameBoard, currentTurn, pieceMoving, fromLocation, toLocation);
		if (firstResult.getStatus() != MoveResultStatus.OK){
			return firstResult;
		} else {
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
			return new MoveResult(resultStatus, firstResult.getBattleWinner());
		}
	}
}
