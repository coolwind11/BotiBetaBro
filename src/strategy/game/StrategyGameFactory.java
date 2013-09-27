/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package strategy.game;

import java.util.Collection;

import strategy.common.StrategyException;
import strategy.game.common.PieceLocationDescriptor;
import strategy.game.version.BaseStrategyGameController;
import strategy.game.version.alpha.AlphaStrategyGameController;
import strategy.game.version.beta.BetaStrategyBoardValidator;
import strategy.game.version.beta.BetaStrategyMoveResolver;
import strategy.game.version.beta.BetaStrategyMoveValidator;
import strategy.game.version.delta.DeltaStrategyBoardValidator;
import strategy.game.version.delta.DeltaStrategyMoveResolver;
import strategy.game.version.delta.DeltaStrategyMoveValidator;
import strategy.game.version.gamma.GammaStrategyBoardValidator;
import strategy.game.version.gamma.GammaStrategyMoveResolver;
import strategy.game.version.gamma.GammaStrategyMoveValidator;

/**
 * <p>
 * Factory to produce various versions of the Strategy game. This is implemented
 * as a singleton.
 * </p><p>
 * NOTE: If an error occurs creating any game, that is not specified in the particular
 * factory method's documentation, the factory method should throw a 
 * StrategyRuntimeException.
 * </p>
 * 
 * @author gpollice
 * @version Sep 10, 2013
 */
public class StrategyGameFactory
{
	private static final StrategyGameFactory instance = new StrategyGameFactory();
	
	/**
	 * Default private constructor to ensure this is a singleton.
	 */
	private StrategyGameFactory()
	{
		// Intentionally left empty.
	}

	/**
	 * @return the instance
	 */
	public static StrategyGameFactory getInstance()
	{
		return instance;
	}
	
	/**
	 * Create an Alpha Strategy game.
	 * @return the created Alpha Strategy game
	 */
	public StrategyGameController makeAlphaStrategyGame()
	{
		return new AlphaStrategyGameController();
	}
	
	/**
	 * Create a new Beta Strategy game given the 
	 * @param redConfiguration the initial starting configuration for the RED pieces
	 * @param blueConfiguration the initial starting configuration for the BLUE pieces
	 * @return the Beta Strategy game instance with the initial configuration of pieces
	 * @throws StrategyException if either configuration is correct
	 */
	public StrategyGameController makeBetaStrategyGame(
			Collection<PieceLocationDescriptor> redConfiguration,
			Collection<PieceLocationDescriptor> blueConfiguration)
		throws StrategyException
	{
		if(redConfiguration == null || blueConfiguration == null)
		{
			throw new StrategyException("Cannot have null red or blue configuration.");
		}
				
		return new BaseStrategyGameController(new BetaStrategyBoardValidator(), new BetaStrategyMoveValidator(),
				new BetaStrategyMoveResolver(), redConfiguration, blueConfiguration);
	}
	
	/**
	 * Factorify a gamma strategy game instance
	 * @param redConfiguration pieces provided by the red player(s)
	 * @param blueConfiguration pieces provided by the blue player(s)
	 * @return a gamma strategy game
	 * @throws StrategyException if there is an issue with either of the configurations
	 */
	public StrategyGameController makeGammaStrategyGame(
			Collection<PieceLocationDescriptor> redConfiguration, 
			Collection<PieceLocationDescriptor> blueConfiguration) throws StrategyException
	{
		if(redConfiguration == null || blueConfiguration == null){
			throw new StrategyException("Cannot initialize game with no piece configuration(s)");
		}
			
		return new BaseStrategyGameController(new GammaStrategyBoardValidator(), new GammaStrategyMoveValidator(),
				new GammaStrategyMoveResolver(), redConfiguration, blueConfiguration);
	}
	
	/**
	 * Creates a new delta implementation of the strategy game.
	 * @param redConfiguration pieces provided by the red player(s)
	 * @param blueConfiguration pieces provided by the blue player(s)
	 * @return a delta strategy game
	 * @throws StrategyException if there is an issue with either of the configurations
	 */
	public StrategyGameController makeDeltaStrategyGame(
			Collection<PieceLocationDescriptor> redConfiguration, 
			Collection<PieceLocationDescriptor> blueConfiguration) throws StrategyException
	{
		if(redConfiguration == null || blueConfiguration == null){
			throw new StrategyException("Cannot initialize game with no piece configuration(s)");
		}
			
		return new BaseStrategyGameController(new DeltaStrategyBoardValidator(), new DeltaStrategyMoveValidator(),
				new DeltaStrategyMoveResolver(), redConfiguration, blueConfiguration);
	}
}
