package engine.emulation.key;

import java.awt.event.KeyEvent;

import engine.util.Position;
import engine.util.Time;

public class EmulatedKeyPress extends EmulatedKeyEvent{

	public EmulatedKeyPress(Time time, int key) {
		super(time, key);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		emulator.keyPress(key);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
