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
import static strategy.game.common.PieceType.MINER;
import static strategy.game.common.PieceType.MAJOR;
import static strategy.game.common.PieceType.BOMB;
import static strategy.game.common.PieceType.GENERAL;
import static strategy.game.common.PieceType.SPY;
import static strategy.game.common.PieceType.SCOUT;
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

	private void setup(){
		redPieces = new ArrayList<PieceLocationDescriptor>();
		bluePieces = new ArrayList<PieceLocationDescriptor>();

		addToConfig(MINER,RED,0,0);
		addToConfig(MAJOR,RED,1,0);
		addToConfig(COLONEL,RED,2,0);
		addToConfig(GENERAL,RED,3,0);
		addToConfig(SCOUT,RED,4,0);
		addToConfig(SCOUT,RED,5,0);
		addToConfig(COLONEL,RED,6,0);
		addToConfig(MAJOR,RED,7,0);
		addToConfig(MAJOR,RED,8,0);
		addToConfig(SERGEANT,RED,9,0);

		addToConfig(CAPTAIN,RED,0,1);
		addToConfig(CAPTAIN,RED,1,1);
		addToConfig(MINER,RED,2,1);
		addToConfig(BOMB,RED,3,1);
		addToConfig(SCOUT,RED,4,1);
		addToConfig(SCOUT,RED,5,1);
		addToConfig(BOMB,RED,6,1);
		addToConfig(MINER,RED,7,1);
		addToConfig(CAPTAIN,RED,8,1);
		addToConfig(CAPTAIN,RED,9,1);	

		addToConfig(LIEUTENANT,RED,0,2);
		addToConfig(LIEUTENANT,RED,1,2);
		addToConfig(BOMB,RED,2,2);
		addToConfig(BOMB,RED,3,2);
		addToConfig(MINER,RED,4,2);
		addToConfig(MINER,RED,5,2);
		addToConfig(BOMB,RED,6,2);
		addToConfig(BOMB,RED,7,2);
		addToConfig(LIEUTENANT,RED,8,2);
		addToConfig(LIEUTENANT,RED,9,2);	

		addToConfig(CAPTAIN,RED,0,3);
		addToConfig(CAPTAIN,RED,1,3);
		addToConfig(MINER,RED,2,3);
		addToConfig(BOMB,RED,3,3);
		addToConfig(SCOUT,RED,4,3);
		addToConfig(SCOUT,RED,5,3);
		addToConfig(BOMB,RED,6,3);
		addToConfig(MINER,RED,7,3);
		addToConfig(CAPTAIN,RED,8,3);
		addToConfig(CAPTAIN,RED,9,3);	

		addToConfig(FLAG,RED,0,4);
		addToConfig(SERGEANT,RED,1,4);
		addToConfig(SCOUT,RED,2,4);
		addToConfig(SERGEANT,RED,3,4);
		addToConfig(MARSHAL,RED,4,4);
		addToConfig(SPY,RED,5,4);
		addToConfig(SERGEANT,RED,6,4);
		addToConfig(SCOUT,RED,7,4);
		addToConfig(SCOUT,RED,8,4);
		addToConfig(SCOUT,RED,9,4);	

		addToConfig(MINER,BLUE,0,9);
		addToConfig(MAJOR,BLUE,1,9);
		addToConfig(COLONEL,BLUE,2,9);
		addToConfig(GENERAL,BLUE,3,9);
		addToConfig(SCOUT,BLUE,4,9);
		addToConfig(SCOUT,BLUE,5,9);
		addToConfig(COLONEL,BLUE,6,9);
		addToConfig(MAJOR,BLUE,7,9);
		addToConfig(MAJOR,BLUE,8,9);
		addToConfig(SERGEANT,BLUE,9,9);

		addToConfig(CAPTAIN,BLUE,0,8);
		addToConfig(CAPTAIN,BLUE,1,8);
		addToConfig(MINER,BLUE,2,8);
		addToConfig(BOMB,BLUE,3,8);
		addToConfig(SCOUT,BLUE,4,8);
		addToConfig(SCOUT,BLUE,5,8);
		addToConfig(BOMB,BLUE,6,8);
		addToConfig(MINER,BLUE,7,8);
		addToConfig(CAPTAIN,BLUE,8,8);
		addToConfig(CAPTAIN,BLUE,9,8);	

		addToConfig(LIEUTENANT,BLUE,0,7);
		addToConfig(LIEUTENANT,BLUE,1,7);
		addToConfig(BOMB,BLUE,2,7);
		addToConfig(BOMB,BLUE,3,7);
		addToConfig(MINER,BLUE,4,7);
		addToConfig(MINER,BLUE,5,7);
		addToConfig(BOMB,BLUE,6,7);
		addToConfig(BOMB,BLUE,7,7);
		addToConfig(LIEUTENANT,BLUE,8,7);
		addToConfig(LIEUTENANT,BLUE,9,7);

		addToConfig(FLAG,BLUE,0,6);
		addToConfig(SERGEANT,BLUE,1,6);
		addToConfig(SCOUT,BLUE,2,6);
		addToConfig(SERGEANT,BLUE,3,6);
		addToConfig(MARSHAL,BLUE,4,6);
		addToConfig(SPY,BLUE,5,6);
		addToConfig(SERGEANT,BLUE,6,6);
		addToConfig(SCOUT,BLUE,7,6);
		addToConfig(SCOUT,BLUE,8,6);
		addToConfig(SCOUT,BLUE,9,6);	
	}
	


	private void addToConfig(PieceType piece, PlayerColor color, int x, int y) {
		final PieceLocationDescriptor toAdd = new PieceLocationDescriptor(
			new Piece(piece,color), new Location2D(x,y));
		if(color == PlayerColor.RED){
			redPieces.add(toAdd);
		} else {
			bluePieces.add(toAdd);
		}
	}
	

}
