package strategy.game.common;

import java.util.ArrayList;

public class StrategyMoveRememberator {
	
	final private int moveWindow; //how many moves will be remembered
	private int index;
	private ArrayList<PieceMoveEntry> buffer;
	
	public StrategyMoveRememberator(int moveWindow) {
		this.moveWindow = moveWindow;
		this.buffer = new ArrayList<PieceMoveEntry>();
		this.index = 0;
	}
	
	public void addMove(PieceMoveEntry move) {
		buffer.add(index,move);
		
		if(index >= moveWindow - 1) {
			index = 0;
		} else {
			index++;
		}
	}
	
	public boolean moveInList(PieceMoveEntry move) {
		return buffer.contains(move);
	}
}
