package engine.emulation.keyboard;

import java.awt.event.KeyEvent;

import engine.util.Position;
import engine.util.Time;

public class EmulatedKeyRelease extends EmulatedKeyEvent{

	public EmulatedKeyRelease(Time time, int key) {
		super(time, key);
	}

	@Override
	public void execute() {
		emulator.keyRelease(key);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
