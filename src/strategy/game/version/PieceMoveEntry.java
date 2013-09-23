package strategy.game.version;

import strategy.game.common.Location;
import strategy.game.common.Piece;


/**
 * This class is a data structure that defines a past move that a piece has made.
 * this is necessary for removing piece move ambiguity.
 * @author danrobertson, cbotaish
 *
 */
public class PieceMoveEntry {

	final private Piece movePiece;
	final private Location fromLocation;
	final private Location toLocation;
	
	public PieceMoveEntry(Piece movePiece, Location fromLocation, Location toLocation) {
		this.movePiece = movePiece;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}
	
	@Override
	public boolean equals(Object other){
		if (other == this) return true;
		if (!(other instanceof PieceMoveEntry)) return false;
		final PieceMoveEntry that = (PieceMoveEntry) other;;
		return (movePiece.equals(that.movePiece) && fromLocation.equals(that.fromLocation)
				&& toLocation.equals(that.toLocation));
	}
	
}
