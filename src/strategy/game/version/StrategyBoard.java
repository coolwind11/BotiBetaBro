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

import strategy.common.PlayerColor;
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
	private int redPieceCount;
	private int bluePieceCount;
	
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
	 * retrieve a piece from the board at the given location
	 * @param location the location too grab the piece from
	 * @return
	 */
	public Piece getPieceAt(Location location) {
		return boardRepresentationMap.get(location);
	}
	
	/**
	 * Move a piece from one location to the other
	 * @param from the location to move from
	 * @param to the location to move to
	 */
	public void movePiece(Location from, Location to) 
	{
		if (boardRepresentationMap.containsKey(to)) {
			removePiece(to);
		}
		boardRepresentationMap.put(to, boardRepresentationMap.get(from));
		boardRepresentationMap.remove(from);
	}
	
	/**
	 * Remove the piece at the supplied location from the board map. Also decrement the piece counter for
	 * the removed piece's color
	 * @param fromLocation the location to remove from
	 */
	public void removePiece(Location fromLocation)
	{
		if(getPieceAt(fromLocation).getOwner() == PlayerColor.BLUE){
			bluePieceCount--;
		} else {
			redPieceCount--;
		}
		boardRepresentationMap.remove(fromLocation);
	}
	
	public int getPieceCount(PlayerColor forColor) {
		return forColor == PlayerColor.BLUE ? bluePieceCount : redPieceCount;
	}
}



