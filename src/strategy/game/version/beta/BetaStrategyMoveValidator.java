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

import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveValidator;

/**
 * Provides a move validator strategy for the BetaStrategy implementation
 * @author cbotaish, drob
 * @version Sept 23, 2013
 */
public class BetaStrategyMoveValidator extends BaseStrategyMoveValidator
{
	@Override
	protected void setupMoveConstraints()
	{
		super.boardHeight = 6;
		super.boardWidth = 6;
		validMoveDistances.put(PieceType.CAPTAIN, 1);
		validMoveDistances.put(PieceType.COLONEL, 1);
		validMoveDistances.put(PieceType.FLAG, 0);
		validMoveDistances.put(PieceType.CHOKE_POINT, 0);
		validMoveDistances.put(PieceType.LIEUTENANT, 1);
		validMoveDistances.put(PieceType.MARSHAL, 1);
		validMoveDistances.put(PieceType.SERGEANT, 1);	
	}
}
