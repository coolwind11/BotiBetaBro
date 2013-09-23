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
import org.junit.BeforeClass;
import org.junit.Test;

import strategy.common.PlayerColor;
import strategy.common.StrategyException;
import strategy.game.StrategyGameController;
import strategy.game.StrategyGameFactory;
import strategy.game.common.Location;
import strategy.game.common.Location2D;
import strategy.game.common.MoveResult;
import strategy.game.common.MoveResultStatus;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;

/**
 * Tests for the BetaStrategyGame  
 * @author Dan Robertson, Chris Botaish
 * @version September 9th, 2013
 */
public class BetaStrategyGameTest {
	
	private  StrategyGameController game;
	private List<PieceLocationDescriptor> pieceLocationsBlue,pieceLocationsRed;
	private static StrategyGameFactory gameFactory;
	
	@BeforeClass
	public static void preSetup() throws StrategyException {
		gameFactory = StrategyGameFactory.getInstance();
	}
	@Before
	public void setup() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(2,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(3,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(4,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(5,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(2,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(3,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(4,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(5,5)));
		
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(0,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(1,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(2,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(3,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(5,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(0,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(1,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(2,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(3,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,0)));
		
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		
	}
	
	@Test(expected=StrategyException.class)
	public void startGameWithInvalidBoardMissingPieces() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(4,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(3,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(2,0)));
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void startGameWithInvalidBoardOffsidesPlacement() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(5,5)));
		
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(4,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(4,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(4,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,5)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(1,5)));
		
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void startGameWithInvalidBoardTooManyMarshalls() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(1,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(1,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(1,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(1,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(1,5)));
		
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(4,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(4,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(4,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,5)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,5)));
		
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void startGameWithInvalidBoardNotEnoughMarshalls() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,5)));
		
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(4,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(4,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(4,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,5)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,5)));
		
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void startGameWithInvalidBoardInvalidPiece() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SCOUT, PlayerColor.BLUE), new Location2D(1,5)));
		
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(-1,-1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(8,8)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(4,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,5)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,5)));
		
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void startGameWithInvalidBoardTooManyPieces() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(2,5)));

		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(4,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(4,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(4,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,5)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,5)));
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void startGameWithInvalidBoardOverlappingPlacement() throws StrategyException {
		pieceLocationsBlue = new ArrayList<PieceLocationDescriptor>();
		pieceLocationsRed = new ArrayList<PieceLocationDescriptor>();
		
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Location2D(0,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.BLUE), new Location2D(0,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.BLUE), new Location2D(0,5)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,0)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,1)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE), new Location2D(1,2)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,3)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,4)));
		pieceLocationsBlue.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.BLUE), new Location2D(1,5)));
		
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.FLAG, PlayerColor.RED), new Location2D(4,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.MARSHAL, PlayerColor.RED), new Location2D(4,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(4,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(4,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.CAPTAIN, PlayerColor.RED), new Location2D(4,5)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,0)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,1)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.LIEUTENANT, PlayerColor.RED), new Location2D(5,2)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,3)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.COLONEL, PlayerColor.RED), new Location2D(5,4)));
		pieceLocationsRed.add(new PieceLocationDescriptor(new Piece(PieceType.SERGEANT, PlayerColor.RED), new Location2D(5,5)));
		
		
		game = gameFactory.makeBetaStrategyGame(pieceLocationsRed,pieceLocationsBlue);	
		
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void cannotCreateBetaStrategyWithNullConfigurations() throws StrategyException
	{
		gameFactory.makeBetaStrategyGame(null, null);
	}
	
	@Test(expected=StrategyException.class)
	public void cannotCreateBetaStrategyWithNullConfigurations2() throws StrategyException
	{
		gameFactory.makeBetaStrategyGame(pieceLocationsRed, null);
	}
	
	
	@Test
	public void testStartGame() throws StrategyException {
		game.startGame();
		assert(true);
	}
	
	@Test(expected=StrategyException.class)
	public void makeValidMoveBeforeGameBegins() throws StrategyException {
		Location redMarsh = new Location2D(1,1);
		game.move(PieceType.MARSHAL, redMarsh, new Location2D(1,2));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMove() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL, new Location2D(2,2), new Location2D(2,3));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationToOutOfBoundsOverX() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(1,1), new Location2D(1000,0));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationToOutOfBoundsOverY() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(1,1), new Location2D(0,1000));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationToOutOfBoundsNegativeX() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(1,1), new Location2D(-1,0));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationToOutOfBoundsNegativeY() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(1,1), new Location2D(0,-1));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationFromOutOfBoundsOverX() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(1000,0), new Location2D(0,0));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationFromOutOfBoundsOverY() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(0,1000), new Location2D(0,0));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationFromOutOfBoundsNegativeX() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(-1,0), new Location2D(0,0));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveLocationFromOutOfBoundsNegativeY() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(0,-1), new Location2D(0,0));
	}
	
	@Test(expected=StrategyException.class)
	public void makeOutOfOrderMove() throws StrategyException {
		game.startGame();
		MoveResult result = game.move(PieceType.SERGEANT, new Location2D(3,5), new Location2D(3,4));
		
		assertEquals(MoveResultStatus.OK,result.getStatus());
	}
	
	@Test(expected=StrategyException.class)
	public void makeDistanceToFarMove() throws StrategyException {
		game.startGame();
		game.move(PieceType.SERGEANT, new Location2D(3,0), new Location2D(3,5));
	}
	
	@Test(expected=StrategyException.class)
	public void makeMoveOnSelf() throws StrategyException {
		game.startGame();
		game.move(PieceType.SERGEANT, new Location2D(5,0), new Location2D(5,0));
	}
	
	@Test(expected=StrategyException.class)
	public void makeInvalidMoveOntoOwnPiece() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL, new Location2D(1,1), new Location2D(0,1));
	}
	
	@Test(expected=StrategyException.class)
	public void moveFlagInvalidMove() throws StrategyException {
		game.startGame();
		game.move(PieceType.FLAG, new Location2D(0,1), new Location2D(0,2));
	}
	
	@Test(expected=StrategyException.class)
	public void moveWhenGameAlreadyOver() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL, new Location2D(1,1), new Location2D(1,2));
		game.move(PieceType.COLONEL, new Location2D(3,4), new Location2D(3,3));
		game.move(PieceType.MARSHAL, new Location2D(1,2), new Location2D(0,2));
		game.move(PieceType.COLONEL, new Location2D(3,3), new Location2D(3,4));
		game.move(PieceType.MARSHAL, new Location2D(0,2), new Location2D(0,3));
		game.move(PieceType.COLONEL, new Location2D(3,4), new Location2D(3,3));
		game.move(PieceType.MARSHAL, new Location2D(0,3), new Location2D(0,4));
		game.move(PieceType.COLONEL, new Location2D(3,3), new Location2D(3,4));
	}
	
	@Test
	public void makeValidMove() throws StrategyException {
		game.startGame();
		MoveResult result = game.move(PieceType.CAPTAIN, new Location2D(4,1), new Location2D(4,2));
		
		assertEquals(MoveResultStatus.OK,result.getStatus());
	}
	
	@Test
	public void makeAttackerWinBattle() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL,  new Location2D(1,1), new Location2D(1,2));
		game.move(PieceType.COLONEL,  new Location2D(2,4), new Location2D(2,3));
		game.move(PieceType.MARSHAL,  new Location2D(1,2), new Location2D(1,3));
		game.move(PieceType.CAPTAIN, new Location2D(5,4), new Location2D(5,3));
		MoveResult result = game.move(PieceType.MARSHAL,  new Location2D(1,3), new Location2D(2,3));
		
		assertEquals(result.getStatus(),MoveResultStatus.OK);
		assertEquals(result.getBattleWinner().getPiece(),new Piece(PieceType.MARSHAL, PlayerColor.RED));
	}
	
	@Test
	public void makeAttackerLoseBattle() throws StrategyException {
		game.startGame();
		game.move(PieceType.LIEUTENANT,  new Location2D(2,1), new Location2D(2,2));
		game.move(PieceType.COLONEL,  new Location2D(2,4), new Location2D(2,3));
		MoveResult result = game.move(PieceType.LIEUTENANT,  new Location2D(2,2), new Location2D(2,3));
		
		assertEquals(result.getBattleWinner().getPiece(), new Piece(PieceType.COLONEL, PlayerColor.BLUE));
	}
	
	@Test
	public void makeBattleDraw() throws StrategyException {
		game.startGame();
		game.move(PieceType.CAPTAIN, new Location2D(5,1), new Location2D(5,2));
		game.move(PieceType.CAPTAIN, new Location2D(5,4), new Location2D(5,3));
		MoveResult result = game.move(PieceType.CAPTAIN, new Location2D(5,2), new Location2D(5,3));
		
		assertEquals(result.getBattleWinner(),null);
	}
	
	@Test
	public void captureFlagBlue() throws StrategyException {
		game.startGame();
		game.move(PieceType.LIEUTENANT, new Location2D(2,1), new Location2D(2,2));
		game.move(PieceType.MARSHAL, new Location2D(1,4), new Location2D(1,3));
		game.move(PieceType.LIEUTENANT,new Location2D(2,2), new Location2D(2,1));
		game.move(PieceType.MARSHAL, new Location2D(1,3), new Location2D(1,2));
		game.move(PieceType.LIEUTENANT,new Location2D(2,1), new Location2D(2,2));
		game.move(PieceType.MARSHAL, new Location2D(1,2), new Location2D(0,2));
		game.move(PieceType.LIEUTENANT,new Location2D(2,2), new Location2D(2,1));
		MoveResult result = game.move(PieceType.MARSHAL,new Location2D(0,2), new Location2D(0,1));
		
		assertEquals(result.getStatus(),MoveResultStatus.BLUE_WINS);
	}
	
	@Test
	public void captureFlagRed() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL, new Location2D(1,1), new Location2D(1,2));
		game.move(PieceType.CAPTAIN, new Location2D(4,4), new Location2D(4,3));
		game.move(PieceType.MARSHAL, new Location2D(1,2), new Location2D(1,3));
		game.move(PieceType.CAPTAIN,new Location2D(4,3), new Location2D(4,4));
		game.move(PieceType.MARSHAL, new Location2D(1,3), new Location2D(0,3));
		game.move(PieceType.CAPTAIN,new Location2D(4,4), new Location2D(4,3));

		MoveResult result = game.move(PieceType.MARSHAL,new Location2D(0,3), new Location2D(0,4));
		
		assertEquals(result.getStatus(),MoveResultStatus.RED_WINS);
	}
	
	@Test
	public void gameEndsInDrawAfter12Turns() throws StrategyException {
		game.startGame();
		game.move(PieceType.LIEUTENANT, new Location2D(2,1), new Location2D(2,2));
		game.move(PieceType.MARSHAL, new Location2D(1,4), new Location2D(1,3));
		game.move(PieceType.LIEUTENANT,new Location2D(2,2), new Location2D(2,1));
		game.move(PieceType.MARSHAL, new Location2D(1,3), new Location2D(1,4));
		game.move(PieceType.LIEUTENANT, new Location2D(2,1), new Location2D(2,2));
		game.move(PieceType.MARSHAL, new Location2D(1,4), new Location2D(1,3));
		game.move(PieceType.LIEUTENANT,new Location2D(2,2), new Location2D(2,1));
		game.move(PieceType.MARSHAL, new Location2D(1,3), new Location2D(1,4));
		game.move(PieceType.LIEUTENANT, new Location2D(2,1), new Location2D(2,2));
		game.move(PieceType.MARSHAL, new Location2D(1,4), new Location2D(1,3));
		game.move(PieceType.LIEUTENANT,new Location2D(2,2), new Location2D(2,1));
		MoveResult result = game.move(PieceType.MARSHAL, new Location2D(1,3), new Location2D(1,4));
		
		assertEquals(result.getStatus(), MoveResultStatus.DRAW);
	}
	
	@Test
	(expected=StrategyException.class)
	public void startGameAfterGameOver() throws StrategyException {
		game.startGame();
		game.move(PieceType.MARSHAL, new Location2D(1,1), new Location2D(1,2));
		game.move(PieceType.CAPTAIN, new Location2D(4,4), new Location2D(4,3));
		game.move(PieceType.MARSHAL, new Location2D(1,2), new Location2D(1,3));
		game.move(PieceType.CAPTAIN,new Location2D(4,3), new Location2D(4,4));
		game.move(PieceType.MARSHAL, new Location2D(1,3), new Location2D(0,3));
		game.move(PieceType.CAPTAIN,new Location2D(4,4), new Location2D(4,3));

		MoveResult result = game.move(PieceType.MARSHAL,new Location2D(0,3), new Location2D(0,4));
		
		assertEquals(result.getStatus(),MoveResultStatus.RED_WINS);
		
		game.startGame();
	}
	
	@Test(expected=StrategyException.class)
	public void startGameBeforeGameOverAfterGameStarted() throws StrategyException {
		game.startGame();
		game.startGame();
	}
}
