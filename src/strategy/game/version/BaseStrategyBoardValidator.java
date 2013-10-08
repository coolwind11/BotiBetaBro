/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;


/**
 * abstract class for StrategyBoardValidator which contains all the assumed standard logic for
 * validating any strategy board
 * @author Dan Robertson, Chris Botaish
 * @version 9/23/13
 */
public abstract class BaseStrategyBoardValidator implements
		StrategyBoardValidator {

	protected int MAX_PIECES = 24;
	protected int MAX_X = 5;
	protected int MAX_Y = 5;
	protected int MIN_X = 0;
	protected int MIN_Y = 0;
	
	protected int MIN_BLUE_STARTING_Y = 4;
	protected int MAX_RED_STARTING_Y = 1;
	
	protected Map<PieceType,Integer> validPieceCount;
	
	/**
	 * Creates a Gamma Strategy board validator
	 */
	protected BaseStrategyBoardValidator() {
		setupValidBoardConfiguration();
	}
	
	/**
	 * Set up the allowable piece : count map 
	 * 		
	 */
	protected abstract void setupValidBoardConfiguration();
	
	/**
	 * @see StrategyBoardValidator#getGameSpecificPieces()
	 */
	public abstract Collection<PieceLocationDescriptor> getGameSpecificPieces();

	/**
	 * @see strategy.game.version.StrategyBoardValidator#isValidInitialSetup(Collection, Collection)
	 */
	@Override
	public boolean isValidInitialSetup(Collection<PieceLocationDescriptor> redConfig,
			Collection<PieceLocationDescriptor> blueConfig) {
		
		if(redConfig.size() != (MAX_PIECES >> 1)) {//MAX_PIECES / 2 (thank Jarvis for that one!)
			return false;
		}
		
		if(blueConfig.size() != (MAX_PIECES >> 1)) {
			return false;
		}
		
		final List<Location> seenLocations = new ArrayList<Location>();
		final Map<PieceType, Integer> bluePieceCount = new HashMap<PieceType, Integer>();
		final Map<PieceType, Integer> redPieceCount = new HashMap<PieceType, Integer>();

		//check indivisual pieces
		//Check for red out of bounds/offsides
		for (PieceLocationDescriptor redPiece : redConfig) {
			int x = redPiece.getLocation().getCoordinate(Coordinate.X_COORDINATE);
			int y = redPiece.getLocation().getCoordinate(Coordinate.Y_COORDINATE);
			
			if (x > MAX_X || x < MIN_X || y > MAX_Y || y < MIN_Y) {
				return false;
			}
			
			if (y > MAX_RED_STARTING_Y) {
				return false;
			}
			
			if(seenLocations.contains(redPiece.getLocation())) {
				return false;
			}
			
			seenLocations.add(redPiece.getLocation());
			
			if(redPieceCount.containsKey(redPiece.getPiece().getType()))
			{
				redPieceCount.put(redPiece.getPiece().getType(), 
						redPieceCount.get(redPiece.getPiece().getType()) + 1);
			}
			else
			{
				redPieceCount.put(redPiece.getPiece().getType(), 1);
			}
		}
		
		if(!hasRightNumberOfPieces(redPieceCount, bluePieceCount))
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks that each side has the right piece count
	 * @param redPieceCount the red piece count that has been tallied
	 * @param bluePieceCount the blue piece count that has been tallied
	 * @return whether or not both sides have the right number of pieces.
	 */
	private boolean hasRightNumberOfPieces(Map<PieceType, Integer> redPieceCount, Map<PieceType, Integer> bluePieceCount)
	{
		//Check for right number of pieces.
		for(PieceType piece : validPieceCount.keySet())
		{
			int requiredNumber = validPieceCount.get(piece);
			
			Integer redCount = redPieceCount.get(piece);
			Integer blueCount = bluePieceCount.get(piece);
			
			if(redCount == null || blueCount == null)
			{
				return false;
			}
			
			if(redCount != requiredNumber || redCount != requiredNumber)
			{
				return false;
			}
		}
		
		return true;
	}

}
