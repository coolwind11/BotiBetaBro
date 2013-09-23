package strategy.game.common;


public class StrategyMoveRememberator {
	
	final private int moveWindow; //how many moves will be remembered
	private int index;
	private PieceMoveEntry[] buffer;
	
	public StrategyMoveRememberator(int moveWindow) {
		this.moveWindow = moveWindow;
		this.buffer = new PieceMoveEntry[moveWindow];
		this.index = 0;
	}
	
	public void addMove(PieceMoveEntry move) {
		buffer[index] = move;
		
		if(index >= moveWindow - 1) {
			index = 0;
		} else {
			index++;
		}
	}
	
	public boolean moveInList(PieceMoveEntry move) {
		
		for(int i = 0; i < buffer.length; i++)
		{
			if(buffer[i] == null)
				continue;
			if(buffer[i].equals(move))
				return true;
		}
		
		return false;
	}
}
