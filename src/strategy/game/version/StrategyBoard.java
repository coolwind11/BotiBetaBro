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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import strategy.game.common.Location;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;

/**
 * Provides an interface to a strategy board.
 * @author Chris Botaish, Dan Robertson
 * @version Sep 9, 2013
 */
public class StrategyBoard
{
	private final Map<Location, Piece> boardRepresentationMap = new HashMap<Location, Piece>();
	
	/**
	 * Creates an empty board with the given dimensions
	 * @param initialPieces the initial location of the pieces on the board.
	 */
	public StrategyBoard(Collection<PieceLocationDescriptor> initialPieces)
	{
		for (PieceLocationDescriptor pieceLocation : initialPieces) {
			boardRepresentationMap.put(pieceLocation.getLocation(), pieceLocation.getPiece());
		}
	}
	
	/**
	 * @see strategy.game.version.StrategyBoard#getPieceAt(Location)
	 */
	public Piece getPieceAt(Location location) {
		return boardRepresentationMap.get(location);
	}
	
	/**
	 * @see strategy.game.version.StrategyBoard#movePiece(Location, Location)
	 */
	public void movePiece(Location from, Location to) 
	{
		boardRepresentationMap.put(to, boardRepresentationMap.get(from));
		boardRepresentationMap.remove(from);
	}
	
	/**
	 * @see strategy.game.version.StrategyBoard#removePiece(Location)
	 */
	public void removePiece(Location fromLocation)
	{
		boardRepresentationMap.remove(fromLocation);
	}
}



