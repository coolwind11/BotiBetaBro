/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package strategy.game.common;

import java.util.Collection;

/**
 * This interface provides validations for the initial board setups.
 * @author drob, cbotaish
 *
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
}
