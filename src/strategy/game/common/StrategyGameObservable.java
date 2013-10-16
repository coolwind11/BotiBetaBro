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

/**
 * This interface provides an interface for an observable strategy game to be implemented.
 * @author gpollice
 * @version Aug 31, 2013
 */
public interface StrategyGameObservable 
{
	/**
	 * Registers an observer with the observable
	 * @param observer the observer to be registered with the observable game
	 */
	void register(StrategyGameObserver observer);

	/**
	 * Removes an observer from the observable game
	 * @param observer the observer that is being removed from the observable game.
	 */
	void unregister(StrategyGameObserver observer); 
}