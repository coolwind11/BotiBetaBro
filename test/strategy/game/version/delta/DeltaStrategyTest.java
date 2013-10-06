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
import static org.junit.Assert.fail;
import static strategy.common.PlayerColor.*;
import static strategy.game.common.PieceType.*;
import static strategy.game.version.gamma.testutil.TestLocations.badLoc;
import static strategy.game.version.gamma.testutil.TestLocations.loc00;
import static strategy.game.version.gamma.testutil.TestLocations.loc01;
import static strategy.game.version.gamma.testutil.TestLocations.loc02;
import static strategy.game.version.gamma.testutil.TestLocations.loc03;
import static strategy.game.version.gamma.testutil.TestLocations.loc04;
import static strategy.game.version.gamma.testutil.TestLocations.loc13;
import static strategy.game.version.gamma.testutil.TestLocations.loc14;
import static strategy.game.version.gamma.testutil.TestLocations.loc15;
import static strategy.game.version.gamma.testutil.TestLocations.loc23;
import static strategy.game.version.gamma.testutil.TestLocations.loc24;
import static strategy.game.version.gamma.testutil.TestLocations.loc41;
import static strategy.game.version.gamma.testutil.TestLocations.loc42;
import static strategy.game.version.gamma.testutil.TestLocations.loc51;
import static strategy.game.version.gamma.testutil.TestLocations.loc64;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import strategy.common.StrategyException;
import strategy.game.StrategyGameController;
import strategy.game.StrategyGameFactory;
import strategy.game.common.Location2D;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;
import strategy.game.version.gamma.testutil.TestConfigurationFactory;

/**
 * Test file for DeltaStrategy
 * @author cbotaish, drob
 * @version 9/26/13
 */
public class DeltaStrategyTest {
	private final StrategyGameFactory factory = StrategyGameFactory.getInstance();
	private List<PieceLocationDescriptor> redPieces;
	private List<PieceLocationDescriptor> bluePieces;
	private StrategyGameController game;

	@Before
	public void setup() throws StrategyException{
		redPieces = TestConfigurationFactory.getInstance().getRedDeltaConfiguration();
		bluePieces = TestConfigurationFactory.getInstance().getBlueDeltaConfiguration();
		game = factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test
	(expected=StrategyException.class)
	public void nullConfigTest() throws StrategyException
	{
		factory.makeDeltaStrategyGame(null,null);
	}
	
	@Test
	public void validConfigTest() throws StrategyException
	{
		assertNotNull(game);
	}

	@Test
	public void startGameTest() throws StrategyException
	{
		assertNotNull(game);
		game.startGame();
	}
	
	@Test
	(expected=StrategyException.class)
	public void startGameTwice() throws StrategyException
	{
		game.startGame();
		game.startGame();
	}
	
	@Test
	(expected=StrategyException.class)
	public void moveFlag() throws StrategyException
	{
		game.startGame();
		game.move(PieceType.FLAG, loc03, loc04);
	}
	
	@Test
	public void validMove() throws StrategyException
	{
		game.startGame();
		MoveResult result = game.move(PieceType.SERGEANT, loc13, loc14);
		assertEquals(MoveResultStatus.OK, result.getStatus());
	}
	
	@Test(expected=StrategyException.class)
	public void notEnoughStartingRedPieces() throws StrategyException
	{
		redPieces.remove(5);
		factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void tooManyBluePieces() throws StrategyException
	{
		redPieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), loc03));
		factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void bluePieceOnRedSide() throws StrategyException{
		redPieces.remove(1);
		bluePieces.add(new PieceLocationDescriptor(new Piece(MARSHAL,BLUE), loc01));
		factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void placeRedPieceInvalidYCoordinate() throws StrategyException
	{
		redPieces.remove(1);
		redPieces.add(new PieceLocationDescriptor(new Piece(MARSHAL, RED), badLoc));
		factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void placeRedPieceInvalidXCoordinate() throws StrategyException
	{
		redPieces.remove(1);
		redPieces.add(new PieceLocationDescriptor(new Piece(MARSHAL, RED), new Location2D(-1, 0)));
		factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void placeBluePieceInvalidStartCoordinate() throws StrategyException
	{
		bluePieces.remove(11);
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), loc02));
		factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void placeBluePieceOtherInvalidStartCoordinate() throws StrategyException
	{
		bluePieces.remove(11);
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), loc64));
		factory.makeDeltaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void badConfigurationWithOverlappingPiece() throws StrategyException{
		bluePieces.remove(0);
 		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), loc04));
 		factory.makeDeltaStrategyGame(redPieces, bluePieces);
		fail();
	}
	
	@Test(expected=StrategyException.class)
	public void invalidConfigNotEnoughOfPieceType() throws StrategyException{
		bluePieces.remove(0);
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), loc04));
 		factory.makeDeltaStrategyGame(redPieces, bluePieces);
		fail();
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeValidMoveBeforeGameStarts() throws StrategyException
	{		
		game.move(SERGEANT, new Location2D(4,1), loc42);
	}
	
	@Test
	public void getPieceAtTest() throws StrategyException
	{
		assertEquals(new Piece(SCOUT, RED), game.getPieceAt(loc51));
	}
	
	
	@Test
	public void makeValidMove() throws StrategyException
	{
		game.startGame();
		
		game.move(SERGEANT, loc41, loc42);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardNegativeY() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,-1), loc00);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardNegativeX() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, new Location2D(-1,0), loc00);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardOverX() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, new Location2D(10,0), loc00);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardOverY() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,10), loc00);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardNegativeY() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, loc00, new Location2D(0,-1));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardNegativeX() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, loc00, new Location2D(-1,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardOverX() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, loc00, new Location2D(10,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardOverY() throws StrategyException
	{
		game.startGame();
		
		game.move(MARSHAL, loc00, new Location2D(0,10));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveOnSelf() throws StrategyException
	{
		game.startGame();
		
		game.move(PieceType.MINER, loc00, loc00);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveOnChokepoint() throws StrategyException
	{		
		game.startGame();
		
		game.move(PieceType.SCOUT, loc23, loc24);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveMoveTooFar() throws StrategyException
	{		
		game.startGame();
		
		game.move(SERGEANT, loc13, loc15);
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveMoveDiagonally() throws StrategyException
	{		
		game.startGame();
		
		game.move(SERGEANT, loc13, loc04);
	}
	
	/*
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveOntoOwnTeam() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(3,1), new Location2D(4,1));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveDueToMoveRepitition() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,2));
		game.move(SERGEANT, new Location2D(4,4), new Location2D(4,3));
		game.move(SERGEANT, new Location2D(4,2), new Location2D(4,1));
		game.move(SERGEANT, new Location2D(4,3), new Location2D(4,4));
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,2));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveMoveFlag() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(FLAG, new Location2D(0,1), new Location2D(0,2));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveOutOfTurn() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(4,4), new Location2D(4,3));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveWrongPieceType() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(FLAG, new Location2D(4,1), new Location2D(4,2));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveNoPiece() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(FLAG, new Location2D(4,3), new Location2D(4,4));
	}
		
		
	@Test
	public void makeValidMoveAlmostRepitition() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,2));
		game.move(SERGEANT, new Location2D(4,4), new Location2D(4,3));
		game.move(SERGEANT, new Location2D(4,2), new Location2D(4,1));
		game.move(SERGEANT, new Location2D(4,3), new Location2D(4,4));
		game.move(SERGEANT, new Location2D(5,1), new Location2D(5,2));
		game.move(LIEUTENANT, new Location2D(1,4), new Location2D(1,3));
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,2));

	}
	
	@Test
	public void redWinABattle() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(5,1), new Location2D(5,2));
		game.move(SERGEANT, new Location2D(0,4), new Location2D(0,3));
		game.move(LIEUTENANT, new Location2D(1,1), new Location2D(1,2));
		game.move(SERGEANT, new Location2D(0,3), new Location2D(0,2));
		MoveResult result = game.move(LIEUTENANT, new Location2D(1,2), new Location2D(0,2));

		assertEquals(MoveResultStatus.OK, result.getStatus());
		assertEquals(new PieceLocationDescriptor(new Piece(LIEUTENANT, RED), new Location2D(0,2)), result.getBattleWinner());
	}
	
	@Test
	public void redLoseABattle() throws StrategyException
	{		
		bluePieces.remove(11);
		bluePieces.add(new PieceLocationDescriptor(new Piece(CAPTAIN, BLUE), new Location2D(0,4)));
		bluePieces.remove(4);
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(3,5)));
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(LIEUTENANT, new Location2D(1,1), new Location2D(1,2));
		game.move(CAPTAIN, new Location2D(0,4), new Location2D(0,3));
		game.move(LIEUTENANT, new Location2D(1,2), new Location2D(1,3));
		MoveResult result = game.move(CAPTAIN, new Location2D(0,3), new Location2D(1,3));
		
		assertEquals(MoveResultStatus.OK, result.getStatus());
		assertEquals(new PieceLocationDescriptor(new Piece(CAPTAIN, BLUE), new Location2D(1,3)), result.getBattleWinner());
	}
	
	@Test
	public void blueWinAGame() throws StrategyException
	{		
		redPieces.remove(11);
		redPieces.remove(0);
		redPieces.add(new PieceLocationDescriptor(new Piece(FLAG, RED), new Location2D(5,1)));
		redPieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, RED), new Location2D(0,1)));
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(0,1), new Location2D(0,2));
		game.move(SERGEANT, new Location2D(4,4), new Location2D(4,3));
		game.move(LIEUTENANT, new Location2D(1,1), new Location2D(1,2)); //red
		game.move(SERGEANT, new Location2D(4,3), new Location2D(4,2)); //blue
		game.move(LIEUTENANT, new Location2D(1,2), new Location2D(1,1)); //red
		game.move(SERGEANT, new Location2D(4,2), new Location2D(5,2));
		game.move(SERGEANT, new Location2D(0,2), new Location2D(0,1));

		MoveResult result = game.move(SERGEANT, new Location2D(5,2), new Location2D(5,1));

		assertEquals(MoveResultStatus.BLUE_WINS, result.getStatus());
	}
	
	@Test
	public void redWinAGame() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		
		game.move(SERGEANT, new Location2D(5,1), new Location2D(5,2));
		
		game.move(SERGEANT, new Location2D(0,4), new Location2D(0,3));
		
		game.move(SERGEANT, new Location2D(5,2), new Location2D(5,3)); //red
		game.move(SERGEANT, new Location2D(0,3), new Location2D(0,2)); //blue
		MoveResult result = game.move(SERGEANT, new Location2D(5,3), new Location2D(5,4)); //red
		
		assertEquals(MoveResultStatus.RED_WINS, result.getStatus());
	}
	
	@Test
	public void drawABattle() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,2));
		game.move(SERGEANT, new Location2D(4,4), new Location2D(4,3));
		MoveResult result = game.move(SERGEANT, new Location2D(4,2), new Location2D(4,3));
		
		assertEquals(MoveResultStatus.OK, result.getStatus());
		assertEquals(null, result.getBattleWinner());
	}
	
	@Test
	(expected=StrategyException.class)
	public void moveChokePoint() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(CHOKE_POINT, new Location2D(3,2), new Location2D(3,1));
	}
	
	@Test
	public void pieceMoveEntryTest()
	{
		PieceMoveEntry entry = new PieceMoveEntry(new Piece(SERGEANT, BLUE), new Location2D(0,0), new Location2D(0,1));
		PieceMoveEntry entry2 = new PieceMoveEntry(new Piece(SERGEANT, BLUE), new Location2D(0,0), new Location2D(0,1));

		assertTrue(!entry.equals("Test"));
		assertTrue(entry.equals(entry));
		assertTrue(entry.equals(entry2));
		assertTrue(entry.hashCode() == entry2.hashCode());
	}
		
	@Test
	public void moveRememberatorRemembersPastThreeMoves() {
		StrategyMoveRememberator pastMoves = new StrategyMoveRememberator(3);
		
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,1)));
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,0)));
		assertTrue(pastMoves.isMoveInList(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,1))));
	}
	
	@Test
	public void moveRememberatorDoesntRememberMovesItDidntMake() {
		StrategyMoveRememberator pastMoves = new StrategyMoveRememberator(3);
		
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,1)));
		pastMoves.addMove(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(0,0)));
		assertTrue(!pastMoves.isMoveInList(new PieceMoveEntry(new Piece(SERGEANT,BLUE), new Location2D(0,0), new Location2D(1,0))));
	}*/
}
