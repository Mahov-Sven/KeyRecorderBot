package engine.emulation.key;

import java.awt.event.KeyEvent;

import engine.emulation.EmulatedEvent;
import engine.util.Position;

public abstract class EmulatedKeyEvent extends EmulatedEvent{
	
	protected KeyEvent key;

	public EmulatedKeyEvent(Position position, KeyEvent key) {
		super(position);
		this.key = key;
	}
}
