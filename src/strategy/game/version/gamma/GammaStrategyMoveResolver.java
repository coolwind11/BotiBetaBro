package strategy.game.version.gamma;

import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveResolver;

public class GammaStrategyMoveResolver extends BaseStrategyMoveResolver {
	
	@Override
	protected void setupResolverConfiguration() {
		pieceRank.put(PieceType.MARSHAL, 12);
		pieceRank.put(PieceType.CAPTAIN, 8);
		pieceRank.put(PieceType.COLONEL, 10);
		pieceRank.put(PieceType.LIEUTENANT, 7);
		pieceRank.put(PieceType.SERGEANT, 6);
		pieceRank.put(PieceType.FLAG, 1);
	}
	
}
