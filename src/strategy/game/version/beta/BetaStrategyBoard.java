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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import strategy.common.PlayerColor;
import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.common.StrategyBoard;

/**
 * Provides a representation of a strategy board.
 * @author Chris Botaish, Dan Robertson
 * @version Sep 9, 2013
 */
public class BetaStrategyBoard implements StrategyBoard
{
	private final int MAX_PIECE_COUNT = 24;
	private final int MIN_BLUE_POS = 4;
	private final int MAX_RED_POS = 1;
	private final int MIN_X = 0;
	private final int MAX_X = 5;
	private final int MIN_Y = 0;
	private final int MAX_Y = 5;
	
	/**
	 * Describes the valid initial setup for pieces including 
	 * the piece types that are valid and the number of those
	 * pieces that should be present.
	 */
	private final Map<PieceType, Integer> validPieceSetup = new HashMap<PieceType, Integer>();
	
	private final Map<Location, Piece> boardRepresentationMap = new HashMap<Location, Piece>();
	private Collection<PieceLocationDescriptor> initialPieceLocations = null;
	
	
	/**
	 * Creates an empty board with the given dimensions
	 * @param initialPieces the initial location of the pieces on the board.
	 */
	public BetaStrategyBoard(Collection<PieceLocationDescriptor> initialPieces)
	{
		initialPieceLocations = initialPieces;
		
		validPieceSetup.put(PieceType.FLAG, 1);
		validPieceSetup.put(PieceType.MARSHAL, 1);
		validPieceSetup.put(PieceType.COLONEL, 2);
		validPieceSetup.put(PieceType.CAPTAIN, 2);
		validPieceSetup.put(PieceType.LIEUTENANT, 3);
		validPieceSetup.put(PieceType.SERGEANT, 3);
		
		resetBoard();
	}
	
	/**
	 * @see strategy.game.common.StrategyBoard#getPieceAt(Location)
	 */
	@Override
	public Piece getPieceAt(Location location) {
		return boardRepresentationMap.get(location);
	}
	
	/**
	 * @see strategy.game.common.StrategyBoard#movePiece(Location, Location)
	 */
	@Override
	public void movePiece(Location from, Location to) 
	{
		boardRepresentationMap.put(to, boardRepresentationMap.get(from));
		boardRepresentationMap.remove(from);
	}
	
	/**
	 * @see strategy.game.common.StrategyBoard#removePiece(Location)
	 */
	@Override
	public void removePiece(Location fromLocation)
	{
		boardRepresentationMap.remove(fromLocation);
	}
	
	/**
	 * @see strategy.game.common.StrategyBoard#resetBoard()
	 */
	@Override
	public void resetBoard() 
	{
		boardRepresentationMap.clear();
		for (PieceLocationDescriptor pieceLocation : initialPieceLocations) {
			boardRepresentationMap.put(pieceLocation.getLocation(), pieceLocation.getPiece());
		}
	}

	/**
	 * @see strategy.game.common.StrategyBoard#hasValidInitialBoardSetup()
	 */
	@Override
	public boolean hasValidInitialBoardSetup()
	{
		boolean isValid = true;
		//Check for right number of pieces.
		if(initialPieceLocations.size() > MAX_PIECE_COUNT)
		{
			isValid = false;
		}
		
		//Maps to hold the count of pieces on the board.
		final Map<PieceType, Integer> redPieceCountMap = new HashMap<PieceType, Integer>();
		final Map<PieceType, Integer> bluePieceCountMap = new HashMap<PieceType, Integer>();

		//Check if any of the pieces are invalid.
		for(PieceLocationDescriptor pieceOnBoard : initialPieceLocations) 
		{
			//Check for valid piece type
			if(!validPieceSetup.containsKey(pieceOnBoard.getPiece().getType()))
			{
				isValid = false;
			}
			
			//Check for offsides pieces or overlapping placements.
			for(PieceLocationDescriptor otherPiece : initialPieceLocations)
			{
				if(pieceOnBoard.equals(otherPiece))
				{
					continue;
				}
				
				if(pieceOnBoard.getLocation().equals(otherPiece.getLocation()))
				{
					isValid = false;
				}
			}
			
			//Check for offsides 
			if(pieceOnBoard.getPiece().getOwner() == PlayerColor.RED) {
				if(pieceOnBoard.getLocation().getCoordinate(Coordinate.Y_COORDINATE) 
						> MAX_RED_POS) {
					isValid = false;
				}
			} else {
				if(pieceOnBoard.getLocation().getCoordinate(Coordinate.Y_COORDINATE) 
						< MIN_BLUE_POS) {
					isValid = false;
				}
			}
			
			//Check for valid column
			if(pieceOnBoard.getLocation().getCoordinate(Coordinate.X_COORDINATE) < MIN_X 
					|| pieceOnBoard.getLocation().getCoordinate(Coordinate.X_COORDINATE) > MAX_X)
			{
				isValid = false;
			}
			//Check for valid row
			if(pieceOnBoard.getLocation().getCoordinate(Coordinate.Y_COORDINATE) < MIN_Y 
					|| pieceOnBoard.getLocation().getCoordinate(Coordinate.Y_COORDINATE) > MAX_Y)
			{
				isValid = false;
			}
			
			//Record how many of each piece there is.
			Map<PieceType, Integer> pieceCountMap = pieceOnBoard.getPiece().getOwner()
					== PlayerColor.RED ? redPieceCountMap : bluePieceCountMap;
			int currentCountForPiece = 0;
			if(pieceCountMap.containsKey(pieceOnBoard.getPiece().getType()))
			{
				currentCountForPiece = pieceCountMap.get(pieceOnBoard.getPiece().getType());
			}

			//Record the number of each piece.
			pieceCountMap.put(pieceOnBoard.getPiece().getType(), currentCountForPiece + 1);
		}
		
		//Check that there are the right numbers of every piece.
		for(Entry<PieceType, Integer> pieceCount : validPieceSetup.entrySet())
		{
			if(redPieceCountMap.get(pieceCount.getKey()) != pieceCount.getValue())
			{
				isValid = false;
			}
			
			if(bluePieceCountMap.get(pieceCount.getKey()) != pieceCount.getValue())
			{
				isValid = false;
			}
		}
		
		return isValid;
	}
	
	
}
