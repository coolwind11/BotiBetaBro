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

import strategy.game.common.PieceType;
import strategy.game.version.BaseStrategyMoveResolver;

/**
 * Provides an implementation of the BaseStrategyMoveResolver for the gamma strategy
 * @author cbotaish, drob
 * @version 9/23/13
 */
public class GammaStrategyMoveResolver extends BaseStrategyMoveResolver {

	@Override
	protected void setupResolverConfiguration() {
		pieceRank.put(PieceType.MARSHAL, 12);
		pieceRank.put(PieceType.CAPTAIN, 8);
		pieceRank.put(PieceType.COLONEL, 10);
		pieceRank.put(PieceType.LIEUTENANT, 7);
		pieceRank.put(PieceType.SERGEANT, 6);
		pieceRank.put(PieceType.FLAG, 1);
	}
	
}
