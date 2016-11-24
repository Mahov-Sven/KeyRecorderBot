package engine.input;

import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public interface NotifyableObjectByInputEvent {

	public void notifyOfInputKeyEvent(GlobalKeyEvent e);
	
	public void notifyOfInputMouseEvent(GlobalMouseEvent e);
}
