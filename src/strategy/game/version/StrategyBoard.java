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
	
	private int redPieceCount, bluePieceCount;
	
	/**
	 * Creates an empty board with the given dimensions
	 * @param initialPieces the initial location of the pieces on the board.
	 */
	public StrategyBoard(Collection<PieceLocationDescriptor> initialPieces)
	{
		redPieceCount = 0;
		bluePieceCount = 0;
		for (PieceLocationDescriptor pieceLocation : initialPieces) {
			boardRepresentationMap.put(pieceLocation.getLocation(), pieceLocation.getPiece());
			if (pieceLocation.getPiece().getOwner() == PlayerColor.BLUE){
				bluePieceCount++;
			} else if (pieceLocation.getPiece().getOwner() == PlayerColor.RED){
				redPieceCount++;
			}
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
		if (boardRepresentationMap.containsKey(to)){
			removePiece(to);
		}
		boardRepresentationMap.put(to, boardRepresentationMap.get(from));
		boardRepresentationMap.remove(from);
	}
	
	/**
	 * @see strategy.game.version.StrategyBoard#removePiece(Location)
	 */
	public void removePiece(Location fromLocation)
	{
		if (getPieceAt(fromLocation).getOwner() == PlayerColor.BLUE){
			bluePieceCount--;
		} else {
			redPieceCount--;
		}
		boardRepresentationMap.remove(fromLocation);
	}
	
	/**
	 * return the remaining number of pieces for the supplied player
	 * @param forColor the player to check piece count for
	 * @return the remaining piece count for that player
	 */
	public int getRemainingPieceCount(PlayerColor forColor) {
		return forColor == PlayerColor.BLUE ? bluePieceCount : redPieceCount;
	}
	
	/**
	 * get the remaining pieces on the board for a player of given color
	 * @param forColor the color of the player
	 * @return the remaining pieces on the board for a given player.
	 */
	public Collection<Piece> getRemainingPieces(PlayerColor forColor) {
		final Collection<Piece> remaining = new ArrayList<Piece>();
		for (Piece piece : boardRepresentationMap.values()) {
			if (piece.getOwner() == forColor) {
				remaining.add(piece);
			}
		}
		
		return remaining;
	}
}



