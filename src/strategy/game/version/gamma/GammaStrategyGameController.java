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

import strategy.common.StrategyException;
import strategy.game.StrategyGameController;
import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.Piece;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.common.PieceType;


/**
 * Implements the gamma version of Strategy (game)
 * @author drob, cbotaish
 *
 */
public class GammaStrategyGameController implements StrategyGameController {
	
	private GammaStrategyBoard gameBoard;
	private final GammaStrategyBoardValidator boardValidator;
	
	/**
	 * Create a gammaStrategyController instance. Takes two piece configurations and ensures they are valid,
	 * then creates the board.
	 * @param redConfig
	 * @param blueConfig
	 * @throws StrategyException
	 */
	public GammaStrategyGameController(Collection<PieceLocationDescriptor> redConfig,
			Collection<PieceLocationDescriptor> blueConfig) throws StrategyException {	
		
		this.boardValidator = new GammaStrategyBoardValidator();
		
		if(!boardValidator.isValidInitialSetup(redConfig, blueConfig)){
			throw new StrategyException("invalid setup!");
		}
	}
	
	@Override
	public void startGame() throws StrategyException {
		
	}

	@Override
	public MoveResult move(PieceType piece, Location from, Location to)
			throws StrategyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piece getPieceAt(Location location) {
		return null;
	}

}
