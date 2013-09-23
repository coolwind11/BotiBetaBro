package strategy.game.version.gamma;

import java.util.HashMap;

import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyBoardValidator;

/**
 * Implementation of strategy board resolver extended for gamma strategy
 * @author Dan Robertson, Chris Botaish
 * @version 9/23/13
 */
public class GammaStrategyBoardValidator extends BaseStrategyBoardValidator {

	
	public GammaStrategyBoardValidator() {
		super();
	}
	/**
	 * setup the paramaters for board validation specific to gamma version, such as
	 * piece type and max count,
	 * board dimensions
	 * red and blue starting bounds
	 */
	@Override
	protected void setupValidBoardConfiguration() {
		validPieceCount = new HashMap<PieceType, Integer>();
		validPieceCount.put(PieceType.FLAG, 1);
		validPieceCount.put(PieceType.MARSHAL, 1);
		validPieceCount.put(PieceType.COLONEL, 2);
		validPieceCount.put(PieceType.CAPTAIN, 2);
		validPieceCount.put(PieceType.LIEUTENANT, 3);
		validPieceCount.put(PieceType.SERGEANT, 3);
		
		MAX_PIECES = 24;
		MAX_X = 5;
		MAX_Y = 5;
		MIN_X = 0;
		MIN_Y = 0;
		
		MIN_BLUE_STARTING_Y = 4;
		MAX_RED_STARTING_Y = 1;		
	}
	
	

}
