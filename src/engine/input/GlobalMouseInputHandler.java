package engine.input;

import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class GlobalMouseInputHandler extends GlobalMouseAdapter{
	
	private GlobalInput input;

	public GlobalMouseInputHandler(GlobalInput input){
		this.input = input;
	}
	
	@Override
	public void mousePressed(GlobalMouseEvent e){
		input.notifyObjects(e);
	}
	
	@Override
	public void mouseReleased(GlobalMouseEvent e){
		input.notifyObjects(e);
	}
	
	@Override
	public void mouseMoved(GlobalMouseEvent e){
		input.notifyObjects(e);
	}
	
	@Override
	public void mouseWheel(GlobalMouseEvent e){
		input.notifyObjects(e);
	}
}
