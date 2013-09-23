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

import strategy.game.common.Location;
import strategy.game.common.Piece;


/**
 * This class is a data structure that defines a past move that a piece has made.
 * this is necessary for removing piece move ambiguity.
 * @author danrobertson, cbotaish
 * @version Sept 22, 2013
 */
public class PieceMoveEntry {

	private final Piece movePiece;
	private final Location fromLocation;
	private final Location toLocation;
	
	/**
	 * Creates a new PieceMoveEntry with the given piece and locations
	 * @param movePiece the piece that is being moved
	 * @param fromLocation the location the piece is being moved from
	 * @param toLocation the location the piece is being moved to.
	 */
	public PieceMoveEntry(Piece movePiece, Location fromLocation, Location toLocation) {
		this.movePiece = movePiece;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}
	
	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object other){
		if (other == this) return true;
		if (!(other instanceof PieceMoveEntry)) return false;
		final PieceMoveEntry that = (PieceMoveEntry) other;
		return (movePiece.equals(that.movePiece) && fromLocation.equals(that.fromLocation)
				&& toLocation.equals(that.toLocation));
	}
	
	/**
	 * @see Object#hashCode()
	 */
	@Override 
	public int hashCode()
	{
		return movePiece.hashCode() + fromLocation.hashCode() + toLocation.hashCode();
	}
	
}
