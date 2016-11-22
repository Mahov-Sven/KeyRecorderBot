package engine.main;

import java.util.ArrayList;
import java.util.List;

import engine.emulation.EmulatedEvent;

public class Bot implements Runnable{

	private List<EmulatedEvent> bot = new ArrayList<EmulatedEvent>();
	
	private String fileName;
	private String pathName;
	
	public Bot(String fileName, String pathName, boolean newBot){
		this.fileName = fileName;
		this.pathName = pathName;
		EmulatedEvent.init();
	}
	
	public void start(){
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
