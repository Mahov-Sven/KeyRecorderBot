package engine.input;

import java.util.ArrayList;
import java.util.List;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class GlobalInput {
	
	private GlobalKeyboardHook keyboardHook;
	private GlobalKeyInputHandler keyboardInput;
	
	private GlobalMouseHook mouseHook;
	private GlobalMouseInputHandler mouseInput;
	
	private List<Object> objectsToNotify;

	public GlobalInput(){
		keyboardHook = new GlobalKeyboardHook();
		keyboardInput = new GlobalKeyInputHandler(this);
		keyboardHook.addKeyListener(keyboardInput);
		
		mouseHook = new GlobalMouseHook();
		mouseInput = new GlobalMouseInputHandler(this);
		mouseHook.addMouseListener(mouseInput);
		
		objectsToNotify = new ArrayList<Object>();
	}
	
	protected void notifyObjects(GlobalKeyEvent e){
		for(Object obj:objectsToNotify){
			NotifyableObjectByInputEvent notifyableObject = (NotifyableObjectByInputEvent) obj;
			notifyableObject.notifyOfInputKeyEvent(e);
		}
	}
	
	protected void notifyObjects(GlobalMouseEvent e){
		for(Object obj:objectsToNotify){
			NotifyableObjectByInputEvent notifyableObject = (NotifyableObjectByInputEvent) obj;
			notifyableObject.notifyOfInputMouseEvent(e);
		}
	}
	
	public GlobalInput add(Object obj){
		objectsToNotify.add(obj);
		return this;
	}
	
	public GlobalInput remove(Object obj){
		objectsToNotify.remove(obj);
		return this;
	}
	
	public GlobalInput clear(){
		objectsToNotify.clear();
		return this;
	}
	
	public void cleanUp(){
		objectsToNotify.clear();
		keyboardHook.shutdownHook();
		mouseHook.shutdownHook();
	}
}
