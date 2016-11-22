package engine.emulation.key;

import java.awt.event.KeyEvent;

import engine.util.Position;

public class EmulatedKeyHeld extends EmulatedKeyEvent{
	
	private long time;

	public EmulatedKeyHeld(Position position, KeyEvent key, long time) {
		super(position, key);
		this.time = time;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
