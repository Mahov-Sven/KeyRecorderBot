package engine.emulation.mouse;

import java.awt.event.InputEvent;

import engine.emulation.EmulatedEvent;
import engine.util.Position;

public abstract class EmulatedMouseEvent extends EmulatedEvent{

	protected InputEvent mouseButton;
	
	public EmulatedMouseEvent(Position position, InputEvent mouseButton) {
		super(position);
		this.mouseButton = mouseButton;
	}
}
