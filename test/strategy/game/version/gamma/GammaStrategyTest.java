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

import static org.junit.Assert.*;
import static strategy.common.PlayerColor.*;
import static strategy.game.common.PieceType.*;

import java.util.*;

import org.junit.*;

import strategy.common.*;
import strategy.game.StrategyGameFactory;
import strategy.game.common.*;


public class GammaStrategyTest {

	private ArrayList<PieceLocationDescriptor> redConfiguration;
	private ArrayList<PieceLocationDescriptor> blueConfiguration;
	private final static StrategyGameFactory factory = StrategyGameFactory.getInstance();
	
	@Before
	public void setup()
	{
		redConfiguration = new ArrayList<PieceLocationDescriptor>();
		blueConfiguration = new ArrayList<PieceLocationDescriptor>();
		addToConfiguration(FLAG, RED, 0, 1);
		addToConfiguration(MARSHAL, RED, 0, 0);
		addToConfiguration(COLONEL, RED, 1, 0);
		addToConfiguration(COLONEL, RED, 2, 0);
		addToConfiguration(CAPTAIN, RED, 3, 0);
		addToConfiguration(CAPTAIN, RED, 4, 0);
		addToConfiguration(LIEUTENANT, RED, 5, 0);
		addToConfiguration(LIEUTENANT, RED, 1, 1);
		addToConfiguration(LIEUTENANT, RED, 2, 1);
		addToConfiguration(SERGEANT, RED, 3, 1);
		addToConfiguration(SERGEANT, RED, 4, 1);
		addToConfiguration(SERGEANT, RED, 5, 1);
		
		addToConfiguration(FLAG, BLUE, 5, 4);
		addToConfiguration(MARSHAL, BLUE, 0, 5);
		addToConfiguration(COLONEL, BLUE, 1, 5);
		addToConfiguration(COLONEL, BLUE, 2, 5);
		addToConfiguration(CAPTAIN, BLUE, 3, 5);
		addToConfiguration(CAPTAIN, BLUE, 4, 5);
		addToConfiguration(LIEUTENANT, BLUE, 5, 5);
		addToConfiguration(LIEUTENANT, BLUE, 1, 4);
		addToConfiguration(LIEUTENANT, BLUE, 2, 4);
		addToConfiguration(SERGEANT, BLUE, 3, 4);
		addToConfiguration(SERGEANT, BLUE, 4, 4);
		addToConfiguration(SERGEANT, BLUE, 0, 4);
	}
	
	@Test(expected=StrategyException.class)
	public void cannotCreateGammaStrategyWithNullConfigurations() throws StrategyException
	{
		factory.makeGammaStrategyGame(null, null);
	}
	
	@Test(expected=StrategyException.class)
	public void redConfigurationHasTooFewItem() throws StrategyException
	{
		redConfiguration.remove(0);
		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
	}
	
	@Test(expected=StrategyException.class)
	public void blueConfigurationHasTooManyItems() throws StrategyException
	{
		addToConfiguration(SERGEANT, BLUE, 0, 3);
		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
	}
	
	@Test(expected=StrategyException.class)
	public void bluePieceOnRedSide() throws StrategyException{
		redConfiguration.remove(1);
		addToConfiguration(MARSHAL,BLUE,0,0);
		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
	}
	
	@Test
	public void createGammaStrategyController() throws StrategyException
	{
		assertNotNull(factory.makeGammaStrategyGame(redConfiguration, blueConfiguration));
	}
	
	@Test(expected=StrategyException.class)
	public void placeRedPieceOnInvalidRow() throws StrategyException
	{
		redConfiguration.remove(1);	// Marshall @ (0, 0)
		addToConfiguration(MARSHAL, RED, 0, 3);
		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
	}
	
	@Test(expected=StrategyException.class)
	public void placeRedPieceOnInvalidColumn() throws StrategyException
	{
		redConfiguration.remove(1);	// Marshall @ (0, 0)
		addToConfiguration(MARSHAL, RED, -1, 0);
		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
	}
	
	@Test(expected=StrategyException.class)
	public void placeBluePieceOnInvalidRow() throws StrategyException
	{
		blueConfiguration.remove(11);	// Sergeant @ (0, 4)
		addToConfiguration(SERGEANT, BLUE, 0, 2);
		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
	}
	
	@Test(expected=StrategyException.class)
	public void placeBluePieceOnInvalidColumn() throws StrategyException
	{
		blueConfiguration.remove(11);	// Sergeant @ (0, 4)
		addToConfiguration(SERGEANT, BLUE, 6, 4);
		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
	}
	
	@Test(expected=StrategyException.class)
	public void invalidConfigWithOverlappingPiece() throws StrategyException{
		blueConfiguration.remove(0);
 		addToConfiguration(SERGEANT, BLUE, 0, 4);
 		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
		fail();
	}
	
	@Test(expected=StrategyException.class)
	public void invalidConfigWrongNumberOfPieces() throws StrategyException{
		blueConfiguration.remove(0);
		addToConfiguration(SERGEANT, BLUE, 0, 4);
 		factory.makeGammaStrategyGame(redConfiguration, blueConfiguration);
		fail();
	}
	
	@Test
	public void moveRememberatorRemembersPastThreeMoves() {
		StrategyMoveRememberator pastMoves = new StrategyMoveRememberator(3);
		
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,1)));
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,0)));
		assertTrue(pastMoves.moveInList(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,1))));
	}
	
	@Test
	public void moveRememberatorDoesntRememberMovesItDidntMake() {
		StrategyMoveRememberator pastMoves = new StrategyMoveRememberator(3);
		
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,1)));
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,0)));
		assertTrue(!pastMoves.moveInList(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(1,0))));
	}
	// Helper methods
	private void addToConfiguration(PieceType type, PlayerColor color, int x, int y)
	{
		final PieceLocationDescriptor confItem = new PieceLocationDescriptor(
				new Piece(type, color),
				new Location2D(x, y));
		if (color == PlayerColor.RED) {
			redConfiguration.add(confItem);
		} else {
			blueConfiguration.add(confItem);
		}
	}

}
