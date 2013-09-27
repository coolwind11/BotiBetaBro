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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static strategy.common.PlayerColor.BLUE;
import static strategy.common.PlayerColor.RED;
import static strategy.game.common.PieceType.CAPTAIN;
import static strategy.game.common.PieceType.CHOKE_POINT;
import static strategy.game.common.PieceType.COLONEL;
import static strategy.game.common.PieceType.FLAG;
import static strategy.game.common.PieceType.LIEUTENANT;
import static strategy.game.common.PieceType.MARSHAL;
import static strategy.game.common.PieceType.SERGEANT;

import java.util.ArrayList;

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
import strategy.game.version.PieceMoveEntry;
import strategy.game.version.StrategyMoveRememberator;

/**
 * Test file for GammaStrategy
 * @author cbotaish, drob
 * @version 9/23/13
 */
public class GammaStrategyTest {

	private ArrayList<PieceLocationDescriptor> redPieces;
	private ArrayList<PieceLocationDescriptor> bluePieces;
	private final static StrategyGameFactory factory = StrategyGameFactory.getInstance();
	
	@Before
	public void setup()
	{
		redPieces = new ArrayList<PieceLocationDescriptor>();
		bluePieces = new ArrayList<PieceLocationDescriptor>();
		redPieces.add(new PieceLocationDescriptor(new Piece(FLAG, RED),new Location2D(0, 1)));
		redPieces.add(new PieceLocationDescriptor(new Piece(MARSHAL, RED),new Location2D(0, 0)));
		redPieces.add(new PieceLocationDescriptor(new Piece(COLONEL, RED),new Location2D(1, 0)));
		redPieces.add(new PieceLocationDescriptor(new Piece(COLONEL, RED),new Location2D(2, 0)));
		redPieces.add(new PieceLocationDescriptor(new Piece(CAPTAIN, RED),new Location2D(3, 0)));
		redPieces.add(new PieceLocationDescriptor(new Piece(CAPTAIN, RED),new Location2D(4, 0)));
		redPieces.add(new PieceLocationDescriptor(new Piece(LIEUTENANT, RED),new Location2D(5, 0)));
		redPieces.add(new PieceLocationDescriptor(new Piece(LIEUTENANT, RED),new Location2D(1, 1)));
		redPieces.add(new PieceLocationDescriptor(new Piece(LIEUTENANT, RED),new Location2D(2, 1)));
		redPieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, RED),new Location2D(3, 1)));
		redPieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, RED),new Location2D(4, 1)));
		redPieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, RED),new Location2D(5, 1)));
		
		bluePieces.add(new PieceLocationDescriptor(new Piece(FLAG, BLUE), new Location2D(5, 4)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(MARSHAL, BLUE), new Location2D(0, 5)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(COLONEL, BLUE), new Location2D(1, 5)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(COLONEL, BLUE), new Location2D(2, 5)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(CAPTAIN, BLUE), new Location2D(3, 5)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(CAPTAIN, BLUE), new Location2D(4, 5)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(LIEUTENANT, BLUE), new Location2D(5, 5)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(LIEUTENANT, BLUE), new Location2D(1, 4)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(LIEUTENANT, BLUE), new Location2D(2, 4)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(3, 4)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(4, 4)));
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(0, 4)));
	}
	
	@Test(expected=StrategyException.class)
	public void cannotCreateGammaStrategyWithNullConfigurations() throws StrategyException
	{
		factory.makeGammaStrategyGame(null, null);
	}
	
	@Test(expected=StrategyException.class)
	public void notEnoughStartingRedPieces() throws StrategyException
	{
		redPieces.remove(5);
		factory.makeGammaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void toManyBluePieces() throws StrategyException
	{
		redPieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(0, 3)));
		factory.makeGammaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void bluePieceOnRedSide() throws StrategyException{
		redPieces.remove(1);
		bluePieces.add(new PieceLocationDescriptor(new Piece(MARSHAL,BLUE),new Location2D(0,0)));
		factory.makeGammaStrategyGame(redPieces, bluePieces);
	}
	
	@Test
	public void createGammaStrategyController() throws StrategyException
	{
		assertNotNull(factory.makeGammaStrategyGame(redPieces, bluePieces));
	}
	
	@Test(expected=StrategyException.class)
	public void placeRedPieceInvalidYCoordinate() throws StrategyException
	{
		redPieces.remove(1);
		redPieces.add(new PieceLocationDescriptor(new Piece(MARSHAL, RED), new Location2D(0, 3)));
		factory.makeGammaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void placeRedPieceInvalidXCoordinate() throws StrategyException
	{
		redPieces.remove(1);
		redPieces.add(new PieceLocationDescriptor(new Piece(MARSHAL, RED), new Location2D(-1, 0)));
		factory.makeGammaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void placeBluePieceInvalidStartCoordinate() throws StrategyException
	{
		bluePieces.remove(11);
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(0, 2)));
		factory.makeGammaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void placeBluePieceOtherInvalidStartCoordinate() throws StrategyException
	{
		bluePieces.remove(11);
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(6, 4)));
		factory.makeGammaStrategyGame(redPieces, bluePieces);
	}
	
	@Test(expected=StrategyException.class)
	public void badConfigurationWithOverlappingPiece() throws StrategyException{
		bluePieces.remove(0);
 		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(0, 4)));
 		factory.makeGammaStrategyGame(redPieces, bluePieces);
		fail();
	}
	
	@Test(expected=StrategyException.class)
	public void invalidConfigNotEnoughOfPieceType() throws StrategyException{
		bluePieces.remove(0);
		bluePieces.add(new PieceLocationDescriptor(new Piece(SERGEANT, BLUE), new Location2D(0, 4)));
 		factory.makeGammaStrategyGame(redPieces, bluePieces);
		fail();
	}
	
	@Test
	public void initializeGame() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
	}
	
	@Test
	(expected=StrategyException.class)
	public void initializeGameTwice() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		game.startGame();
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeValidMoveBeforeGameStarts() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,2));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeValidMoveAfterGameEnds() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		
		game.move(SERGEANT, new Location2D(5,1), new Location2D(5,2));
		
		game.move(SERGEANT, new Location2D(0,4), new Location2D(0,3));
		
		game.move(SERGEANT, new Location2D(5,2), new Location2D(5,3)); //red
		game.move(SERGEANT, new Location2D(0,3), new Location2D(0,2)); //blue
		MoveResult result = game.move(SERGEANT, new Location2D(5,3), new Location2D(5,4)); //red
		
		assertEquals(MoveResultStatus.RED_WINS, result.getStatus());
		
		game.move(SERGEANT, new Location2D(5,3), new Location2D(5,4));
	}
	
	@Test
	public void getPieceAtTest() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		assertEquals(new Piece(SERGEANT, RED), game.getPieceAt(new Location2D(5,1)));
	}
	
	
	@Test
	public void makeValidMove() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,2));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardNegativeY() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,-1), new Location2D(0,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardNegativeX() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(-1,0), new Location2D(0,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardOverX() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(10,0), new Location2D(0,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveFromOffBoardOverY() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,10), new Location2D(0,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardNegativeY() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,0), new Location2D(0,-1));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardNegativeX() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,0), new Location2D(-1,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardOverX() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,0), new Location2D(10,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveToOffBoardOverY() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,0), new Location2D(0,10));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveOnSelf() throws StrategyException
	{
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(MARSHAL, new Location2D(0,0), new Location2D(0,0));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveOnChokepoint() throws StrategyException
	{
		//Chokepoints at: (2,2) (2,3) (3,2) (3,3)
		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(3,1), new Location2D(3,2));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveMoveTooFar() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(4,1), new Location2D(4,3));
	}
	
	@Test
	(expected=StrategyException.class)
	public void makeInvalidMoveMoveDiagonally() throws StrategyException
	{		
		StrategyGameController game = factory.makeGammaStrategyGame(redPieces, bluePieces);
		game.startGame();
		
		game.move(SERGEANT, new Location2D(3,1), new Location2D(4,2));
	}
	
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
	}

}
