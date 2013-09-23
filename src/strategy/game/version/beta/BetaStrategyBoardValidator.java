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

import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyBoardValidator;

/**
 * This class provides validations for the initial board setups.
 * @author drob, cbotaish
 * @version Sept 22, 2013
 */
public class BetaStrategyBoardValidator extends BaseStrategyBoardValidator {
	
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
