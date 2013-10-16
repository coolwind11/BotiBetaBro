/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version.epsilon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
public class EpsilonStrategyBoardValidator extends BaseStrategyBoardValidator {
	
	private final Collection<PieceLocationDescriptor> otherPieces = new ArrayList<PieceLocationDescriptor>();
	
	/**
	 * Creates a new delta strategy board validator
	 */
	public EpsilonStrategyBoardValidator()
	{
		//Add the chokepoints before creating the board
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(2, 4)));
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(3, 4)));
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(2, 5)));
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(3, 5)));
		
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(6, 4)));
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(7, 4)));
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(6, 5)));
		otherPieces.add(new PieceLocationDescriptor(new Piece(PieceType.CHOKE_POINT, null), new Location2D(7, 5)));
	}
	
	/**
	 * setup the paramaters for board validation specific to delta version, such as
	 * piece type and max count,
	 * board dimensions
	 * red and blue starting bounds
	 */
	@Override
	protected void setupValidBoardConfiguration() {
		validPieceCount = new HashMap<PieceType, Integer>();
		validPieceCount.put(PieceType.MARSHAL, 1);
		validPieceCount.put(PieceType.GENERAL, 1);
		validPieceCount.put(PieceType.COLONEL, 2);
		validPieceCount.put(PieceType.MAJOR, 3);
		validPieceCount.put(PieceType.CAPTAIN, 4);
		validPieceCount.put(PieceType.LIEUTENANT, 2);
		validPieceCount.put(PieceType.FIRST_LIEUTENANT, 2);
		validPieceCount.put(PieceType.SERGEANT, 4);
		validPieceCount.put(PieceType.MINER, 5);
		validPieceCount.put(PieceType.SCOUT, 8);
		validPieceCount.put(PieceType.BOMB, 6);
		validPieceCount.put(PieceType.SPY, 1);
		
		MAX_PIECES = 80;
		MAX_X = 9;
		MAX_Y = 9;
		MIN_X = 0;
		MIN_Y = 0;
		
		MIN_BLUE_STARTING_Y = 6;
		MAX_RED_STARTING_Y = 3;
	}
	
	/**
	 * @see strategy.game.version.StrategyBoardValidator#getGameSpecificPieces()
	 */
	@Override
	public Collection<PieceLocationDescriptor> getGameSpecificPieces()
	{
		return otherPieces;
	}
	
	@Override
	protected boolean hasRightNumberOfPieces(Map<PieceType, Integer> redPieceCount, Map<PieceType, Integer> bluePieceCount) {
		
		int redOffby = 0;
		int blueOffby = 0;
		for(PieceType piece : validPieceCount.keySet())
		{
			int requiredNumber = validPieceCount.get(piece);
			
			Integer redCount = redPieceCount.get(piece);
			Integer blueCount = bluePieceCount.get(piece);
			
			if(redCount == null || blueCount == null)
			{
				return false;
			}
			
			if(redCount != requiredNumber){
				redOffby += Math.abs(redCount - requiredNumber);
			}
			if(blueCount != requiredNumber) {
				blueOffby += Math.abs(blueCount - requiredNumber);
			}
		}
		
		if (redOffby > 1 || blueOffby > 1){
			return false;
		}
		
		return true;
	}
}
