package engine.emulation;

import java.awt.AWTException;
import java.awt.Robot;

import engine.util.Position;
import engine.util.Time;

public abstract class EmulatedEvent {

	protected Time time;
	protected static Robot emulator;
	
	public EmulatedEvent(Time time){
		this.time = time;
	}
	
	public static void init(){
		try {
			emulator = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void execute();
	
	public boolean isTime(long time){
		return this.time.isTime(time);
	}
	
	public abstract String toString();
}
