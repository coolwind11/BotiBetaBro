/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package strategy.game.version.delta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static strategy.common.PlayerColor.BLUE;
import static strategy.common.PlayerColor.RED;
import static strategy.game.common.PieceType.CAPTAIN;
import static strategy.game.common.PieceType.COLONEL;
import static strategy.game.common.PieceType.FLAG;
import static strategy.game.common.PieceType.LIEUTENANT;
import static strategy.game.common.PieceType.MARSHAL;
import static strategy.game.common.PieceType.SERGEANT;
import static strategy.game.common.PieceType.CHOKE_POINT;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.StrategyGameController;
import strategy.game.StrategyGameFactory;
import strategy.game.common.Location2D;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.version.PieceMoveEntry;
import strategy.game.version.StrategyMoveRememberator;

/**
 * Test file for GammaStrategy
 * @author cbotaish, drob
 * @version 9/26/13
 */

public class DeltaStrategyTest {
	
	private List<PieceLocationDescriptor> redPieces;
	private List<PieceLocationDescriptor> bluePieces;
	private final static StrategyGameFactory factory = StrategyGameFactory.getInstance();

	void setup(){
		redPieces = new ArrayList<PieceLocationDescriptor>();
		bluePieces = new ArrayList<PieceLocationDescriptor>();
	}
	
	

}
