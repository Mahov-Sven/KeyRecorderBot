package engine.emulation.mouse;

import engine.util.Time;

public class EmulatedMouseScroll extends EmulatedMouseEvent{
	
	private int delta;
	private static final int ADJUSTMENT = 32;

	public EmulatedMouseScroll(Time time, int mouseButton, int delta) {
		super(time, mouseButton);
		this.delta = -delta/ADJUSTMENT;
	}

	@Override
	public void execute() {
		emulator.mouseWheel(delta);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
