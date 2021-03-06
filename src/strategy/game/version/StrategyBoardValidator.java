/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package strategy.game.version;

import java.util.Collection;

import strategy.game.common.PieceLocationDescriptor;

/**
 * This interface provides validations for the initial board setups.
 * @author drob, cbotaish
 * @version Sept 22, 2013
 */
public interface StrategyBoardValidator {
	
	/**
	 * Check to see if the supplied piece configurations are correct (valid)
	 * @param redConfig the PieceLocationDescriptors for the red team
	 * @param blueConfig the PieceLocationDescriptors for the blue team
	 * @return whether or not the supplied configurations are both valid
	 */
	boolean isValidInitialSetup(Collection<PieceLocationDescriptor> redConfig,
								Collection<PieceLocationDescriptor> blueConfig);
	
	/**
	 * Returns the pieces specific to the game that are not the property of one of the players.
	 * @return the pieces that are neutral in the game.
	 */
	Collection<PieceLocationDescriptor> getGameSpecificPieces();
}
