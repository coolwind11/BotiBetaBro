/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version.delta;

import java.util.Collection;
import java.util.HashMap;

import strategy.game.common.Location2D;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyBoardValidator;

/**
 * Implementation of strategy board resolver extended for delta strategy
 * @author Dan Robertson, Chris Botaish
 * @version 9/23/13
 */
public class DeltaStrategyBoardValidator extends BaseStrategyBoardValidator {
	
	/**
	 * setup the paramaters for board validation specific to delta version, such as
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
	
	/**
	 * @see strategy.game.version.StrategyBoardValidator#isValidInitialSetup(Collection, Collection)
	 */
	@Override
	public boolean isValidInitialSetup(Collection<PieceLocationDescriptor> redConfig,
			Collection<PieceLocationDescriptor> blueConfig) {
		final boolean isValidBoardConfig = super.isValidInitialSetup(redConfig, blueConfig);
		
		//Add the chokepoints before creating the board
		redConfig.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(2, 2)));
		redConfig.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(3, 2)));
		redConfig.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(2, 3)));
		redConfig.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(3, 3)));
		
		return isValidBoardConfig;
	}
}
