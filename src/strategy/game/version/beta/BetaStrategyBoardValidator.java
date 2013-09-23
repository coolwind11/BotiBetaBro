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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.common.StrategyBoardValidator;

/**
 * This class provides validations for the initial board setups.
 * @author drob, cbotaish
 *
 */
public class BetaStrategyBoardValidator implements StrategyBoardValidator {

	private final int MAX_PIECES = 24;
	private final int MAX_X = 5;
	private final int MAX_Y = 5;
	private final int MIN_X = 0;
	private final int MIN_Y = 0;
	
	private final int MIN_BLUE_STARTING_Y = 4;
	private final int MAX_RED_STARTING_Y = 1;
	
	private final Map<PieceType,Integer> validPieceCount;	
	
	/**
	 * Creates a Gamma Strategy board validator
	 */
	public BetaStrategyBoardValidator() {
		validPieceCount = new HashMap<PieceType, Integer>();
		validPieceCount.put(PieceType.FLAG, 1);
		validPieceCount.put(PieceType.MARSHAL, 1);
		validPieceCount.put(PieceType.COLONEL, 2);
		validPieceCount.put(PieceType.CAPTAIN, 2);
		validPieceCount.put(PieceType.LIEUTENANT, 3);
		validPieceCount.put(PieceType.SERGEANT, 3);
	}
	
	/**
	 * @see strategy.game.common.StrategyBoardValidator#isValidInitialSetup(Collection, Collection)
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
				redPieceCount.put(redPiece.getPiece().getType(), redPieceCount.get(redPiece.getPiece().getType()) + 1);
			}
			else
			{
				redPieceCount.put(redPiece.getPiece().getType(), 1);
			}
		}
		
		//Check for blue out of bounds/offsides
		for (PieceLocationDescriptor bluePiece : blueConfig) {
			int x = bluePiece.getLocation().getCoordinate(Coordinate.X_COORDINATE);
			int y = bluePiece.getLocation().getCoordinate(Coordinate.Y_COORDINATE);
			
			if (x > MAX_X || x < MIN_X || y > MAX_Y || y < MIN_Y) {
				return false;
			}
			
			if (y < MIN_BLUE_STARTING_Y) {
				return false;
			}
			
			if(seenLocations.contains(bluePiece.getLocation())) {
				return false;
			}
			seenLocations.add(bluePiece.getLocation());
			
			if(bluePieceCount.containsKey(bluePiece.getPiece().getType()))
			{
				bluePieceCount.put(bluePiece.getPiece().getType(), redPieceCount.get(bluePiece.getPiece().getType()) + 1);
			}
			else
			{
				bluePieceCount.put(bluePiece.getPiece().getType(), 1);
			}
		}
		
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
