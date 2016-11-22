package engine.bot;

import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import engine.emulation.EmulatedEvent;
import engine.emulation.mouse.EmulatedMouseMovement;
import engine.util.Position;
import engine.util.Time;

public class BotRecorder implements Runnable{

	private List<EmulatedEvent> botEvents;
	//priority events are events which need to be executed between updates.
	//priority events allow for button pressing (button is pressed down, priority event releases the button afterwards)
	
	private String fileName;
	private String pathName;
	private boolean running = false;
	private long startTime;
	private Thread botRecorder;
	private final int UPS = 240;
	private final long NANO = 1000000000;
	
	public BotRecorder(String fileName, String pathName){
		this.botEvents = new ArrayList<EmulatedEvent>();
		this.fileName = fileName;
		this.pathName = pathName;
		this.botRecorder = new Thread(this);
		EmulatedEvent.init();
	}
	
	public void start(){
		running = true;
		botRecorder.start();
	}
	
	public void testForEscapeSequence(){
		if(System.nanoTime() - startTime >= 10 * Time.NANO) running = false;
	}
	
	private Position previousMousePos = new Position(-1,-1);
	
	public void update(){
		
		testForEscapeSequence();
		
		long deltaTime = System.nanoTime() - startTime;
		
		Position mousePos = Position.fromJPoint(MouseInfo.getPointerInfo().getLocation());
		
		if(!mousePos.equals(previousMousePos)) botEvents.add(new EmulatedMouseMovement(mousePos, new Time(deltaTime)));
		
		previousMousePos = mousePos;
	}

	@Override
	public void run() {
		long delta;
		long lastUpdate = System.nanoTime();
		
		long updates = 0;
		
		startTime = System.nanoTime();
		
		while(running){
			delta = System.nanoTime() - lastUpdate;
			if(UPS*delta/NANO >= 1){
				lastUpdate = System.nanoTime();
				
				if(updates%UPS == 0){
					//This is triggered once per second
					
					updates = 0;
				}
				
				update();
				
				updates++;
			}
		}
	}
	
	public List<EmulatedEvent> getRecordedEvents(){
		return this.botEvents;
	}
	
	public boolean isRunning(){
		return running;
	}
}
