package engine.bot;

import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import engine.emulation.EmulatedEvent;
import engine.emulation.EmulationEnd;
import engine.emulation.mouse.EmulatedMouseMovement;
import engine.emulation.mouse.EmulatedMousePress;
import engine.emulation.mouse.EmulatedMouseRelease;
import engine.emulation.mouse.EmulatedMouseScroll;
import engine.input.NotifyableObjectByInputEvent;
import engine.util.Position;
import engine.util.Time;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class BotRecorder implements Runnable, NotifyableObjectByInputEvent{

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
		if(System.nanoTime() - startTime >= 10 * Time.NANO) end();
	}
	
	public void end(){
		botEvents.add(new EmulationEnd(new Time(System.nanoTime() - startTime)));
		running = false;
	}
	
	@Override
	public void notifyOfInputKeyEvent(GlobalKeyEvent e) {
		if(!running) return;
		
		System.out.println(e + " | " + e.getKeyChar() + "| " + e.getTransitionState() + " | " + e.getVirtualKeyCode());
	}

	Position prevMousePos = new Position(-1,-1);
	
	@Override
	public void notifyOfInputMouseEvent(GlobalMouseEvent e) {
		if(!running) return;
		
		Time time = new Time(System.nanoTime() - startTime);
		Position mousePos = new Position(e.getX(),e.getY());
		int buttonsPressed = e.getButtons();
		//0x##### where 0x[Middle Button][][][Right Button][Left Button] and
		//0x00000 means there are not buttons being pressed
		
		int mouseState = e.getTransitionState();
		//0 = Mouse button is released
		//1 = Mouse button is pressed
		//2 = Mouse has been moved
		//3 = Mouse wheel has been scrolled
		
		int buttonPressed = e.getButton();
		//gives the current button pressed or released.
		
		int VKbuttonPressed = 1 << (5 - buttonPressed);
		//The Virtual Keyboard adjusted button
		
		int scrollDelta = e.getDelta();
		//The ammount of scrolling requested

		//System.out.println(e + " | " + e.getTransitionState() + " | " + e.getButtons() + " | " + e.getButton());
		
		if(!mousePos.equals(prevMousePos)){
			botEvents.add(new EmulatedMouseMovement(mousePos, time));
			prevMousePos = mousePos;
		}
		
		if(mouseState == 0){
			botEvents.add(new EmulatedMouseRelease(time, VKbuttonPressed));
		}
		
		if(mouseState == 1){
			botEvents.add(new EmulatedMousePress(time, VKbuttonPressed));
		}
		
		if(mouseState == 3){
			botEvents.add(new EmulatedMouseScroll(time, VKbuttonPressed, scrollDelta));
		}
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
				
				testForEscapeSequence();
				
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
