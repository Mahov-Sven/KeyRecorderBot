package engine.emulation.mouse;

import engine.util.Time;

public class EmulatedMouseRelease extends EmulatedMouseEvent{

	public EmulatedMouseRelease(Time time, int mouseButton) {
		super(time, mouseButton);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		emulator.mouseRelease(mouseButton);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
