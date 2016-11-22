package engine.emulation.mouse;

import java.awt.event.InputEvent;

import engine.util.Position;
import engine.util.Time;

public class EmulatedMousePress extends EmulatedMouseEvent{

	public EmulatedMousePress(Time time, int mouseButton) {
		super(time, mouseButton);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		emulator.mousePress(mouseButton);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
