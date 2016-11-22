package engine.util;

public class Time {

	private long time; //time is in Nano seconds
	public static final long NANO = 1000000000;
	public static final long MILI = 1000000;
	
	public Time(long time){
		this.time = time;
	}
	
	public boolean isTime(long time){
		if(time >= this.time) return true;
		return false;
	}
	
	public boolean isTimeExact(long time){
		if(time == this.time) return true;
		return false;
	}
}
