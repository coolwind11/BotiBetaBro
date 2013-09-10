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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.StrategyGameController;
import strategy.game.common.Location;
import strategy.game.common.Location2D;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;

/**Description 
 * 
 * @author Dan Robertson, Chris Botaish
 * @version September 9th, 2013
 */
public class BetaStrategyGameTest {
	
	private  StrategyGameController game;
	private List<PieceLocationDescriptor> pieceLocations;

	@Before
	public void setup() {
		pieceLocations = new ArrayList<PieceLocationDescriptor>();
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,2)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,3)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,0)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,1)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,2)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,3)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,5)));
		
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(4,1)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(4,2)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(4,3)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,4)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,5)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,0)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,1)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,2)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,3)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,4)));
		pieceLocations.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,5)));
		
		
		game = new BetaStrategyGameController(pieceLocations);	
	}
	
	@Test
	public void testStartGame() throws StrategyException {
		game.startGame();
		assert(true);
	}
	
	@Test(expected=StrategyException.class)
	public void moveBeforeGameBegins() throws StrategyException {
		Location redMarsh = new Location2D(0,0);
		game.move(PieceType.MARSHAL, redMarsh, redMarsh);
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMove() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL, new Location2D(2,2), new Location2D(2,3));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationOutOfBounds() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(4,4), new Location2D(1000,1000));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationOutOfBounds1() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(4,4), new Location2D(-1,-1));
	}
	
	@Test
	public void makeValidMove() throws StrategyException {
		game.startGame();
		MoveResult result = game.move(PieceType.CAPTAIN, new Location2D(4,4), new Location2D(3,4));
		
		assertEquals(MoveResultStatus.OK,result.getStatus());
	}

}
