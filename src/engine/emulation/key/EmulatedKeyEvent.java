package engine.emulation.key;

import engine.emulation.EmulatedEvent;
import engine.util.Position;
import engine.util.Time;

public abstract class EmulatedKeyEvent extends EmulatedEvent{
	
	protected int key;

	public EmulatedKeyEvent(Time time, int key) {
		super(time);
		this.key = key;
	}
}
