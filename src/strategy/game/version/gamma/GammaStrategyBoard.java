/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package strategy.game.version.gamma;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import strategy.common.PlayerColor;
import strategy.game.common.Coordinate;
import strategy.game.common.Location;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.common.StrategyBoard;

import java.util.Collection;
import java.util.Map.Entry;
public class GammaStrategyBoard implements StrategyBoard {
	
	/**
	 * Create an instance of a GammaStrategyBoard
	 * @param initialConfig The collection of red and blue pieces initially provided
	 */
	public GammaStrategyBoard(Collection<PieceLocationDescriptor> initialConfig) {
		
	}

	@Override
	public Piece getPieceAt(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void movePiece(Location from, Location to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePiece(Location fromLocation) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setupBoard() {
		// TODO Re-Auto-generate method stub
	}

}
