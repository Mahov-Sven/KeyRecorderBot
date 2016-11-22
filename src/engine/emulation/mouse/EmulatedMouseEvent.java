package engine.emulation.mouse;

import engine.emulation.EmulatedEvent;
import engine.util.Position;
import engine.util.Time;

public abstract class EmulatedMouseEvent extends EmulatedEvent{

	protected int mouseButton;
	
	public EmulatedMouseEvent(Time time, int mouseButton) {
		super(time);
		this.mouseButton = mouseButton;
	}
	
	public EmulatedMouseEvent(Time time){
		super(time);
	}
}
