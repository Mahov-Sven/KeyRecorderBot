package engine.emulation;

import java.awt.AWTException;
import java.awt.Robot;

import engine.util.Position;

public abstract class EmulatedEvent {

	protected Position position;
	protected static Robot emulator;
	
	public EmulatedEvent(Position position){
		this.position = position;
	}
	
	public static void init(){
		try {
			emulator = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void execute();
	
	public abstract String toString();
}
