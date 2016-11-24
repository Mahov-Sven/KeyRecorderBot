package engine.input;

import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class GlobalKeyInputHandler extends GlobalKeyAdapter{
	
	private GlobalInput input;
	
	public GlobalKeyInputHandler(GlobalInput input){
		this.input = input;
	}

	@Override
	public void keyPressed(GlobalKeyEvent e){
		input.notifyObjects(e);
	}
	
	@Override
	public void keyReleased(GlobalKeyEvent e){
		input.notifyObjects(e);
	}
}
