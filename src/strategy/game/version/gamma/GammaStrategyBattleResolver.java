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

import strategy.game.common.Location;
import strategy.game.common.MoveResult;
import strategy.game.common.Piece;
import strategy.game.common.StrategyBattleResolver;
import strategy.game.common.StrategyBoard;


/**
 * This interface handles Strategy battles that occur when a piece moves to an occupied location
 * @author drob, cbotaish
 *
 */
public class GammaStrategyBattleResolver implements StrategyBattleResolver {

	@Override
	public MoveResult resolveBattle(StrategyBoard gameBoard,
			Piece battleInitiator, Location battleLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
