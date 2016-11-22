package engine.bot;

import java.util.ArrayList;
import java.util.List;

import engine.emulation.EmulatedEvent;

public class Bot implements Runnable{

	private List<EmulatedEvent> botEvents;
	//priority events are events which need to be executed between updates.
	//priority events allow for button pressing (button is pressed down, priority event releases the button afterwards)
	
	private String fileName;
	private String pathName;
	private boolean running = false;
	private long startTime;
	private Thread bot;
	private final int UPS = 120;
	private final long NANO = 1000000000;
	
	public Bot(String fileName, String pathName){
		this.botEvents = new ArrayList<EmulatedEvent>();
		this.fileName = fileName;
		this.pathName = pathName;
		this.bot = new Thread(this);
		EmulatedEvent.init();
	}
	
	public void start(){
		running = true;
		bot.start();
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
			
			if(botEvents.size() == 0) running = false;
		}
	}
	
	private void update(){
		if(botEvents.size() == 0) return;
		
		long deltaTime = System.nanoTime() - startTime;
		while(true){
			if(botEvents.size() == 0) break;
			
			EmulatedEvent event = botEvents.get(0);
			if(!event.isTime(deltaTime)) break;
			
			event.execute();
			
			botEvents.remove(0);
		}
	}
	
	public synchronized void addEvent(EmulatedEvent event){
		botEvents.add(event);
	}
	
	public synchronized void addEvents(List<EmulatedEvent> events){
		botEvents.addAll(events);
	}
	
	public boolean isRunning(){
		return running;
	}
}
