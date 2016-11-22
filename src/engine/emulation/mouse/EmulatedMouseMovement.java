package engine.emulation.mouse;

import engine.util.Position;
import engine.util.Time;

public class EmulatedMouseMovement extends EmulatedMouseEvent{

	private Position position;
	
	public EmulatedMouseMovement(Position position, Time time) {
		super(time);
		this.position = position;
	}

	@Override
	public void execute() {
		emulator.mouseMove(position.x, position.y);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
